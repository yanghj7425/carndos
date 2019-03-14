package com.carndos.modules.sys.user.dao;

import com.carndos.modules.sys.user.entity.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SysUserMapper extends Mapper<SysUser> {

    /**
     *
     * @param userName
     * @return SysUser
     */
    SysUser querySysUserByName(String userName);

}