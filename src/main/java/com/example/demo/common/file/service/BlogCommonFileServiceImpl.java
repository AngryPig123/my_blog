package com.example.demo.common.file.service;

import com.example.demo.common.file.dto.res.FileInfo;
import com.example.demo.common.file.mapper.BlogCommonFileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.testcontainers.shaded.com.google.common.io.Files.getFileExtension;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogCommonFileServiceImpl implements BlogCommonFileService {

    @Value("${cloud.aws.s3.endPoint}")
    private String AWS_S3_ENDPOINT;

    @Value("${spring.profiles.active}")
    private String PROFILES;
    @Value("${cloud.aws.s3.bucket.integrated}")
    private String S3_BUCKET_INTEGRATED;

    private final BlogCommonFileMapper blogCommonFileMapper;

    @Override
    public List<FileInfo.FileResult> fileInfoList() {
        return blogCommonFileMapper.fileInfoList(setFilePath());
    }

    @Override
    public int insertOrUpdateFile(FileInfo.InsertOrUpdate insertOrUpdate) {
        return blogCommonFileMapper.insertOrUpdateFile(insertOrUpdate);
    }

    @Override
    public String setUploadFilePath(Object fileObject, String bucketFolderPath, Long profileId) {
        List<String> fileName;
        if (fileObject instanceof MultipartFile) {
            MultipartFile multipartFile = (MultipartFile) fileObject;
            fileName = new ArrayList<>(Arrays.asList(Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")));
        } else if (fileObject instanceof File) {
            File file = (File) fileObject;
            fileName = new ArrayList<>(Arrays.asList(Objects.requireNonNull(file.getName()).split("\\.")));
        } else {
            throw new RuntimeException("반환할 수 있는 타입이 아닙니다.");
        }
        String extension = fileName.remove(fileName.size() - 1);
        long epochSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        String filePath = String.join("", fileName) + "_" + epochSecond;
        return bucketFolderPath + "/" + profileId + "/" + filePath + "." + extension;
    }

    @Override
    public FileInfo.InsertOrUpdate setInsertOrUpdateObject(Object fileObject, String path, Long id) {
        String fileName;
        long fileSize;
        String mimeType;
        String extension;

        if (fileObject instanceof MultipartFile) {
            MultipartFile multipartFile = (MultipartFile) fileObject;
            fileName = multipartFile.getOriginalFilename();
            fileSize = multipartFile.getSize();
            mimeType = multipartFile.getContentType();
            extension = getFileExtension(Objects.requireNonNull(fileName));
        } else if (fileObject instanceof File) {
            File file = (File) fileObject;
            fileName = file.getName();
            fileSize = file.length();
            try {
                mimeType = Files.probeContentType(file.toPath());
            } catch (IOException e) {
                mimeType = "application/octet-stream";
            }
            extension = getFileExtension(Objects.requireNonNull(fileName));
        } else {
            throw new RuntimeException("반환할 수 있는 타입이 아닙니다.");
        }

        return FileInfo.InsertOrUpdate.builder()
                .name(fileName)
                .size(fileSize)
                .mimeType(mimeType)
                .extension(extension)
                .path(path)
                .userId(id)
                .build();
    }

    private String setFilePath() {
        return PROFILES.equals("local") ? AWS_S3_ENDPOINT + "/" + S3_BUCKET_INTEGRATED + "/" : AWS_S3_ENDPOINT;
    }

}
