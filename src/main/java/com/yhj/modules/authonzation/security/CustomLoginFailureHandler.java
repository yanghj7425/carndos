package com.yhj.modules.authonzation.security;


import com.yhj.modules.authonzation.except.InvalidTokenException;
import com.yhj.modules.commons.components.CustomFinalConstant;
import com.yhj.modules.commons.entitiy.response.RespBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        RespBean respBean;
        response.setStatus(HttpServletResponse.SC_OK);
        if (e instanceof BadCredentialsException) {
            respBean = RespBean.error(response, CustomFinalConstant.CREDENTIALS_CODE, "验证错误");
        } else if (e instanceof UsernameNotFoundException) {
            respBean = RespBean.error(response, CustomFinalConstant.CREDENTIALS_CODE, "用户不存在");
        } else if (e instanceof LockedException) {
            respBean = RespBean.error(response, CustomFinalConstant.LOCKED_CODE, "账户被锁定");
        } else if (e instanceof CredentialsExpiredException) {
            respBean = RespBean.error(response, CustomFinalConstant.EXPIRED_PASSWORD_CODE, "密码过期，请联系管理员");
        } else if (e instanceof AccountExpiredException) {
            respBean = RespBean.error(response, CustomFinalConstant.EXPIRED_ACCOUNT_CODE, "账户过期，请联系管理员");
        } else if (e instanceof DisabledException) {
            respBean = RespBean.error(response, CustomFinalConstant.DISABLE_ACCOUNT_CODE, "账户被禁用，请联系管理员");
        } else if (e instanceof InvalidTokenException) {
            respBean = RespBean.error(response, CustomFinalConstant.TOKEN_AUTH_FAIL_CODE, e.getMessage());
        } else {
            respBean = RespBean.error(response, CustomFinalConstant.ERROR_CODE, "登陆失败");
        }
        respBean.writeJsonToClient();
    }
}
