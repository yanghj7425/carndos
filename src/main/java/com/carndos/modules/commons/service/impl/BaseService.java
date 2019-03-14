package com.carndos.modules.commons.service.impl;

import com.carndos.modules.commons.service.BaseServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description  默认为修改为只读事务
 * @param <T>
 * @param <M>
 */
@Transactional(transactionManager = "transactionManager")
public abstract class BaseService<T, M extends Mapper<T>> implements BaseServiceI<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected M baseMapper;

    @Override
    public List<T> queryAll() {
        return baseMapper.selectAll();
    }

    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public T queryById(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }
}
