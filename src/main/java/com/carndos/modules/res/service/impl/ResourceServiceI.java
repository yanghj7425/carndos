package com.carndos.modules.res.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.carndos.modules.commons.service.impl.AbstractBaseService;
import com.carndos.modules.commons.util.PoJoUtils;
import com.carndos.modules.res.entity.SysResource;
import com.carndos.modules.res.dao.ResourceMapper;
import com.carndos.modules.res.pojo.ResBO;
import com.carndos.modules.res.service.ResourceService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

@Service("resourceService")
public class ResourceServiceI extends AbstractBaseService<SysResource, Mapper<SysResource>> implements ResourceService {

    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public List<SysResource> queryAll() {
        return resourceMapper.selectAll();
    }

    @Override
    public Long insertNewResource(SysResource sysResource) {
        resourceMapper.insertNewResource(sysResource);
        return sysResource.getId();
    }


    @Override
    public List<ResBO> queryResTree() {
        List<SysResource> list = resourceMapper.queryResourceOrderById();
        List<ResBO> poJoResNodeList = CollectionUtil.newArrayList();
        for (SysResource sysResource : list) {
            ResBO poJoResNode = PoJoUtils.transferSysResource2ResNode(sysResource);
            boolean isPutted = fillResNodeList(poJoResNode, poJoResNodeList);
            if (!isPutted) {
                poJoResNodeList.add(poJoResNode);
            }
        }
        return poJoResNodeList;
    }


    @Override
    public Integer updateResource(SysResource sysResource) {
        return resourceMapper.updateByPrimaryKeySelective(sysResource);
    }


    /**
     * @param poJoResNode          the pojo object of sysResource that would be add in ResBO`s children
     * @param childPoJoResNodeList children list
     * @return if the node is add in children return true,otherwise false
     */
    private boolean fillResNodeList(ResBO poJoResNode, List<ResBO> childPoJoResNodeList) {
        boolean isPutted = false;
        for (ResBO node : childPoJoResNodeList) {
            if (node.getId().equals(poJoResNode.getResFid())) {
                node.getChildren().add(poJoResNode);
                return true;
            }
            if (node.getChildren().size() > 0) {
                isPutted = fillResNodeList(poJoResNode, node.getChildren());
            }
        }
        return isPutted;
    }
}
