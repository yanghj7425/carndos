package com.yhj.modules.sys.dao;

import com.yhj.modules.sys.entity.SysUser;
import com.yhj.modules.sys.entity.SysUserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserRoleMapper extends Mapper<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);
}