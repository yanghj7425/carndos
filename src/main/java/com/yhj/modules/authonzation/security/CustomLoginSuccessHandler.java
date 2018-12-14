package com.yhj.modules.authonzation.security;

import com.google.common.collect.Maps;
import com.yhj.modules.authonzation.utils.JWTUtils;
import com.yhj.modules.commons.components.CustomConstantInterface;
import com.yhj.modules.commons.entitiy.response.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements CustomConstantInterface {

    /**
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String userName = ((User) authentication.getPrincipal()).getUsername();
        Map<String, Object> retJson = Maps.newHashMap();
        retJson.put("token", JWTUtils.encoder(userName, 30));
        retJson.put(STATUS_KEY, SUCCESS_CODE);
        RespBean.ok(response,retJson).writeJsonToClient();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(retJson);
    }
}
