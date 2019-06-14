package com.carndos.modules.user.service.impl;

import com.carndos.modules.commons.service.impl.AbstractBaseService;
import com.carndos.modules.user.dao.SysUserRoleMapper;
import com.carndos.modules.user.entity.SysUser;
import com.carndos.modules.user.entity.SysUserRole;
import com.carndos.modules.user.service.UserRoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userRoleService")
public class UserRoleServiceI extends AbstractBaseService<SysUserRole, Mapper<SysUserRole>> implements UserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> querySysUserRoleByName(SysUser sysUser) {
        return sysUserRoleMapper.querySysUserRoleByName(sysUser);
    }
}
