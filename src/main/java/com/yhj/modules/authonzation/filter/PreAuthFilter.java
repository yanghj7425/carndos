package com.yhj.modules.authonzation.filter;

import com.yhj.modules.authonzation.utils.JWTUtils;
import com.yhj.modules.commons.entitiy.response.RespBean;
import com.yhj.modules.commons.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        Object  obj = SecurityUtil.getAuthentication();
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
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        RespBean.error(response,"token 认证失败").writeToClient();
    }
}
