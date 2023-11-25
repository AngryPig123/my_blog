package com.example.demo.user.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UserProfileService {

    boolean defaultUserProfileImageUpload(File file);

    boolean userProfileImageUpload(MultipartFile multipartFile, Long profileId);

}
