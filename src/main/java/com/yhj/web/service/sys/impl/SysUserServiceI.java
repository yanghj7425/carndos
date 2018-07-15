package com.yhj.web.service.sys.impl;

import com.yhj.web.dao.sys.SysUserMapper;
import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.sys.SysUserService;
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
