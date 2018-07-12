package com.yhj.web.dao.res;

import com.yhj.web.entity.res.ResRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ResRoleMapper extends Mapper<ResRole> {

    List<ResRole> queryAllResRole();

}