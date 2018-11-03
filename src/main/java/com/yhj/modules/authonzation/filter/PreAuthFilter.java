package com.yhj.modules.authonzation.filter;

import com.yhj.modules.authonzation.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        System.out.println("\r\t" + token);
        if (StringUtils.isNotEmpty(token)) {
            String userName = JWTUtils.decoder(token, String.class);
            if (userName == null) {
                return "";
            }
            return userName;
        }
        return null;

    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

        return "";
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(40003);
        System.out.println("认证失败");
    }
}
