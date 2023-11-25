package com.example.demo.board;

import com.example.demo.annotation.UserBoardMultiPathVariable;
import com.example.demo.board.dto.UserBoardContentVariable;
import com.example.demo.board.servier.UserBoardService;
import com.example.demo.common.file.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user/{userId}/board/{boardId}")
public class BoardController {

    private final UserBoardService userBoardService;

    @GetMapping(path = "/content/{contentId}")
    public String findUserBoardContent(@UserBoardMultiPathVariable UserBoardContentVariable userBoardContentVariable) {

        log.info("pathVariableMap = {}", userBoardContentVariable);
        //  파일이 업로드 될떄 해당 폴더가 존재하는지 체크하고 파일을 upload하는 로직 필요.
        //  board md file upload

        return "ok";
    }

    @PostMapping
    public String uploadUserBoardContent(
            @UserBoardMultiPathVariable UserBoardContentVariable.UserBoardVariable userBoardVariable,
            @Param("uploadFile") MultipartFile uploadFile
    ) {

        //  ToDO response 메세지를 공통화 시킨다.
        userBoardService.uploadUserBoard(userBoardVariable, uploadFile);

        return "ok";
    }

}
