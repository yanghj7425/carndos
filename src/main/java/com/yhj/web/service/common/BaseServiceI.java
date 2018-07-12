package com.yhj.web.service.common;

import java.util.List;

/**
 * @description Service 接口
 * @param <T>
 */
public interface BaseServiceI<T> {
    /**
     * @description 查询所有数据
     * @return
     */
    List<T> selectAll();
}
