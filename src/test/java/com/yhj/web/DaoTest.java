package com.yhj.web;

import com.yhj.config.core.RootConfig;
import com.yhj.web.controller.security.CustomSuccessHandler;
import com.yhj.web.dao.res.CustomAccessDecisionManager;
import com.yhj.web.dao.res.JdbcRequestMapBuilder;
import com.yhj.web.dao.res.ResRoleMapper;
import com.yhj.web.dao.res.ResourceMapper;
import com.yhj.web.entity.res.ResRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class DaoTest {

    @Autowired
    CustomSuccessHandler customSuccessHandler;


    @Autowired
    JdbcRequestMapBuilder builder;


    @Autowired
    CustomAccessDecisionManager customAccessDecisionManager;


    @Autowired
    ResRoleMapper resRoleMapper;


    @Autowired
    ResourceMapper resourceMapper;

    @Test
    public void test() {
        System.out.println("s");

        if (resRoleMapper == null) {
            System.out.println("null");
        }
        List<ResRole> list = resRoleMapper.queryAllResRole();
        for (ResRole resRole : list) {
            System.out.println(resRole);
        }
    }


}
