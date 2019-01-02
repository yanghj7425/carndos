package com.yhj.modules.res.service.impl;

import com.google.common.collect.Lists;
import com.yhj.modules.commons.pojo.PoJoUtils;
import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.res.dao.ResourceMapper;
import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.pojo.ResNode;
import com.yhj.modules.res.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.Iterator;
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
    public Long insertNewResource(SysResource sysResource) {
        resourceMapper.insertNewResource(sysResource);
        return sysResource.getId();
    }

    @Override
    public List<ResNode> queryResourceTree() {
        List<SysResource> list = resourceMapper.queryResourceOrderById();
        List<ResNode> childResNodeList = Lists.newArrayList();
        for (SysResource sysResource : list) {
            ResNode resNode = PoJoUtils.transferSysResource2ResNode(sysResource);
            fillResNodeList(resNode, childResNodeList);
        }
        return childResNodeList;
    }


    @Override
    public Integer updateResource(SysResource sysResource) {
        return resourceMapper.updateByPrimaryKeySelective(sysResource);
    }

    /**
     * when we get a node, we should  check in subResNodeList if one node`s  id  equals  the new node then we put the new node in it`s subList
     *
     * @param resNode        resource node
     * @param childResNodeList  resource nodes list
     * @description recursively put nodes in list for construct a resource tree
     */
    private void fillResNodeList(ResNode resNode, List<ResNode> childResNodeList) {
        Iterator<ResNode> itr = childResNodeList.iterator();
        boolean isContainNode = false;
        while (itr.hasNext()) {
            ResNode node = itr.next();
            if (node.getId().equals(resNode.getResFid())) {
                isContainNode = true;
                fillResNodeList(resNode, node.getChildren());
            }
        }
        if (!isContainNode) {
            childResNodeList.add(resNode);
        }
    }


}
