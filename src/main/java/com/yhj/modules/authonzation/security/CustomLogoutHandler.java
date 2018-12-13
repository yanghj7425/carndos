package com.yhj.modules.authonzation.security;


import com.alibaba.fastjson.JSONObject;
import com.yhj.modules.commons.components.CustomConstantInterface;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutHandler implements LogoutSuccessHandler, CustomConstantInterface {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
            JSONObject retJson = new JSONObject();
            retJson.put(STATUS_KEY, SUCCESS_CODE);
            retJson.put(MSG_KEY, "");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(retJson.toJSONString());
        } catch (IOException e) {
            return;
        }
    }
}
