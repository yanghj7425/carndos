package com.yhj.web;

import com.yhj.config.core.RootConfig;
import com.yhj.security.res.CustomSecurityMetadataSource;
import com.yhj.web.service.res.ResRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class SecurityDataTest {
    private static final Logger logger = LoggerFactory.getLogger(SecurityDataTest.class);

    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;


    @Autowired
    private ResRoleService resRoleService;

    @Test
    public void threadOutlogs() {


        resRoleService.threadOutlogs();


    }


    public void testResRoleService() {

        Map<RequestMatcher, Collection<ConfigAttribute>> map = customSecurityMetadataSource.buildRequestMap();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "\t : \t");
            System.out.println(entry.getValue());
        }

    }


    public void customSecurityMetadataSourceTest() {
        System.out.println(customSecurityMetadataSource == null);

    }


}
