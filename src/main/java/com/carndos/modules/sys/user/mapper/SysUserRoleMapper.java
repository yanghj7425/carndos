package com.carndos.modules.sys.user.mapper;

import com.carndos.modules.sys.user.entity.SysUser;
import com.carndos.modules.sys.user.entity.SysUserRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SysUserRoleMapper extends Mapper<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);
}