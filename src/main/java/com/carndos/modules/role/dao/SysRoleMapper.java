package com.carndos.modules.role.dao;

import com.carndos.modules.role.entity.SysRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends Mapper<SysRole> {


    /**
     * @return List<SysRole>
     * @description this method  query sysRole information
     */
    List<SysRole> querySysRoles();
}