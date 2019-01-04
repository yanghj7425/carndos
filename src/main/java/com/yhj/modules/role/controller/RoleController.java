package com.yhj.modules.role.controller;

import com.alibaba.fastjson.JSON;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.role.entity.SysRole;
import com.yhj.modules.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * list all system`s roles information. just test method
     *
     * @return map
     */
    @GetMapping("lists")
    @ResponseBody
    public Map querySysRoles() {
        List<SysRole> roles = roleService.querySysRoles();
        return renderSuccess("roles", JSON.toJSON(roles));
    }
}
