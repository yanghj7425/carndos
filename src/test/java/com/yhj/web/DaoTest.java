package com.yhj.web;

import com.yhj.config.core.RootConfig;
import com.yhj.web.controller.security.CustomSuccessHandler;
import com.yhj.web.dao.res.JdbcRequestMapBuilder;
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

    @Test
    public void test() {
        List<ResRole> list = builder.queryResourceData();

        System.out.println(list);
    }


}
