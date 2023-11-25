package com.example.demo.home;

import com.example.demo.menu.service.BlogMenuService;
import com.example.demo.requestholder.RequestHolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {

    private final BlogMenuService blogMenuService;
    private final RequestHolderService requestHolderService;

    @GetMapping
    public String home(Model model) {
        return "/home";
    }

}
