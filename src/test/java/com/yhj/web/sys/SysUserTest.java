package com.yhj.web.sys;

import com.yhj.config.core.RootConfig;
import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.service.sys.SysUserService;
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
    private SysUserService sysUserService;



    public void userServiceTest() {
        List<SysUser> list = sysUserService.queryAll();


        for (SysUser user : list) {
            System.out.println(user.getUserCreateTime());
        }


        SysUser u = sysUserService.queryById(1L);
        System.out.println(u.getUserName());
    }


    @Test
    public void insertUserTest() {
        SysUser user = new SysUser();

        user.setUserDesc("insssss");


        sysUserService.insert(user);
    }
}
