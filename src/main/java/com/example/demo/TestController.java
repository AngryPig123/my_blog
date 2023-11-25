package com.example.demo;


import com.example.demo.common.file.dto.res.FileInfo;
import com.example.demo.common.file.service.BlogCommonFileService;
import com.example.demo.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/profile")
public class TestController {

    private final UserProfileService userProfileService;
    private final BlogCommonFileService blogCommonFileService;

    @ResponseBody
    @PostMapping(path = "/image/upload/{profileId}")
    public String fileUploadTest(
            @PathVariable("profileId") Long profileId,
            @RequestParam("uploadFile") MultipartFile uploadFile
    ) {
        boolean b = userProfileService.userProfileImageUpload(uploadFile, profileId);
        return "ok";
    }

    @GetMapping(path = "/image/list")
    public String imageLoadTest(Model model) {

        List<FileInfo.FileResult> fileResults = blogCommonFileService.fileInfoList();

        model.addAttribute("fileList", fileResults);

        return "/file/list";
    }


}














