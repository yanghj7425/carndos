package com.yhj.modules.authonzation.filter;

import com.yhj.modules.authonzation.except.InvalidTokenException;
import com.yhj.modules.authonzation.utils.JWTUtils;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {


    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) throws InvalidTokenException {
        String token = request.getHeader("X-Token");
        if (token == null) {
            return null;
        }
        String userName = JWTUtils.decoder(token, String.class);
        if (userName == null) {
            throw new InvalidTokenException("非法的 Token");
        }
        return userName;
    }

    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "";
    }


}
