package com.example.demo.config;

import org.junit.jupiter.api.Test;

import java.io.File;

class BlogApplicationRunnerDevTest {

    private final String PROFILE_IMAGE_PATH = "profile/image/upload";

    @Test
    void test() {

        String[] folders = PROFILE_IMAGE_PATH.split("/");
        StringBuilder currentPath = new StringBuilder();

        for (int i = 0; i < folders.length; i++) {
            if (i != folders.length - 1) {
                currentPath.append(folders[i]).append("/");
                System.out.println(currentPath.toString());
            } else {
                currentPath.append(folders[i]);
                System.out.println(currentPath.toString());
            }
        }

    }

}