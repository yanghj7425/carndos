package com.yhj.modules.res.dao;

import com.yhj.modules.res.entity.ResRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ResRoleMapper extends Mapper<ResRole> {

    List<ResRole> queryAllResRole();

}