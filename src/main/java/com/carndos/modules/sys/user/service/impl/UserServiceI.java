package com.carndos.modules.sys.user.service.impl;

import com.carndos.modules.commons.service.impl.AbstractBaseService;
import com.carndos.modules.sys.user.mapper.SysUserMapper;
import com.carndos.modules.sys.user.entity.SysUser;
import com.carndos.modules.sys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceI extends AbstractBaseService<SysUser, Mapper<SysUser>> implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;


    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public int insert(SysUser sysUser) {
        //给密码编码后存储

        sysUser.setUserPasswd(sysUser.getUserPasswd());

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
