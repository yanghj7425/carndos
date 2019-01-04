package com.yhj.modules.user.dao;

import com.yhj.modules.user.entity.SysUser;
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