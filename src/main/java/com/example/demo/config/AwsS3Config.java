package com.example.demo.config;

import com.example.demo.common.properties.properties.BlogAwsProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class AwsS3Config {

    @Bean("s3Client")
    @Profile("local")
    public S3Client s3ClientLocal(
            @Qualifier("blogAwsProperties") BlogAwsProperties blogAwsProperties
    ) {
        BlogAwsProperties.BlogAwsConfigProperties blogAwsConfigProperties = blogAwsProperties.getBlogAwsConfigProperties();
        BlogAwsProperties.BlogAwsS3ConfigProperties blogAwsS3Properties = blogAwsProperties.getBlogAwsS3Properties();
        return S3Client.builder()
                .region(Region.of(blogAwsConfigProperties.getRegion()))
                .credentialsProvider(() -> AwsBasicCredentials.create(blogAwsConfigProperties.getAwsCredentialsAccessKey(), blogAwsConfigProperties.getAwsCredentialsSecretKey()))
                .endpointOverride(URI.create(blogAwsS3Properties.getS3EndPoint()))
                .build();
    }

    @Bean("s3Client")
    @Profile("develop")
    public S3Client s3ClientDevelop(
            @Qualifier("blogAwsProperties") BlogAwsProperties blogAwsProperties
    ) {
        BlogAwsProperties.BlogAwsConfigProperties blogAwsConfigProperties = blogAwsProperties.getBlogAwsConfigProperties();
        return S3Client.builder()
                .region(Region.of(blogAwsConfigProperties.getRegion()))
                .credentialsProvider(() -> AwsBasicCredentials.create(blogAwsConfigProperties.getAwsCredentialsAccessKey(), blogAwsConfigProperties.getAwsCredentialsSecretKey()))
                .build();
    }

}
