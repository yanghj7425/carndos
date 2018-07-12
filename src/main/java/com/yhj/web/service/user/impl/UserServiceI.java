package com.yhj.web.service.user.impl;

import com.yhj.web.entity.user.User;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.user.UserService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public class UserServiceI extends BaseService<User, Mapper<User>> implements UserService {

    @Override
    public List<User> selectAll() {
        return null;
    }
}
