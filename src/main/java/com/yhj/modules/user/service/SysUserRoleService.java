package com.yhj.modules.user.service;

import com.yhj.modules.commons.service.BaseServiceI;
import com.yhj.modules.user.entity.SysUser;
import com.yhj.modules.user.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends BaseServiceI<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);


}
