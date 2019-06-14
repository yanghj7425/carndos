package com.carndos.modules.user.service;

import com.carndos.modules.commons.service.BaseServiceI;
import com.carndos.modules.commons.pojo.PageParam;
import com.carndos.modules.user.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService extends BaseServiceI<SysUser> {
    SysUser querySysUserByName(String userName);

    List<SysUser> queryAllUser();

    PageInfo<SysUser> queryForPage(PageParam pageParam);
}
