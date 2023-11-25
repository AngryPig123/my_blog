package com.example.demo.board.servier;

import com.example.demo.board.dto.UserBoardContentVariable;
import org.springframework.web.multipart.MultipartFile;

public interface UserBoardService {
    boolean uploadUserBoard(UserBoardContentVariable.UserBoardVariable userBoardVariable, MultipartFile uploadFile);
}
