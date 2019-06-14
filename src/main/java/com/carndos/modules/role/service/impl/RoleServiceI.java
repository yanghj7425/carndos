package com.carndos.modules.role.service.impl;

import com.carndos.modules.commons.service.impl.AbstractBaseService;
import com.carndos.modules.role.dao.SysRoleMapper;
import com.carndos.modules.role.service.RoleService;
import com.carndos.modules.role.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "roleService")
public class RoleServiceI extends AbstractBaseService<SysRole, Mapper<SysRole>> implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> querySysRoles() {
        return sysRoleMapper.querySysRoles();
    }
}
