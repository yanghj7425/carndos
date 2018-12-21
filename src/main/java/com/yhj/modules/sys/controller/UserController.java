package com.yhj.modules.sys.controller;

import com.google.common.collect.Maps;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.util.SecurityUtil;
import com.yhj.modules.sys.dao.SysUserMapper;
import com.yhj.modules.sys.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sys")
public class UserController extends BaseController {

    @Resource
    private SysUserMapper userMapper;

    @GetMapping("userInfo")
    @ResponseBody
    public Map userInfo() {
        Map<String, Object> map = Maps.newHashMap();
        List<String> roles = SecurityUtil.getRoles();
        map.put("roles", roles);
        return renderSuccess(map);
    }


    @GetMapping("users")
    @ResponseBody
    public Map queryAllUser() {
        Map<String, Object> map = Maps.newHashMap();
        List<SysUser> users = userMapper.selectAll();
        map.put("users", users);
        return renderSuccess(map);
    }


}