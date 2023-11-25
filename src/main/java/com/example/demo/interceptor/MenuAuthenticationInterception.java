package com.example.demo.interceptor;

import com.example.demo.menu.service.BlogMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@EnableCaching // 어노테이션을 이용한 캐시기능을 사용하겠다는 선언
@RequiredArgsConstructor
public class MenuAuthenticationInterception implements HandlerInterceptor {

    private final BlogMenuService blogMenuService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            blogMenuService.setMenuForModel(modelAndView);
        }
    }

}
