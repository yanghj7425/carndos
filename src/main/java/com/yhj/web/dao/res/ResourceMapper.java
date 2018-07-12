package com.yhj.web.dao.res;

import com.yhj.web.entity.res.Resource;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ResourceMapper extends Mapper<Resource> {
}