package com.carndos.modules.role.service;


import com.carndos.modules.commons.service.BaseServiceI;
import com.carndos.modules.role.entity.SysRole;

import java.util.List;

public interface RoleService extends BaseServiceI<SysRole> {


    /**
     * @return List<SysRole>
     * @description this method  query sysRole information
     */
    List<SysRole> querySysRoles();

}
