package com.example.demo.user.service;

import com.example.demo.common.file.mapper.BlogCommonFileMapper;
import com.example.demo.common.file.service.BlogCommonFileService;
import com.example.demo.common.file.service.s3.S3Service;
import com.example.demo.common.properties.properties.BlogAwsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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

import static com.example.demo.common.file.dto.res.FileInfo.InsertOrUpdate;
import static org.testcontainers.shaded.com.google.common.io.Files.getFileExtension;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final S3Service s3Service;
    private final BlogCommonFileMapper blogCommonFileMapper;
    private final BlogCommonFileService blogCommonFileService;
    private final BlogAwsProperties blogAwsProperties;

    @Override
    public boolean userProfileImageUpload(MultipartFile multipartFile, Long profileId) {

        BlogAwsProperties.BlogAwsS3ConfigProperties blogAwsS3Properties = blogAwsProperties.getBlogAwsS3Properties();

        //  ToDO image 파일 검증 부분 추가 필요
        String createFolderPath = blogAwsS3Properties.getProfileImagePath() + "/" + profileId + "/";
        s3Service.createFolderIfNotExists(blogAwsS3Properties.getIntegrated(), createFolderPath);

        String uploadFilePath = blogCommonFileService.setUploadFilePath(multipartFile, blogAwsS3Properties.getBoardFilePath(), profileId);
        s3Service.uploadMultipartFile(multipartFile, blogAwsS3Properties.getIntegrated(), uploadFilePath);

        //  db insert 부분.
        InsertOrUpdate insertOrUpdate = blogCommonFileService.setInsertOrUpdateObject(multipartFile, uploadFilePath, profileId);
        log.info("insertOrUpdate = {}", insertOrUpdate);
        blogCommonFileMapper.insertOrUpdateFile(insertOrUpdate);

        //  ToDO
        return false;

    }

    @Override
    public boolean defaultUserProfileImageUpload(File file) {

        Long profileId = 1L;
        BlogAwsProperties.BlogAwsS3ConfigProperties blogAwsS3Properties = blogAwsProperties.getBlogAwsS3Properties();

        //  ToDO image 파일 검증 부분 추가 필요
        String createFolderPath = blogAwsS3Properties.getSiteFilePath() + "/" + profileId + "/";
        s3Service.createFolderIfNotExists(blogAwsS3Properties.getIntegrated(), createFolderPath);

        String uploadFilePath = blogCommonFileService.setUploadFilePath(file, blogAwsS3Properties.getSiteFilePath(), profileId);
        s3Service.uploadFile(file, blogAwsS3Properties.getIntegrated(), uploadFilePath);

        //  db insert 부분.
        InsertOrUpdate insertOrUpdate = blogCommonFileService.setInsertOrUpdateObject(file, uploadFilePath, profileId);
        log.info("insertOrUpdate = {}", insertOrUpdate);
        blogCommonFileMapper.insertOrUpdateFile(insertOrUpdate);

        //  ToDO
        return false;

    }

}
