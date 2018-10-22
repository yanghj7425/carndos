package com.yhj.security.login;

import com.alibaba.fastjson.JSONObject;
import com.yhj.security.utils.SecurityUtil;
import com.yhj.web.bean.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
       response.setContentType("application/json;charset=utf-8");

        RespBean respBean = RespBean.ok("登录成功!",  SecurityUtil.getPrincipal());
        PrintWriter out = response.getWriter();

        out.write(JSONObject.toJSONString(respBean));
        out.flush();
        out.close();
    }
}
