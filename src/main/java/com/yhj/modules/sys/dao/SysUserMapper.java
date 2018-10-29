package com.yhj.modules.sys.dao;

import com.yhj.modules.sys.entity.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SysUserMapper extends Mapper<SysUser> {

    SysUser querySysUserByName(String userName);
}