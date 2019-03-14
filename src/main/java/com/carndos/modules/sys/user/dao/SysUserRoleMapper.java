package com.carndos.modules.sys.user.dao;

import com.carndos.modules.sys.user.entity.SysUser;
import com.carndos.modules.sys.user.entity.SysUserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserRoleMapper extends Mapper<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);
}