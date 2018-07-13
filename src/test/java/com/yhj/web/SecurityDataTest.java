package com.yhj.web;

import com.yhj.config.core.RootConfig;
import com.yhj.security.CustomSecurityMetadataSource;
import com.yhj.web.service.res.ResRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class SecurityDataTest {


    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;


    @Autowired
    private ResRoleService resRoleService;


    public void testResRoleService() {

        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> map = resRoleService.buildRequestMap();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }


    @Test
    public void customSecurityMetadataSourceTest() {
        System.out.println(customSecurityMetadataSource == null);

    }


}
