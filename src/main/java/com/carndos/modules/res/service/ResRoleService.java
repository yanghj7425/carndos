package com.carndos.modules.res.service;

import com.carndos.modules.commons.service.BaseServiceI;
import com.carndos.modules.res.pojo.ResRoleBO;

import java.util.List;

public interface ResRoleService extends BaseServiceI<ResRoleBO> {


    /**
     * @param roleIds this is a array of role`id
     * @param resId   resource`s id that would be assigned to roles
     * @return list  res_role`s primary keys
     */
    List<Integer> saveResToRole(String resId, String... roleIds);

    /**
     * @param resId The resource`s Id
     * @return list
     * @description this method will query all off role which can access the resource
     */
    List<Integer> queryResAssignedRoleIds(String resId);
}
