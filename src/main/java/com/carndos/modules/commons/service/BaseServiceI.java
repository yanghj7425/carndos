package com.carndos.modules.commons.service;

import java.util.List;

/**
 * @param <T>
 * @description Service 接口
 */
public interface BaseServiceI<T> {
    /**
     * @return
     * @description 查询所有数据
     */
    List<T> queryAll();

    T queryById(Long id);


    int insert(T t);


}
