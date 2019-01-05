package com.yhj.modules.res.service;

import com.yhj.modules.commons.service.BaseServiceI;
import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.pojo.PoJoResNode;

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
    List<PoJoResNode> queryResTree();

    /**
     * @param sysResource the resource detail want to bu updated
     * @return the record of updates
     */
    Integer updateResource(SysResource sysResource);


}
