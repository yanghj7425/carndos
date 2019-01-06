package com.yhj.modules.res.service.impl;

import com.google.common.collect.Lists;
import com.yhj.modules.commons.util.PoJoUtils;
import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.res.dao.ResourceMapper;
import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.pojo.PoJoResNode;
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
    public Long insertNewResource(SysResource sysResource) {
        resourceMapper.insertNewResource(sysResource);
        return sysResource.getId();
    }


    @Override
    public List<PoJoResNode> queryResTree() {
        List<SysResource> list = resourceMapper.queryResourceOrderById();
        List<PoJoResNode> poJoResNodeList = Lists.newArrayList();
        for (SysResource sysResource : list) {
            PoJoResNode poJoResNode = PoJoUtils.transferSysResource2ResNode(sysResource);
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
     * @param poJoResNode          the pojo object of sysResource that would be add in PoJoResNode`s children
     * @param childPoJoResNodeList children list
     * @return if the node is add in children return true,otherwise false
     */
    private boolean fillResNodeList(PoJoResNode poJoResNode, List<PoJoResNode> childPoJoResNodeList) {
        boolean isPutted = false;
        for (PoJoResNode node : childPoJoResNodeList) {
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
