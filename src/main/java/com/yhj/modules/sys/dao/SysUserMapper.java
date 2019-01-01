package com.yhj.modules.sys.dao;

import com.yhj.modules.sys.entity.SysRole;
import com.yhj.modules.sys.entity.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserMapper extends Mapper<SysUser> {

    /**
     *
     * @param userName
     * @return SysUser
     */
    SysUser querySysUserByName(String userName);

}