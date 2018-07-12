package com.yhj.web.service.res.impl;

import com.yhj.web.dao.res.ResourceMapper;
import com.yhj.web.entity.res.Resource;
import com.yhj.web.service.common.impl.BaseService;
import com.yhj.web.service.res.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service("resourceService")
@Transactional(readOnly = true)
public class ResourceServiceI extends BaseService<Resource, Mapper<Resource>> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> queryAll() {
        return resourceMapper.selectAll();
    }
}
