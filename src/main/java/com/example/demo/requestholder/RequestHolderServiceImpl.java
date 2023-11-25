package com.example.demo.requestholder;

import com.example.demo.user.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.example.demo.requestholder.SessionType.USER_SESSION;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestHolderServiceImpl implements RequestHolderService {

    @Override
    public HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("requestAttributes = {}", requestAttributes);
        return Objects.requireNonNull(requestAttributes).getRequest();
    }

    @Override
    public UserInfo getUserInfoFromSession() {
        return (UserInfo) this.getSession().getAttribute(USER_SESSION.getSessionName());
    }

    private HttpSession getSession() {
        return this.getHttpServletRequest().getSession();
    }


}
