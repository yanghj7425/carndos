package com.yhj.modules.authonzation.security;

import com.alibaba.fastjson.JSONObject;
import com.yhj.modules.authonzation.utils.JWTUtils;
import com.yhj.modules.commons.components.CustomConstantInterface;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements CustomConstantInterface {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String userName = ((User) authentication.getPrincipal()).getUsername();
        JSONObject retJson = new JSONObject();
        retJson.put("token", JWTUtils.encoder(userName, 30));
        retJson.put("name", userName);
        retJson.put(STATUS_KEY,SUCCESS_CODE);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(retJson.toJSONString());
    }
}
