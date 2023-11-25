package com.example.demo.common.file.mapper;

import com.example.demo.common.file.dto.res.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogCommonFileMapper {
    int insertOrUpdateFile(FileInfo.InsertOrUpdate insertOrUpdate);

    List<FileInfo.FileResult> fileInfoList(String filePrefix);

}
