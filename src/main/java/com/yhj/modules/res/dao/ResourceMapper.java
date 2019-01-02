package com.yhj.modules.res.dao;

import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.pojo.ResNode;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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