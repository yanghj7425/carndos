package com.yhj.modules.res.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import java.util.Map;

@Service("resourceService")
public class ResourceServiceI extends BaseService<SysResource, Mapper<SysResource>> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<SysResource> queryAll() {
        return resourceMapper.selectAll();
    }

    @Override
    public Integer insertNewResource(ResNode resNode) {
        SysResource sysResource = resNode.getSysResource();
        Integer count = resourceMapper.insertNewResource(sysResource);
        resNode.setId(sysResource.getId());
        return count;
    }

    @Override
    public List<ResNode> queryResourceTree() {
        List<SysResource> list = resourceMapper.queryResourceOrderById();
        List<ResNode> resNodeList = Lists.newArrayList();
        for (SysResource sysResource : list) {
            ResNode resNode = sysResource.getResNode();
//            ResNode resNode = new ResNode();
            fillResNodeList(resNode, resNodeList);
        }
        return resNodeList;
    }

    /**
     * @param resNode     资源节点
     * @param resNodeList 资源节点列表
     * @description 递归处理树 信息
     */
    private void fillResNodeList(ResNode resNode, List<ResNode> resNodeList) {
        Iterator<ResNode> itr = resNodeList.iterator();
        boolean isContainNode = false;
        while (itr.hasNext()) {
            ResNode node = itr.next();
            if (node.getId().equals(resNode.getResFid())) {
                isContainNode = true;
                fillResNodeList(resNode, node.getChildren());
            }
        }
        if (!isContainNode) {
            resNodeList.add(resNode);
        }
    }

}
