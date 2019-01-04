package com.yhj.modules.user.service;

import com.yhj.modules.commons.service.BaseServiceI;
import com.yhj.modules.user.entity.SysUser;

import java.util.List;

public interface SysUserService extends BaseServiceI<SysUser> {
    SysUser querySysUserByName(String userName);

    List<SysUser> queryAllUser();

}
