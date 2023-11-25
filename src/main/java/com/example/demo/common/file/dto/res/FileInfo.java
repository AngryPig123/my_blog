package com.example.demo.common.file.dto.res;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    private String id;
    private String name;
    private Long size;
    private String mimeType;
    private String extension;
    private String path;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private List<MultipartFile> files;

    public InsertOrUpdate toInsertOrUpdate() {
        return InsertOrUpdate.builder()
                .name(name)
                .size(size)
                .mimeType(mimeType)
                .extension(extension)
                .path(path)
                .userId(userId)
                .build();
    }

    public FileResult toFileResult() {
        return FileResult.builder()
                .name(name)
                .mimeType(mimeType)
                .extension(extension)
                .path(path)
                .build();
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsertOrUpdate {
        private String name;
        private Long size;
        private String mimeType;
        private String extension;
        private String path;
        private Long userId;
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileResult {
        private String name;
        private String mimeType;
        private String extension;
        private String path;
    }

}
