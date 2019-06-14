package com.carndos.modules.commons.service.impl;

import com.carndos.modules.commons.service.BaseServiceI;
import com.carndos.modules.commons.pojo.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @param <T>
 * @param <M>
 * @description 默认为修改为只读事务
 */
@Transactional(transactionManager = "transactionManager")
public abstract class AbstractBaseService<T, M extends Mapper<T>> implements BaseServiceI<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected M baseMapper;

    @Override
    public List<T> queryAll() {
        return baseMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public T queryById(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    protected final PageInfo<T> doQueryForPage(PageParam pageParam, Function<Map<String, Object>, List<T>> action) {
        try {
            PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
            List<T> list = action.apply(pageParam.getParams());
            PageInfo<T> pageInfo = PageInfo.of(list);
            return pageInfo;
        } finally {
            PageHelper.clearPage();
        }
    }

}
