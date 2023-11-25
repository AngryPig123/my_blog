package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.commonmark.parser.Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MarkdownConfig {

    @Bean("markdownParser")
    public Parser markdownParser() {
        return Parser.builder().build();
    }

}
