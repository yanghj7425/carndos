package com.carndos.modules.sys.user.controller;

import com.carndos.modules.commons.controller.BaseController;
import com.carndos.modules.commons.util.SecurityUtil;
import com.carndos.modules.sys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
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
    public Map userInfo() {
        return renderSuccess("roles", SecurityUtil.getRoles());
    }

    /**
     * lists all system user`s account information. just test method
     *
     * @return map
     */
    @GetMapping("lists")
    public Map queryAllUser() {
        return renderSuccess("users", userService.queryAllUser());
    }


}