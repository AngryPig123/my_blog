package com.example.demo.common.properties.properties;

import com.example.demo.user.dto.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogLocalTestProperties {

    private final String INIT_USER_NAME;
    private final String INIT_USER_GRADE;
    private final String INIT_USER_EMAIL;
    private final Long INIT_USER_ID;

    public BlogLocalTestProperties(
            @Value("${init.user.name}") String name,
            @Value("${init.user.grade}") String grade,
            @Value("${init.user.email}") String email,
            @Value("${init.user.id}") Long id
    ) {
        this.INIT_USER_NAME = name;
        this.INIT_USER_GRADE = grade;
        this.INIT_USER_EMAIL = email;
        this.INIT_USER_ID = id;
    }

    public UserInfo toUserInfo() {
        return UserInfo.builder()
                .name(INIT_USER_NAME)
                .grade(INIT_USER_GRADE)
                .email(INIT_USER_EMAIL)
                .id(INIT_USER_ID)
                .build();
    }

}
