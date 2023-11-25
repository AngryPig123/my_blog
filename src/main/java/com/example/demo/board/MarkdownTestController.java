package com.example.demo.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.parser.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/markdown")
public class MarkdownTestController {

    private final Parser markdownParser;

    @GetMapping
    public String markdownTestController(){
        //  local stack 에서 markdown 파일을 불러 온다.
        //
        return "";
    }

}
