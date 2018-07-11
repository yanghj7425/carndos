package com.yhj.web.controller.security;

import com.yhj.web.dao.res.JdbcRequestMapBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {


    private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = null;


    //资源权限集合
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    //查找数据库权限和资源的关系
    private JdbcRequestMapBuilder builder;

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

        return builder.buildRequestMap();
    }

    public void afterPropertiesSet() throws Exception {
        this.requestMap = bindRequestMap();
    }

    public JdbcRequestMapBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(JdbcRequestMapBuilder builder) {
        this.builder = builder;
    }


}
