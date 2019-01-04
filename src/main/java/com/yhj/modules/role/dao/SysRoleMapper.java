package com.yhj.modules.role.dao;

import com.yhj.modules.role.entity.SysRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysRoleMapper extends Mapper<SysRole> {


    /**
     * @return List<SysRole>
     * @description this method  query sysRole information
     */
    List<SysRole> querySysRoles();
}