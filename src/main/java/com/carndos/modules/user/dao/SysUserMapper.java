package com.carndos.modules.user.dao;

import com.carndos.modules.user.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface SysUserMapper extends Mapper<SysUser> {

    /**
     *
     * @param userName
     * @return SysUser
     */
    SysUser querySysUserByName(String userName);

}