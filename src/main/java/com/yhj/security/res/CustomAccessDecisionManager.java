package com.yhj.security.res;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 授权管理器
 */
@Component(value = "accessDecisionManager")
public class CustomAccessDecisionManager implements AccessDecisionManager {


    private final Logger log = LoggerFactory.getLogger(getClass());

    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
        if (null == configAttributes) {
            return;
        }

        if (configAttributes.isEmpty()) {
            return;
        }

        //所请求的资源拥有的权限(一个资源对多个权限)
        for (ConfigAttribute configAttribute : configAttributes) {
            //访问所请求的资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            log.info("message {}", object);

            //用户所拥有的权限authentication
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority ga : authorities) {
                if (needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //没有权限
        throw new AccessDeniedException("没有权限访问！");

    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}
