package com.yhj.modules.user.controller;

import com.alibaba.fastjson.JSON;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.util.SecurityUtil;
import com.yhj.modules.user.entity.SysRole;
import com.yhj.modules.user.service.SysRoleService;
import com.yhj.modules.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("info")
    @ResponseBody
    public Map userInfo() {
        return renderSuccess("roles", SecurityUtil.getRoles());
    }


    @GetMapping("lists")
    @ResponseBody
    public Map queryAllUser() {
        return renderSuccess("users", sysUserService.queryAllUser());
    }

    @GetMapping("roles")
    @ResponseBody
    public Map querySysRoles() {
        List<SysRole> roles = sysRoleService.querySysRoles();
        return renderSuccess("roles", JSON.toJSON(roles));
    }

    @RequestMapping("login")
    public Map login() {
        System.out.println("login failure");
        return renderSuccess();
    }
}