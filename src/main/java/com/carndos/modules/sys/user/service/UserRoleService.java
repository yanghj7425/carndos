package com.carndos.modules.sys.user.service;

import com.carndos.modules.commons.service.BaseServiceI;
import com.carndos.modules.sys.user.entity.SysUser;
import com.carndos.modules.sys.user.entity.SysUserRole;

import java.util.List;

public interface UserRoleService extends BaseServiceI<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);


}
