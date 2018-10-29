package com.yhj.modules.sys.service;

import com.yhj.modules.sys.entity.SysUser;
import com.yhj.modules.commons.service.BaseServiceI;

public interface SysUserService extends BaseServiceI<SysUser> {
        SysUser querySysUserByName(String userName);
}
