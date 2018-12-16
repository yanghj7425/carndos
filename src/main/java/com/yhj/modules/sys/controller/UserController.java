package com.yhj.modules.sys.controller;

import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sys")
public class UserController extends BaseController {

    @GetMapping("login")
    @ResponseBody
    public Map login() {
        Map<String, Object> map = new HashMap<>();
        List<String> roles = SecurityUtil.getRoles();
        map.put("roles", roles);
        return renderSuccess(map);
    }
}