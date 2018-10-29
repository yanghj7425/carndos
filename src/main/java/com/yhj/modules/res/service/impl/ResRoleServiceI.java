package com.yhj.modules.res.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.res.dao.ResRoleMapper;
import com.yhj.modules.res.entity.ResRole;
import com.yhj.modules.res.service.ResRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

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
