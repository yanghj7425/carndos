package com.carndos.modules.user.controller;

import com.carndos.modules.commons.controller.BaseController;
import com.carndos.modules.commons.util.SecurityUtil;
import com.carndos.modules.commons.pojo.PageParam;
import com.carndos.modules.user.entity.SysUser;
import com.carndos.modules.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public Map queryAllUser(HttpServletRequest request) {

        PageParam pageParam = PageParam.builder(request).build();

        PageInfo<SysUser> sysUserPageInfo = userService.queryForPage(pageParam);

        return renderSuccess("users", sysUserPageInfo);


//        return renderSuccess("users", queryForPage(pageParam, map -> userService.queryAllUser()));
    }


}