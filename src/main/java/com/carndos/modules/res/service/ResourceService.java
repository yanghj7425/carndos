package com.carndos.modules.res.service;

import com.carndos.modules.commons.service.BaseServiceI;
import com.carndos.modules.res.entity.SysResource;
import com.carndos.modules.res.pojo.ResBO;

import java.util.List;

public interface ResourceService extends BaseServiceI<SysResource> {
    /**
     * @param sysResource record  information
     * @return Long the primary key of the inserted  sysResource
     * @description insert one  record into sysResource table
     */
    Long insertNewResource(SysResource sysResource);


    /**
     * @return the resource tree
     * @description query sysResource as a List,then  reconstruct  it to be a tree style
     */
    List<ResBO> queryResTree();

    /**
     * @param sysResource the resource detail want to bu updated
     * @return the record of updates
     */
    Integer updateResource(SysResource sysResource);


}
