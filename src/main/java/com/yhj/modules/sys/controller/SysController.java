package com.yhj.modules.sys.controller;

import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.util.SecurityUtil;
import com.yhj.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("sys")
public class SysController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("userInfo")
    @ResponseBody
    public Map userInfo() {
        return renderSuccess("roles", SecurityUtil.getRoles());
    }


    @GetMapping("users")
    @ResponseBody
    public Map queryAllUser() {
        return renderSuccess("users", sysUserService.queryAllUser());
    }


}