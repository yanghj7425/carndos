package com.yhj.web.service.common.impl;

import com.yhj.web.service.common.BaseServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public abstract class BaseService<T, M extends Mapper<T>> implements BaseServiceI<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected M baseMapper;

    @Override
    public List<T> queryAll() {
        return baseMapper.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public T queryById(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }
}
