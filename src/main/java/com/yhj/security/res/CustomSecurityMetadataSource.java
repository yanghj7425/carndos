package com.yhj.security.res;

import com.yhj.web.service.res.ResRoleService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component(value = "securityMetadataSource")
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {


    private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = null;


    //资源权限集合
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    //查找数据库权限和资源的关系
    @Autowired
    private ResRoleService resRoleService;

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Collection<ConfigAttribute> atts = NULL_CONFIG_ATTRIBUTE;
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                atts = entry.getValue();
                break;
            }
        }
        return atts;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            attributes.addAll(entry.getValue());
        }
        System.out.println("总共有这些权限：" + attributes.toString());
        return attributes;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


    //绑定requestMap
    protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {

        return resRoleService.buildRequestMap();
    }

    public void afterPropertiesSet() {
        this.requestMap = bindRequestMap();
    }


}
