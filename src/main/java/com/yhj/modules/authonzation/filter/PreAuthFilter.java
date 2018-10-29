package com.yhj.modules.authonzation.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        System.out.println(token);
        if (!StringUtils.isNotEmpty(token)) {
            // 可以通过request获取当前认证过的用户名，比如通过参数、HTTP请求头或者Cookie获取token，再通过token调用第三方接口获取用户名
            return null;
        }
        return "admin";

    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

        System.out.println("*****************************************************");
        return "";
    }
}
