package com.example.demo.common.file.service;

import com.example.demo.common.file.dto.res.FileInfo;
import org.jetbrains.annotations.NotNull;
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

public interface BlogCommonFileService {
    List<FileInfo.FileResult> fileInfoList();

    int insertOrUpdateFile(FileInfo.InsertOrUpdate insertOrUpdate);
    FileInfo.InsertOrUpdate setInsertOrUpdateObject(Object fileObject, String path, Long id);
    String setUploadFilePath(Object fileObject, String bucketFolderPath, Long profileId);

}
