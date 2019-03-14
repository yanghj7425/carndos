package com.carndos.modules.sys.user.service;

import com.carndos.modules.commons.service.BaseServiceI;
import com.carndos.modules.sys.user.entity.SysUser;

import java.util.List;

public interface UserService extends BaseServiceI<SysUser> {
    SysUser querySysUserByName(String userName);

    List<SysUser> queryAllUser();

}
