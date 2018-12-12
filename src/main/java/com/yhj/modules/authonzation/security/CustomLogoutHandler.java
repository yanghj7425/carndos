package com.yhj.modules.authonzation.security;


import com.alibaba.fastjson.JSONObject;
import com.yhj.modules.commons.components.CustomConstantInterface;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutHandler implements LogoutHandler, CustomConstantInterface {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
            JSONObject retJson = new JSONObject();
            retJson.put(STATUS_KEY, SUCCESS_CODE);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(retJson.toJSONString());
        } catch (IOException e) {
            return;
        }
    }
}
