package com.example.demo.board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardContentVariable {

    private Long userId;
    private Long boardId;
    private Long contentId;

    public UserBoardVariable toUserBoardVariable() {
        return UserBoardVariable.builder()
                .userId(userId)
                .boardId(boardId)
                .build();
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserBoardVariable {
        private Long userId;
        private Long boardId;
    }

}
