package com.example.demo.config;

import com.example.demo.common.properties.properties.BlogFileProperties;
import com.example.demo.config.resolver.BlogMultipartResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MultipartResolverConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver(BlogFileProperties blogFileProperties) {
        log.info("blogFileProperties = {}", blogFileProperties);
        return new BlogMultipartResolver(blogFileProperties);
    }

}
