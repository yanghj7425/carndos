package com.yhj.modules.sys.service;

import com.yhj.modules.sys.entity.SysUser;
import com.yhj.modules.sys.entity.SysUserRole;
import com.yhj.modules.commons.service.BaseServiceI;

import java.util.List;

public interface SysUserRoleService extends BaseServiceI<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);


}
