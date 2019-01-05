package com.yhj.modules.user.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.user.dao.SysUserMapper;
import com.yhj.modules.user.entity.SysUser;
import com.yhj.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "userService")
public class UserServiceI extends BaseService<SysUser, Mapper<SysUser>> implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public int insert(SysUser sysUser) {
        //给密码编码后存储
        sysUser.setUserPasswd(passwordEncoder.encode(sysUser.getUserName()));

        return super.insert(sysUser);
    }

    @Override
    public SysUser querySysUserByName(String userName) {
        return sysUserMapper.querySysUserByName(userName);
    }

    @Override
    public List<SysUser> queryAllUser() {
        return sysUserMapper.selectAll();
    }



    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}