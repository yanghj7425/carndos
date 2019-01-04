package com.yhj.modules.role.service;


import com.yhj.modules.commons.service.BaseServiceI;
import com.yhj.modules.role.entity.SysRole;

import java.util.List;

public interface RoleService extends BaseServiceI<SysRole> {


    /**
     * @return List<SysRole>
     * @description this method  query sysRole information
     */
    List<SysRole> querySysRoles();

}
