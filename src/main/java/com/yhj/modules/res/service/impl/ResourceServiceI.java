package com.yhj.modules.res.service.impl;

import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.res.dao.ResourceMapper;
import com.yhj.modules.res.entity.Resource;
import com.yhj.modules.res.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service("resourceService")
public class ResourceServiceI extends BaseService<Resource, Mapper<Resource>> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> queryAll() {
        return resourceMapper.selectAll();
    }
}
