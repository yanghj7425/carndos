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
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service("resRoleService")
@Transactional(readOnly = true)
public class ResRoleServiceI extends BaseService<ResRole, Mapper<ResRole>> implements ResRoleService {

    @Autowired
    private ResRoleMapper resRoleMapper;

    @Override
    public List<ResRole> queryAll() {

        return resRoleMapper.queryAllResRole();

    }



}
