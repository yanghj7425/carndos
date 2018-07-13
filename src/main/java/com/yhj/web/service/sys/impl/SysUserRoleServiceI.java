package com.yhj.web.service.sys.impl;

import com.yhj.web.dao.sys.SysUserRoleMapper;
import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.entity.sys.SysUserRole;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.sys.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "sysUserRoleService")
public class SysUserRoleServiceI extends BaseService<SysUserRole, Mapper<SysUserRole>> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> querySysUserRoleByName(SysUser sysUser) {
        return sysUserRoleMapper.querySysUserRoleByName(sysUser);
    }
}
