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
        List<ResNode> resNodeList = Lists.newArrayList();
        for (SysResource sysResource : list) {
            ResNode resNode = PoJoUtils.transferSysResource2ResNode(sysResource);
            boolean isPutted = fillResNodeList(resNode, resNodeList);
            if (!isPutted) {
                resNodeList.add(resNode);
            }
        }
        return resNodeList;
    }


    @Override
    public Integer updateResource(SysResource sysResource) {
        return resourceMapper.updateByPrimaryKeySelective(sysResource);
    }


    /**
     * @param resNode          the pojo object of sysResource that would be add in ResNode`s children
     * @param childResNodeList children list
     * @return if the node is add in children return true,otherwise false
     */
    private boolean fillResNodeList(ResNode resNode, List<ResNode> childResNodeList) {
        boolean isPutted = false;
        for (ResNode node : childResNodeList) {
            if (node.getId().equals(resNode.getResFid())) {
                node.getChildren().add(resNode);
                return true;
            }
            if (node.getChildren().size() > 0) {
                isPutted = fillResNodeList(resNode, node.getChildren());
            }
        }
        return isPutted;
    }
}
