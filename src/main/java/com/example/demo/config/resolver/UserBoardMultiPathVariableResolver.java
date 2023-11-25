package com.example.demo.config.resolver;

import com.example.demo.annotation.UserBoardMultiPathVariable;
import com.example.demo.board.dto.UserBoardContentVariable;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public class UserBoardMultiPathVariableResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserBoardMultiPathVariable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        UserBoardContentVariable dto = new UserBoardContentVariable();
        Map<String, String> pathVariables = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        try {

            String userId = pathVariables.get("userId");
            String boardId = pathVariables.get("boardId");
            String contentId = pathVariables.get("contentId");

            dto.setUserId(Long.valueOf(userId));
            dto.setBoardId(Long.valueOf(boardId));
            dto.setContentId(Long.valueOf(contentId));

            return dto;

        } catch (NumberFormatException numberFormatException) {
            //  ToDo
            return dto.toUserBoardVariable();
        }
    }

}
