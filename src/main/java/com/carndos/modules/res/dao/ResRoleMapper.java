package com.carndos.modules.res.dao;

import com.carndos.modules.res.entity.SysResRole;
import com.carndos.modules.res.pojo.ResRoleBO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface ResRoleMapper extends Mapper<ResRoleBO> {

    List<ResRoleBO> queryAllResRole();

    /**
     * @param sysResRole It`s  the entity class that  the relational table of Role and  SysResource
     * @return primary key of the table res_role
     */
    Integer saveResToRole(SysResRole sysResRole);

    /**
     * @param resId
     * @return list
     * @description this method will query all off role which can access the resource
     */
    List<Integer> queryResAssignedRoleIds(String resId);


    /**
     *
     * @param resMap
     * @return
     */
    Integer updateResStatus(Map<String,Object> resMap);
}