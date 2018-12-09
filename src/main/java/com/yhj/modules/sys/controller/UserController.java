package com.yhj.modules.sys.controller;

import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.entitiy.response.RespBean;
import com.yhj.modules.commons.util.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("sys")
public class UserController extends BaseController {

    @GetMapping("login")
    @ResponseBody
    public RespBean login(@RequestParam("token") String token, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        List<String> roles = SecurityUtil.getRoles();
        map.put("roles", roles);
        return RespBean.ok("getIndo success", map);
    }
}