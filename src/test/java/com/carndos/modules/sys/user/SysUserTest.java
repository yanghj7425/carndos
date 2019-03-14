package com.carndos.modules.sys.user;

import com.alibaba.fastjson.JSON;
import com.carndos.config.core.RootConfig;
import com.carndos.modules.sys.role.entity.SysRole;
import com.carndos.modules.sys.user.entity.SysUser;
import com.carndos.modules.sys.user.entity.SysUserRole;
import com.carndos.modules.sys.role.service.RoleService;
import com.carndos.modules.sys.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class SysUserTest {

    @Autowired
    private UserService sysUserService;


    @Autowired
    private RoleService sysRoleService;

    @Test
    public void queryRolesTest() {
        List<SysRole> roles = sysRoleService.querySysRoles();
        System.out.println(JSON.toJSON(roles));
    }


    public void sysUserRoleServiceTest() {
        SysUser sysUser = new SysUser();


        SysUser user = sysUserService.querySysUserByName("admin");
        List<SysUserRole> roles = user.getRoles();
        for (SysUserRole role : roles) {
            System.out.println(role);
        }

    }


    public void userServiceTest() {
        List<SysUser> list = sysUserService.queryAll();


        for (SysUser user : list) {
            System.out.println(user.getUserCreateTime());
        }
        SysUser u = sysUserService.queryById(1L);
        System.out.println(u.getUserName());

        u = sysUserService.querySysUserByName("user");
        System.out.println(u.getUserDesc());
    }


    public void insertUserTest() {
        SysUser user = new SysUser();

        user.setUserDesc("insssss");

        sysUserService.insert(user);
    }
}
