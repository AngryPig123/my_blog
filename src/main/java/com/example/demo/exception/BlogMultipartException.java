package com.example.demo.exception;

import com.example.demo.advice.AdviceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class BlogMultipartException extends RuntimeException {

    private String requestUrl;
    private String extension;
    private Long size;

    @Builder
    public BlogMultipartException(
            String requestUrl,
            String extension,
            Long size
    ) {
        this.requestUrl = requestUrl;
        this.extension = extension;
        this.size = size;
    }

    public AdviceResponse toFileException() {
        return AdviceResponse.builder()
                .requestUrl(requestUrl)
                .extension(extension)
                .size(size)
                .build();
    }

}
