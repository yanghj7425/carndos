package com.yhj.web.service.common.impl;

import com.yhj.web.service.common.BaseServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

public abstract class BaseService<T, M extends Mapper<T>> implements BaseServiceI<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected M baseMapper;


    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public T queryById(int id) {
        return baseMapper.selectByPrimaryKey(id);
    }
}
