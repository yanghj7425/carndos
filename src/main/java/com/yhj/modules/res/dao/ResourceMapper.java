package com.yhj.modules.res.dao;

import com.yhj.modules.res.entity.SysResource;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ResourceMapper extends Mapper<SysResource> {
    Integer insertNewResource(SysResource sysResource);
}