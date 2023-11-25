package com.example.demo.interceptor;

import com.example.demo.common.properties.properties.BlogLocalTestProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.demo.requestholder.SessionType.USER_SESSION;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserSessionMockingInterceptor implements HandlerInterceptor {

    private final BlogLocalTestProperties blogLocalTestProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute(USER_SESSION.getSessionName(), blogLocalTestProperties.toUserInfo());
        return true;
    }

}
