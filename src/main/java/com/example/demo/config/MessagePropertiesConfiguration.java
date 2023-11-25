package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

import static com.example.demo.config.CommonConfigPathService.getMessageProperties;
import static org.testcontainers.shaded.org.apache.commons.lang.CharEncoding.UTF_8;

@Slf4j
@Configuration
public class MessagePropertiesConfiguration {

    private static final String RESOURCE_MESSAGES_PATH = "messages.";

    @Bean
    public MessageSource messageSource() {
        String[] messageProperties = getMessageProperties(RESOURCE_MESSAGES_PATH, "messages");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(messageProperties);
        messageSource.setDefaultEncoding(UTF_8);
        messageSource.setDefaultLocale(Locale.KOREAN);
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

}
