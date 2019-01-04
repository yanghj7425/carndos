package com.yhj.modules.user.controller;

import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.util.SecurityUtil;
import com.yhj.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * query current have login user information. now just return user`s roles info
     *
     * @return map
     */
    @GetMapping("info")
    @ResponseBody
    public Map userInfo() {
        return renderSuccess("roles", SecurityUtil.getRoles());
    }

    /**
     * lists all system user`s account information. just test method
     *
     * @return map
     */
    @GetMapping("lists")
    @ResponseBody
    public Map queryAllUser() {
        return renderSuccess("users", userService.queryAllUser());
    }


}