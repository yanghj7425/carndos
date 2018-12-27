package com.yhj.modules.res.service;

import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.commons.service.BaseServiceI;

public interface ResourceService extends BaseServiceI<SysResource> {
    /**
     * @description 插入一条资源信息
     * @param sysResource
     * @return
     */
    Integer insertNewResource(SysResource sysResource);

}
