package com.yhj.modules.user.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.user.dao.SysUserRoleMapper;
import com.yhj.modules.user.entity.SysUser;
import com.yhj.modules.user.entity.SysUserRole;
import com.yhj.modules.user.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "sysUserRoleService")
public class SysUserRoleServiceI extends BaseService<SysUserRole, Mapper<SysUserRole>> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> querySysUserRoleByName(SysUser sysUser) {
        return sysUserRoleMapper.querySysUserRoleByName(sysUser);
    }
}
