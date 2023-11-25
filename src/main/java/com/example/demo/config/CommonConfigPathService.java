package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
class CommonConfigPathService {

    static String[] getMessageProperties(String resourcePrefix, String resourceMessageFolderPath) {
        try {
            String path = new ClassPathResource(resourceMessageFolderPath).getFile().getAbsolutePath();
            File folder = new File(path);
            try (Stream<String> files = Arrays.stream(Objects.requireNonNull(folder.list()))) {
                return files.map(file -> resourcePrefix + file.split("\\.")[0]).toArray(String[]::new);
            }
        } catch (IOException e) {
            log.error("message properties 설정 실패", e);
            return new String[0];
        }
    }

    static String[] getStaticResources(String resourcePrefix) {
        try {
            String path = new ClassPathResource(resourcePrefix).getFile().getAbsolutePath();
            File folder = new File(path);
            String[] list = folder.list();
            for (int i = 0; i < Objects.requireNonNull(list).length; i++) {
                list[i] = "/" + list[i] + "/**";
                log.info("static resources = {}", list[i]);
            }
            return list;
        } catch (IOException ioException) {
            log.error("static resource 제외 실패", ioException);
            throw new RuntimeException();
        }
    }

}
