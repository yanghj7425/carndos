package com.yhj.web.dao.sys;

import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.entity.sys.SysUserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserRoleMapper extends Mapper<SysUserRole> {
    List<SysUserRole> querySysUserRoleByName(SysUser sysUser);
}