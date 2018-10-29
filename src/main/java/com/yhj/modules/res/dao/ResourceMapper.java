package com.yhj.modules.res.dao;

import com.yhj.modules.res.entity.Resource;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ResourceMapper extends Mapper<Resource> {
}