package com.yhj.modules.sys.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.sys.dao.SysUserMapper;
import com.yhj.modules.sys.entity.SysUser;
import com.yhj.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.common.Mapper;

@Service(value = "sysUserService")
public class SysUserServiceI extends BaseService<SysUser, Mapper<SysUser>> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public int insert(SysUser sysUser) {
        TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        System.out.println("++++++++++++++++++++++++" + status.isRollbackOnly());

        //给密码编码后存储
        sysUser.setUserPasswd(passwordEncoder.encode(sysUser.getUserName()));

        return super.insert(sysUser);
    }

    @Override
    public SysUser querySysUserByName(String userName) {
        return sysUserMapper.querySysUserByName(userName);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}