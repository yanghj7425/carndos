package com.carndos.modules.sys.user.mapper;

import com.carndos.modules.sys.user.entity.SysUser;
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