package com.example.demo.runnable;

import com.example.demo.common.file.service.s3.S3Service;
import com.example.demo.common.properties.properties.BlogAwsProperties;
import com.example.demo.common.properties.properties.BlogServerProperties;
import com.example.demo.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
class CommonRunnableService {

    private final S3Service s3Service;
    private final ServerProperties serverProperties;
    private final BlogAwsProperties blogAwsProperties;
    private final UserProfileService userProfileService;
    private final BlogServerProperties blogServerProperties;

    void createLocalStackBucket() {
        BlogAwsProperties.BlogAwsS3ConfigProperties blogAwsS3ConfigProperties = blogAwsProperties.getBlogAwsS3Properties();
        s3Service.createBucket(blogAwsS3ConfigProperties.getIntegrated());
    }

    void initBucketImageFolder() {
        log.info("======================================== S3 BUCKET, FOLDER SETTING START ========================================");
        BlogAwsProperties.BlogAwsS3ConfigProperties blogAwsS3ConfigProperties = blogAwsProperties.getBlogAwsS3Properties();

        String[] profileFolder = blogAwsS3ConfigProperties.getProfileImagePath().split("/");
        String[] boardCurrentPath = blogAwsS3ConfigProperties.getBoardFilePath().split("/");
        String[] siteFileFolder = blogAwsS3ConfigProperties.getSiteFilePath().split("/");

        StringBuilder currentPath = new StringBuilder();
        s3FolderInit(profileFolder, currentPath, blogAwsS3ConfigProperties.getIntegrated());

        currentPath = new StringBuilder();
        s3FolderInit(boardCurrentPath, currentPath, blogAwsS3ConfigProperties.getIntegrated());

        currentPath = new StringBuilder();
        s3FolderInit(siteFileFolder, currentPath, blogAwsS3ConfigProperties.getIntegrated());

        log.info("======================================== S3 BUCKET, FOLDER SETTING END ========================================");
    }

    void initBucketDefaultImageFolder() {
        log.info("======================================== DEFAULT IMAGE SETTING START ========================================");
        //  유저가 프로필 사진을 직접 등록 안하고 시스템에 등록 되어 있는 기본 이미지를 사용할 수 있게 하기 위함.
        String defaultImageFolderPath = "C:/Users/shgud/Desktop/blog/blog/src/main/resources/static/default_image";
        String defaultMarkdownFolderPath = "C:/Users/shgud/Desktop/blog/blog/src/main/resources/static/default_markdown";

        defaultFileSetting(defaultImageFolderPath);
        defaultFileSetting(defaultMarkdownFolderPath);

        log.info("======================================== DEFAULT IMAGE SETTING END ========================================");
    }

    private void defaultFileSetting(String defaultImageFolderPath) {
        File dir = new File(defaultImageFolderPath);
        File[] files = dir.listFiles();

        for (File file : Objects.requireNonNull(files)) {
            userProfileService.defaultUserProfileImageUpload(file);
        }
    }

    void initServerLink() {
        String contextPath = serverProperties.getServlet().getContextPath() == null ? "" : serverProperties.getServlet().getContextPath();
        String link = blogServerProperties.getBLOG_SERVER_PREFIX() + ":" + blogServerProperties.getSERVET_PORT() + contextPath;
        log.info("link = {}", link);
    }

    void s3FolderInit(String[] boardCurrentPath, StringBuilder currentPath, String bucketName) {
        for (String folder : boardCurrentPath) {
            currentPath.append(folder).append("/");
            s3Service.createFolderIfNotExists(bucketName, currentPath.toString());
        }
    }

}
