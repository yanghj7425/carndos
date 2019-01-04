package com.yhj.modules.user.dao;

import com.yhj.modules.user.entity.SysUser;
import com.yhj.modules.user.entity.SysUserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserRoleMapper extends Mapper<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);
}