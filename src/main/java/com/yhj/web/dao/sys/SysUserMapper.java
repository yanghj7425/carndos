package com.yhj.web.dao.sys;

import com.yhj.web.entity.sys.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SysUserMapper extends Mapper<SysUser> {

    SysUser querySysUserByName(String userName);
}