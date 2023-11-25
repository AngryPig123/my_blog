package com.example.demo.requestholder;

import com.example.demo.user.dto.UserInfo;

import javax.servlet.http.HttpServletRequest;

public interface RequestHolderService {
    HttpServletRequest getHttpServletRequest();
    UserInfo getUserInfoFromSession();
}
