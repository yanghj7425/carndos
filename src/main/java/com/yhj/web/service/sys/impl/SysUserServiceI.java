package com.yhj.web.service.sys.impl;

import com.yhj.web.dao.sys.SysUserMapper;
import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.sys.SysUserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.common.Mapper;

@Service(value = "sysUserService")
public class SysUserServiceI extends BaseService<SysUser, Mapper<SysUser>> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public int insert(SysUser sysUser) {
        TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        System.out.println("++++++++++++++++++++++++" + status.isRollbackOnly());

        return super.insert(sysUser);
    }

    @Override
    public SysUser querySysUserByName(String userName) {
        return  sysUserMapper.querySysUserByName(userName);
    }
}
