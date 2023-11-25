package com.example.demo.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionLoggerInterceptor implements HandlerInterceptor {

    private static ThreadLocal<RequestContext> requestParametersThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String uuId = UUID.randomUUID().toString();

        RequestContext requestContext = RequestContext.builder()
                .parameterMap(parameterMap)
                .UUID(uuId)
                .build();

        requestParametersThreadLocal.set(requestContext);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if (ex != null) {

            ObjectMapper objectmapper = new ObjectMapper();

            RequestContext requestContext = requestParametersThreadLocal.get();

            List<String> logs = new ArrayList<>();

            String errorUri = request.getRequestURI();
            logs.add(errorUri);

            String errorMessage = ex.getMessage();
            logs.add(errorMessage);

            Map<String, String[]> parameterMap = requestContext.getParameterMap();

            try {
                String parameter = objectmapper.writeValueAsString(parameterMap);
                logs.add(parameter);
            } catch (JsonProcessingException jsonProcessingException) {
                log.warn("Failed to convert parameterMap to JSON", jsonProcessingException);
                logs.add("JsonProcessingException");
            }

            String uuid = requestContext.getUUID();
            logs.add(uuid);

            logErrorForException(logs);

        }

        requestParametersThreadLocal.remove();

    }

    //    ToDO 나중에 공통으로 쓰일 수 있도록 클래스를 분리한다
    @Data
    @Builder
    @AllArgsConstructor
    private static class RequestContext {
        private String UUID;
        private Map<String, String[]> parameterMap;
    }

    //    ToDO 나중에 공통으로 쓰일 수 있도록 클래스를 분리한다
    private void logErrorForException(Object... args) {
        log.error("exception log = {}", args);
    }

}
