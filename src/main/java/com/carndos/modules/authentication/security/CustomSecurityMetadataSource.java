package com.carndos.modules.authentication.security;

import com.carndos.modules.sys.res.mapper.ResRoleMapper;
import com.carndos.modules.sys.res.pojo.PoJoResRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 默认实现  DefaultFilterInvocationSecurityMetadataSource
 */

@Component(value = "securityMetadataSource")
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {


    private final Logger logger = LoggerFactory.getLogger(getClass());


    //资源权限集合
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    //查找数据库权限和资源的关系
    @Resource
    private ResRoleMapper resRoleMapper;

    public Collection<ConfigAttribute> getAttributes(Object object) {

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();

        Collection<ConfigAttribute> attribute = new ArrayList<>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                attribute = entry.getValue();
                return attribute;
            }
        }
        return attribute;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> attributes = new HashSet<>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            attributes.addAll(entry.getValue());
        }
        if (logger.isInfoEnabled()) {
            logger.debug("System have those privileges : {}", attributes);
        }
        return attributes;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


    @Override
    public void afterPropertiesSet() {
        this.requestMap = buildRequestMap();
    }


    /**
     * @return 返回 requestMap 资源和权限对应关系的数据格式
     * @description 一个资源可以被多个角色访问
     */
    public Map<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> matcherCollectionLinkedHashMap = new LinkedHashMap<>();

        List<PoJoResRole> resourceList = resRoleMapper.queryAllResRole();

        for (PoJoResRole poJoResRole : resourceList) {

            RequestMatcher requestMatcher = getRequestMatcher(poJoResRole.getResUrl());

            Collection<ConfigAttribute> multipleRoles;

            if (matcherCollectionLinkedHashMap.containsKey(requestMatcher)) {

                multipleRoles = matcherCollectionLinkedHashMap.get(requestMatcher);

            } else {
                multipleRoles = new ArrayList<>();
            }
            multipleRoles.add(new SecurityConfig(poJoResRole.getRoleName()));
            // bad small

            matcherCollectionLinkedHashMap.put(requestMatcher, multipleRoles);
        }
        return matcherCollectionLinkedHashMap;
    }


    private RequestMatcher getRequestMatcher(String resUrl) {
        return new AntPathRequestMatcher(resUrl);
    }


}
