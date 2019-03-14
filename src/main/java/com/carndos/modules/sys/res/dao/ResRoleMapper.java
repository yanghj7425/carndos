package com.carndos.modules.sys.res.dao;

import com.carndos.modules.sys.res.entity.SysResRole;
import com.carndos.modules.sys.res.pojo.PoJoResRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ResRoleMapper extends Mapper<PoJoResRole> {

    List<PoJoResRole> queryAllResRole();

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