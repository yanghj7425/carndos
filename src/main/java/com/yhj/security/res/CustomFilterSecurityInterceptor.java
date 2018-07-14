package com.yhj.security.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;


@Component(value = "filterSecurityInterceptor")
public class CustomFilterSecurityInterceptor extends FilterSecurityInterceptor {



    @Autowired
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Autowired
    public void setSecurityMetadataSource(CustomSecurityMetadataSource securityMetadataSource) {
        super.setSecurityMetadataSource(securityMetadataSource);
    }
}
