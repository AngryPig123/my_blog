package com.example.demo.config;

import com.example.demo.config.resolver.UserBoardMultiPathVariableResolver;
import com.example.demo.interceptor.ExceptionLoggerInterceptor;
import com.example.demo.interceptor.MenuAuthenticationInterception;
import com.example.demo.interceptor.UserSessionMockingInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.example.demo.config.CommonConfigPathService.getStaticResources;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final ExceptionLoggerInterceptor exceptionLoggerInterceptor;
    private final UserSessionMockingInterceptor userSessionMockingInterceptor;
    private final MenuAuthenticationInterception menuAuthenticationInterception;
    private static final String STATIC = "static";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] staticResources = getStaticResources(STATIC);

        registry.addInterceptor(exceptionLoggerInterceptor)
                .excludePathPatterns(staticResources);

        registry.addInterceptor(userSessionMockingInterceptor)
                .excludePathPatterns(staticResources);

        registry.addInterceptor(menuAuthenticationInterception)
                .excludePathPatterns(staticResources);

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserBoardMultiPathVariableResolver());
    }


}
