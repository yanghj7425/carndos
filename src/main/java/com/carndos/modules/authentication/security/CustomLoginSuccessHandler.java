package com.carndos.modules.authentication.security;

import cn.hutool.core.collection.CollectionUtil;
import com.carndos.modules.authentication.utils.JWTUtils;
import com.carndos.modules.commons.entitiy.response.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String userName = ((User) authentication.getPrincipal()).getUsername();
        Map<String, Object> tokenMap = CollectionUtil.newHashMap();
        tokenMap.put("token", JWTUtils.encoder(userName, 30));
        RespBean.ok(response, tokenMap).writeJsonToClient();
    }
}
