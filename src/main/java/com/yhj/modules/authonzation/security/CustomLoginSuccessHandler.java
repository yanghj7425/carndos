package com.yhj.modules.authonzation.security;

import com.alibaba.fastjson.JSONObject;
import com.yhj.modules.commons.entitiy.response.RespBean;
import com.yhj.modules.authonzation.utils.JWTUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Object o = authentication.getCredentials();
        String userName = ((User) authentication.getPrincipal()).getUsername();
        Map<String,Object> authInfo = new HashMap<>();
        authInfo.put("token", JWTUtils.sign(userName,30));
        authInfo.put("roles", authentication.getAuthorities());
        authInfo.put("name", userName);
        RespBean respBean = RespBean.ok("登录成功!", authInfo);
        PrintWriter out = response.getWriter();

        out.write(JSONObject.toJSONString(respBean));
        out.flush();
        out.close();
    }
}
