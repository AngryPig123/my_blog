package com.example.demo.common.properties.properties;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogFileProperties {

    private final Long MAXIMUM_FILE_SIZE;
    private final Long MINIMUM_FILE_SIZE;
    private final String IMAGE_EXTENSION;

    public BlogFileProperties(
            @Value("${file.validate.size.max}") Long MAXIMUM_FILE_SIZE,
            @Value("${file.validate.size.min}") Long MINIMUM_FILE_SIZE,
            @Value("${file.validate.extension.image}") String IMAGE_EXTENSION
    ) {
        this.MAXIMUM_FILE_SIZE = MAXIMUM_FILE_SIZE;
        this.MINIMUM_FILE_SIZE = MINIMUM_FILE_SIZE;
        this.IMAGE_EXTENSION = IMAGE_EXTENSION;
    }

    public BlogFileValidateProperties getBlogFileValidateProperties() {
        List<String> imageFileExtension = List.of(IMAGE_EXTENSION.split(","));
        return BlogFileValidateProperties.builder()
                .maximumFileSize(MAXIMUM_FILE_SIZE)
                .minimumFileSize(MINIMUM_FILE_SIZE)
                .imageFileExtension(imageFileExtension)
                .build();
    }

    @Getter
    @Builder
    @ToString
    @RequiredArgsConstructor
    public static class BlogFileValidateProperties {
        private final Long maximumFileSize;
        private final Long minimumFileSize;
        private final List<String> imageFileExtension;
    }

}
