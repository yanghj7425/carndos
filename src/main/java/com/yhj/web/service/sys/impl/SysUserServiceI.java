package com.yhj.web.service.sys.impl;

import com.yhj.web.entity.sys.SysUser;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.sys.SysUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service(value = "sysUserService")
public class SysUserServiceI extends BaseService<SysUser, Mapper<SysUser>> implements SysUserService {

}
