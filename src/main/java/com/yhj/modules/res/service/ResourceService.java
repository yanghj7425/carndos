package com.yhj.modules.res.service;

import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.commons.service.BaseServiceI;
import com.yhj.modules.res.pojo.ResNode;

import java.util.List;
import java.util.Map;

public interface ResourceService extends BaseServiceI<SysResource> {
    /**
     * @param sysResource
     * @return
     * @description 插入一条资源信息
     */
    Integer insertNewResource(SysResource sysResource);


    List<ResNode> queryResourceTree();

}
