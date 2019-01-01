package com.yhj.modules.res.service.impl;

import com.google.common.collect.Lists;
import com.yhj.modules.commons.service.impl.BaseService;
import com.yhj.modules.res.dao.ResourceMapper;
import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
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
    public Long insertNewResource(SysResource sysResource) {
        resourceMapper.insertNewResource(sysResource);
        return sysResource.getId();
    }

    @Override
    public List<Map<String, Object>> queryResourceTree() {
        List<Map<String, Object>> list = resourceMapper.queryResourceOrderById();
        List<Map<String, Object>> subResNodeList = Lists.newArrayList();
        for (Map<String, Object> sysResource : list) {
            fillResNodeList(sysResource, subResNodeList);
        }
        return subResNodeList;
    }

    
    @Override
    public Integer updateResource(SysResource sysResource) {
        return resourceMapper.updateByPrimaryKeySelective(sysResource);
    }

    /**
     * when we get a node, we should  check in subResNodeList if one node`s  id  equals  the new node then we put the new node in it`s subList
     *
     * @param resNode        resource node
     * @param subResNodeList resource nodes list
     * @description recursively put nodes in list for construct a resource tree
     */
    private void fillResNodeList(Map<String, Object> resNode, List<Map<String, Object>> subResNodeList) {
        Iterator<Map<String, Object>> itr = subResNodeList.iterator();
        boolean isContainNode = false;
        while (itr.hasNext()) {
            Map<String, Object> node = itr.next();
            // if current id`s value equals 
            if (node.get("id").equals(resNode.get("resFid"))) {
                isContainNode = true;
                ArrayList<Map<String, Object>> children;
                if (node.containsKey("children")) {
                    children = (ArrayList<Map<String, Object>>) node.get("children");
                } else {
                    children = Lists.newArrayList();
                }
                node.put("children", children);
                fillResNodeList(resNode, children);
            }
        }
        if (!isContainNode) {
            subResNodeList.add(resNode);
        }
    }


}
