package com.yhj.modules.authonzation.security;


import com.alibaba.fastjson.JSONObject;
import com.yhj.modules.commons.components.CustomConstantInterface;
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
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler implements CustomConstantInterface {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        JSONObject clientJson = new JSONObject();
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            RespBean.ok(response, CREDENTIALS_CODE, "密码错误").writeJsonToClient();

            return;
//            clientJson.put(MSG_KEY, "密码错误");
//            clientJson.put(STATUS_KEY, CREDENTIALS_CODE);
        } else if (e instanceof LockedException) {
            clientJson.put(MSG_KEY, "账户被锁定");
            clientJson.put(STATUS_KEY, LOCKED_CODE);
        } else if (e instanceof CredentialsExpiredException) {
            clientJson.put(MSG_KEY, "密码过期，请联系管理员");
            clientJson.put(STATUS_KEY, EXPIRED_PASSWORD_CODE);
        } else if (e instanceof AccountExpiredException) {
            clientJson.put(MSG_KEY, EXPIRED_ACCOUNT_CODE);
            clientJson.put(STATUS_KEY, "账户过期，请联系管理员");
        } else if (e instanceof DisabledException) {
            clientJson.put(MSG_KEY, DISABLE_ACCOUNT_CODE);
            clientJson.put(STATUS_KEY, "账户过期，请联系管理员");
        } else {
            clientJson.put(MSG_KEY, ERROR_CODE);
            clientJson.put(STATUS_KEY, "登录失败");
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(clientJson.toJSONString());
    }
}
