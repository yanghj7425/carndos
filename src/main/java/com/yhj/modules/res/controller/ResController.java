package com.yhj.modules.res.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.res.entity.ResRole;
import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.pojo.ResNode;
import com.yhj.modules.res.service.ResRoleService;
import com.yhj.modules.res.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("res")
@ResponseBody
public class ResController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("resTree")
    public Map queryResList() {

        List<ResNode> resTree = resourceService.queryResourceTree();
        String str = resTree.toString();
        System.out.println(str);
        for (ResNode resNode : resTree) {
            System.out.println(JSON.toJSON(resNode));
        }
        return renderSuccess("tree", str);
    }

    @PostMapping("addResNode")
    public Map createResNode(ResNode resNode) {
        resourceService.insertNewResource(resNode);
        Long primaryKey = resNode.getId();
        return renderSuccess("KEY", primaryKey);
    }

}
