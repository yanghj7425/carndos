package com.carndos.modules;

import com.carndos.config.core.RootConfig;
import com.carndos.modules.authentication.security.CustomSecurityMetadataSource;
import com.carndos.modules.sys.res.service.ResRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class SecurityDataTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;


    @Autowired
    private ResRoleService resRoleService;

    @Test
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
