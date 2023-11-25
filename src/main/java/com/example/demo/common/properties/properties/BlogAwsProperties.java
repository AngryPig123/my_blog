package com.example.demo.common.properties.properties;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogAwsProperties {

    private final String AWS_REGION;
    private final String AWS_CREDENTIALS_ACCESS_KEY;
    private final String AWS_CREDENTIALS_SECRET_KEY;
    private final String INTEGRATED;
    private final String SITE_FILE_PATH;
    private final String PROFILE_IMAGE_PATH;
    private final String BOARD_FILE_PATH;
    private final String S3_END_POINT;

    public BlogAwsProperties(
            @Value("${cloud.aws.region.static}") String awsRegion,
            @Value("${cloud.aws.credentials.accessKey}") String accessKey,
            @Value("${cloud.aws.credentials.secretKey}") String secretKey,
            @Value("${cloud.aws.s3.bucket.integrated}") String integrated,
            @Value("${cloud.aws.s3.folder.site.file}") String siteFilePath,
            @Value("${cloud.aws.s3.folder.profile.image}") String profileImagePath,
            @Value("${cloud.aws.s3.folder.board.file}") String boardFilePath,
            @Value("${cloud.aws.s3.endPoint}") String s3EndPoint
    ) {
        this.AWS_REGION = awsRegion;
        this.AWS_CREDENTIALS_ACCESS_KEY = accessKey;
        this.AWS_CREDENTIALS_SECRET_KEY = secretKey;
        this.INTEGRATED = integrated;
        this.SITE_FILE_PATH = siteFilePath;
        this.PROFILE_IMAGE_PATH = profileImagePath;
        this.BOARD_FILE_PATH = boardFilePath;
        this.S3_END_POINT = s3EndPoint;
    }

    @Getter
    @Builder
    @ToString
    @RequiredArgsConstructor
    public static class BlogAwsConfigProperties {
        private final String region;
        private final String awsCredentialsAccessKey;
        private final String awsCredentialsSecretKey;
    }

    public BlogAwsConfigProperties getBlogAwsConfigProperties() {
        return BlogAwsConfigProperties.builder()
                .region(AWS_REGION)
                .awsCredentialsAccessKey(AWS_CREDENTIALS_ACCESS_KEY)
                .awsCredentialsSecretKey(AWS_CREDENTIALS_SECRET_KEY)
                .build();
    }


    @Getter
    @Builder
    @ToString
    @RequiredArgsConstructor
    public static class BlogAwsS3ConfigProperties {
        private final String s3EndPoint;
        private final String integrated;
        private final String siteFilePath;
        private final String profileImagePath;
        private final String boardFilePath;
    }

    public BlogAwsS3ConfigProperties getBlogAwsS3Properties() {
        return BlogAwsS3ConfigProperties.builder()
                .s3EndPoint(S3_END_POINT)
                .integrated(INTEGRATED)
                .siteFilePath(SITE_FILE_PATH)
                .profileImagePath(PROFILE_IMAGE_PATH)
                .boardFilePath(BOARD_FILE_PATH)
                .build();
    }

}
