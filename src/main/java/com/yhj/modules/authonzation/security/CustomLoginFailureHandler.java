package com.yhj.modules.authonzation.security;


import com.yhj.modules.commons.entitiy.response.RespBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        RespBean respBean;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            respBean = RespBean.error(response, "账户名或者密码输入错误!");
        } else if (e instanceof LockedException) {
            respBean = RespBean.error(response, "账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            respBean = RespBean.error(response, "密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            respBean = RespBean.error(response, "账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            respBean = RespBean.error(response, "账户被禁用，请联系管理员!");
        } else {
            respBean = RespBean.error(response, "登录失败!");
        }
        respBean.writeToClient();
    }
}
