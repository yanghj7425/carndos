package com.yhj.web.service.res.impl;

import com.yhj.web.dao.res.ResRoleMapper;
import com.yhj.web.entity.res.ResRole;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.res.ResRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service("resRoleService")
public class ResRoleServiceI extends BaseService<ResRole, Mapper<ResRole>> implements ResRoleService {

    @Autowired
    private ResRoleMapper resRoleMapper;

    public List<ResRole> selectAll() {
        return resRoleMapper.queryAllResRole();
    }
}
