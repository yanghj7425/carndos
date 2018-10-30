package com.yhj.modules.authonzation.filter;

import com.yhj.modules.authonzation.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        System.out.println(token);
        if (StringUtils.isNotEmpty(token)) {
            String userName = JWTUtils.decoder(token, String.class);

            return userName;
        }
        return null;

    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

        return "";
    }
}
