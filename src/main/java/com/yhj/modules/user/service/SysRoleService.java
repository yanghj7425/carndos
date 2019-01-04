package com.yhj.modules.user.service;

import com.yhj.modules.commons.service.BaseServiceI;
import com.yhj.modules.user.entity.SysRole;

import java.util.List;

public interface SysRoleService extends BaseServiceI<SysRole> {


    /**
     * @return List<SysRole>
     * @description this method  query sysRole information
     */
    List<SysRole> querySysRoles();

}
