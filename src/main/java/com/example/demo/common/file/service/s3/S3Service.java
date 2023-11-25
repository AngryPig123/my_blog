package com.example.demo.common.file.service.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface S3Service {
    void createBucket(String bucketName);

    void createFolderIfNotExists(String bucketName, String folderName);

    boolean deleteFile(String bucketName, String filePath);

    boolean uploadMultipartFile(MultipartFile multipartFile, String bucketName, String key);

    boolean uploadFile(File file, String bucketName, String key);

    void s3FolderInit(StringBuilder boardCurrentPath, String bucketName);

}
