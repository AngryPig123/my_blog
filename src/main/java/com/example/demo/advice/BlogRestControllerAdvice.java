package com.example.demo.advice;

import com.example.demo.exception.BlogMultipartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BlogRestControllerAdvice {

    @ExceptionHandler(BlogMultipartException.class)
    public ResponseEntity<AdviceResponse> blogMultipartExceptionHandler(
            BlogMultipartException blogMultipartException
    ) {
        return ResponseEntity.badRequest().body(blogMultipartException.toFileException());
    }

}
