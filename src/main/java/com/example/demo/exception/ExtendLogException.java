package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtendLogException extends RuntimeException {
    public ExtendLogException(String message) {
        log.error("error = {}", message);
    }

}
