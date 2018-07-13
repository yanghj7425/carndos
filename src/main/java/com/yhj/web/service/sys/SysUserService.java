package com.yhj.web.service.sys;

import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.service.common.BaseServiceI;

public interface SysUserService extends BaseServiceI<SysUser> {
        SysUser querySysUserByName(String userName);
}
