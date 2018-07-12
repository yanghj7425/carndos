package com.yhj.web.dao.res;

import com.yhj.web.entity.res.ResRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResRoleMapper {

    List<ResRole> queryAllResRole();

}