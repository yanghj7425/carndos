package com.yhj.modules.authonzation.filter;

import com.alibaba.fastjson.JSONObject;
import com.yhj.modules.authonzation.utils.JWTUtils;
import com.yhj.modules.commons.components.CustomConstantInterface;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter implements CustomConstantInterface {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
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
        JSONObject clientJson = new JSONObject();
        clientJson.put(STATUS_KEY,TOKEN_AUTH_UNSUCCESS_CODE);
        clientJson.put(MSG_KEY,"token 认证失败");
        response.setContentType(RESPONSE_CONTENT_TYPE_HEADER);


    }
}
