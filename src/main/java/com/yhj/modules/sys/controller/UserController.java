package com.yhj.modules.sys.controller;

import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.entitiy.response.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sys")
public class UserController extends BaseController {

    @GetMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("logout");
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("login")
    @ResponseBody
    public RespBean login(@RequestParam("token") String token, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        map.put("roles", list);
        return RespBean.ok("getIndo success", map);
    }

}