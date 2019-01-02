package com.yhj.modules.res.controller;

import com.alibaba.fastjson.JSON;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.commons.pojo.PoJoUtils;
import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.pojo.ResNode;
import com.yhj.modules.res.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return renderSuccess("tree", JSON.toJSON(resTree));
    }

    @PostMapping("addRes")
    public Map createResource(ResNode resNode) {
        SysResource sysResource = PoJoUtils.transferResNode2SysResource(resNode);
        Long primaryKey = resourceService.insertNewResource(sysResource);
        return renderSuccess("KEY", primaryKey);
    }

    @PostMapping("updateRes")
    public Map updateResource(ResNode resNode) {
        resourceService.updateResource(PoJoUtils.transferResNode2SysResource(resNode));

        return renderSuccess();
    }

}
