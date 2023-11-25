package com.example.demo.config;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
class MessagePropertiesConfigurationTest {

    @Autowired
    MessageSource messageSource;

    @Test
    @Order(1)
    void message_kr_test(){
        System.out.println(messageSource.getMessage("message.kr.test",null, Locale.getDefault()));
    }

    @Test
    @Order(2)
    void error_kr_test(){
        System.out.println(messageSource.getMessage("error.kr.test",null, Locale.getDefault()));
    }



}