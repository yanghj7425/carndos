package com.yhj.web.service.sys;

import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.entity.sys.SysUserRole;
import com.yhj.web.service.common.BaseServiceI;

import java.util.List;

public interface SysUserRoleService extends BaseServiceI<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);


}
