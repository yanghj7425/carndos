package com.yhj.web;

import com.yhj.config.core.RootConfig;
import com.yhj.web.controller.security.CustomSuccessHandler;
import com.yhj.web.dao.res.CustomAccessDecisionManager;
import com.yhj.web.dao.res.JdbcRequestMapBuilder;
import com.yhj.web.dao.res.ResRoleMapper;
import com.yhj.web.dao.res.ResourceMapper;
import com.yhj.web.entity.res.ResRole;
import com.yhj.web.entity.res.Resource;
import com.yhj.web.service.res.ResRoleService;
import com.yhj.web.service.res.impl.ResRoleServiceI;
import com.yhj.web.service.res.impl.ResourceServiceI;
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
    ResRoleService resRoleService;


    @Autowired
    ResourceServiceI resourceService;

    @Test
    public void test() {
        System.out.println("s");
        List<ResRole> list = resRoleService.selectAll();
        for (ResRole resRole : list){
            System.out.println(resRole);
        }


    }


}
