package com.example.demo.common.properties.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BlogServerProperties {

    private final String SERVET_PORT;
    private final String BLOG_SERVER_PREFIX;

    public BlogServerProperties(
            @Value("${server.port}") String SERVET_PORT,
            @Value("${blog.server.prefix}") String BLOG_SERVER_PREFIX
    ) {
        this.SERVET_PORT = SERVET_PORT;
        this.BLOG_SERVER_PREFIX = BLOG_SERVER_PREFIX;
    }

}
