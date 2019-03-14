package com.carndos.modules.sys.user.service.impl;

import com.carndos.modules.commons.service.impl.BaseService;
import com.carndos.modules.sys.user.dao.SysUserRoleMapper;
import com.carndos.modules.sys.user.entity.SysUser;
import com.carndos.modules.sys.user.entity.SysUserRole;
import com.carndos.modules.sys.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "userRoleService")
public class UserRoleServiceI extends BaseService<SysUserRole, Mapper<SysUserRole>> implements UserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> querySysUserRoleByName(SysUser sysUser) {
        return sysUserRoleMapper.querySysUserRoleByName(sysUser);
    }
}
