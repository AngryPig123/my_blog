package com.example.demo.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String name;
    private String grade;
    private Long id;
    private String email;
}
