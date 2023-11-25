package com.example.demo.common.file.service.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Override
    public void createBucket(String bucketName) {
        //  ToDO 해당 부분에 버킷이 있는지를 체크하는 로직을 추가한다.
        s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        log.info("create bucket = {}", bucketName);
    }

    @Override
    public void createFolderIfNotExists(String bucketName, String folderName) {
        if (!doesFolderExist(bucketName, folderName)) {
            log.info("folderName = {}", folderName);
            createFolder(bucketName, folderName);
        }
    }

    @Override
    public boolean uploadMultipartFile(MultipartFile multipartFile, String bucketName, String key) {

        try (InputStream inputStream = multipartFile.getInputStream()) {
            PutObjectRequest putObjectRequest =
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build();

            RequestBody requestBody = RequestBody
                    .fromInputStream(inputStream, multipartFile.getSize());

            PutObjectResponse response = s3Client.putObject(putObjectRequest, requestBody);
            if (response != null) {
                log.info("File uploaded successfully to S3. Bucket: {}, Key: {}", bucketName, key);
                return true;
            } else {
                log.error("Error uploading file to S3. Bucket: {}, Key: {}", bucketName, key);
                return false;
            }
        } catch (Exception e) {
            log.error("Error uploading file to S3. Bucket: {}, Key: {}", bucketName, key, e);
            return false;
        }

    }

    @Override
    public boolean uploadFile(File file, String bucketName, String key) {

        try (InputStream inputStream = new FileInputStream(file)) {

            PutObjectRequest putObjectRequest =
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build();

            RequestBody requestBody = RequestBody.fromInputStream(inputStream, file.length());

            PutObjectResponse response = s3Client.putObject(putObjectRequest, requestBody);
            if (response != null) {
                log.info("File uploaded successfully to S3. Bucket: {}, Key: {}", bucketName, key);
                return true;
            } else {
                log.error("Error uploading file to S3. Bucket: {}, Key: {}", bucketName, key);
                return false;
            }
        } catch (Exception e) {
            log.error("Error uploading file to S3. Bucket: {}, Key: {}", bucketName, key, e);
            return false;
        }

    }

    @Override
    public boolean deleteFile(String bucketName, String filePath) {
        try {

            DeleteObjectResponse response = s3Client.deleteObject(
                    DeleteObjectRequest.builder()
                            .bucket(bucketName)
                            .key(filePath)
                            .build()
            );

            if (response.deleteMarker() == null) {
                log.info("File deleted successfully from S3. Bucket = {} , Key = {}", bucketName, filePath);
                return true;
            } else {
                log.error("Error deleting file from S3. Bucket = {} , Key = {}", bucketName, filePath);
                return false;
            }

        } catch (Exception e) {

            log.error("Error deleting file from S3. Bucket = {} , Key = {}", bucketName, filePath);
            return false;

        }
    }

    @Override
    public void s3FolderInit(StringBuilder boardCurrentPath, String bucketName) {
        StringBuilder currentPath = new StringBuilder();
        String[] dividePath = boardCurrentPath.toString().split("\\/");
        for (String folder : dividePath) {
            currentPath.append(folder).append("/");
            this.createFolderIfNotExists(bucketName, currentPath.toString());
        }
    }

    private boolean createFolder(String bucketName, String folderName) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderName)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(new byte[0]));
            log.info("success create folder for s3");
            return true;
        } catch (S3Exception s3Exception) {
            log.error("fail create folder for s3 = ", s3Exception);
            return false;
        }
    }

    private boolean doesFolderExist(String bucketName, String folderName) {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(folderName)
                .delimiter("/")
                .maxKeys(1)
                .build();
        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);
        if (!listObjectsResponse.contents().isEmpty() || !listObjectsResponse.commonPrefixes().isEmpty()) {
            log.info("doesFolderExist for s3");
            return true;
        } else {
            log.info("not doesFolderExist for s3");
            return false;
        }
    }


}
