package com.carndos.modules.sys.res.dao;

import com.carndos.modules.sys.res.entity.SysResource;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ResourceMapper extends Mapper<SysResource> {
    /**
     * @param sysResource 需要插入的资源信息
     * @return Integer
     */
    Integer insertNewResource(SysResource sysResource);

    /**
     * @return List<SysResource>
     */
    List<SysResource> queryResourceOrderById();
}