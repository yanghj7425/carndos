package com.yhj.modules.res.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.res.dao.ResourceMapper;
import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service("resourceService")
public class ResourceServiceI extends BaseService<SysResource, Mapper<SysResource>> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<SysResource> queryAll() {
        return resourceMapper.selectAll();
    }


    @Override
    public Integer insertNewResource(SysResource sysResource) {
        return resourceMapper.insertNewResource(sysResource);
    }
}
