package com.yhj.modules.authonzation.security;

import com.yhj.modules.authonzation.except.CustomAccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * inject CustomLoginFailureHandler Object and redirect exception to it
     */
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        customLoginFailureHandler.onAuthenticationFailure(request, response, new CustomAccessDeniedException(accessDeniedException.getMessage(), accessDeniedException));
    }

    @Autowired
    public void setCustomLoginFailureHandler(CustomLoginFailureHandler customLoginFailureHandler) {
        this.customLoginFailureHandler = customLoginFailureHandler;
    }
}
