package com.yhj.web.service.res.impl;

import com.yhj.web.dao.res.ResRoleMapper;
import com.yhj.web.entity.res.ResRole;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.res.ResRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service("resRoleService")
public class ResRoleServiceI extends BaseService<ResRole, Mapper<ResRole>> implements ResRoleService {

    @Autowired
    private ResRoleMapper resRoleMapper;

    @Override
    public List<ResRole> selectAll() {

        return resRoleMapper.queryAllResRole();

    }


    @Override
    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

        List<ResRole> resourceList = selectAll();
        for (ResRole resRole : resourceList) {
            RequestMatcher requestMatcher = getRequestMatcher(resRole.getResUrl());
            List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
            list.add(new SecurityConfig(resRole.getResRole()));
            requestMap.put(requestMatcher, list);
        }
        return requestMap;
    }


    private RequestMatcher getRequestMatcher(String resUrl) {
        return new AntPathRequestMatcher(resUrl);
    }

}
