package com.example.demo.board.servier;

import com.example.demo.board.dto.UserBoardContentVariable;
import com.example.demo.common.file.dto.res.FileInfo;
import com.example.demo.common.file.mapper.BlogCommonFileMapper;
import com.example.demo.common.file.service.BlogCommonFileService;
import com.example.demo.common.file.service.s3.S3Service;
import com.example.demo.common.properties.properties.BlogAwsProperties;
import com.example.demo.requestholder.RequestHolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserBoardServiceImpl implements UserBoardService {

    private final S3Service s3Service;
    private final BlogAwsProperties blogAwsProperties;
    private final BlogCommonFileService blogCommonFileService;
    private final RequestHolderService requestHolderService;

    // ToDO 해당 부분에서 s3 upload, db insert 부분에 트랜잭션 처리가 필요하다. try catch로 롤백 시킨다.
    @Override
    @Transactional
    public boolean uploadUserBoard(UserBoardContentVariable.UserBoardVariable userBoardVariable, MultipartFile uploadFile) {
        BlogAwsProperties.BlogAwsS3ConfigProperties blogAwsS3Properties = blogAwsProperties.getBlogAwsS3Properties();
        String bucketName = blogAwsS3Properties.getIntegrated();
        //  ToDO 추후 유저의 세션 정보셋팅후 다시 테스트해 본다.
        Long userId = requestHolderService.getUserInfoFromSession().getId();
        StringBuilder imagePath = this.setImagePathToUserBoardRequest(userBoardVariable);
        s3Service.s3FolderInit(imagePath, bucketName);
        String realPath = blogCommonFileService.setUploadFilePath(uploadFile, bucketName, userId);
        FileInfo.InsertOrUpdate insertOrUpdateFileObject = blogCommonFileService.setInsertOrUpdateObject(uploadFile, realPath, userId);
        blogCommonFileService.insertOrUpdateFile(insertOrUpdateFileObject);
        s3Service.uploadMultipartFile(uploadFile, bucketName, realPath);
        return false;
    }

    @NotNull
    private StringBuilder setImagePathToUserBoardRequest(UserBoardContentVariable.UserBoardVariable userBoardVariable) {
        return new StringBuilder("board/")
                .append(userBoardVariable.getBoardId())
                .append("/")
                .append("user/")
                .append(userBoardVariable.getUserId());

    }

}
