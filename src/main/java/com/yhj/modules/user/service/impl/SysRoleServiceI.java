package com.yhj.modules.user.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.user.dao.SysRoleMapper;
import com.yhj.modules.user.entity.SysRole;
import com.yhj.modules.user.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "sysRoleService")
public class SysRoleServiceI extends BaseService<SysRole, Mapper<SysRole>> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> querySysRoles() {
        return sysRoleMapper.querySysRoles();
    }
}
