package com.yhj.modules.role.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.role.entity.SysRole;
import com.yhj.modules.role.service.RoleService;
import com.yhj.modules.role.dao.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "roleService")
public class RoleServiceI extends BaseService<SysRole, Mapper<SysRole>> implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> querySysRoles() {
        return sysRoleMapper.querySysRoles();
    }
}
