package com.yhj.modules.res.controller;

import com.alibaba.fastjson.JSON;
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

        renderSuccess();
        List<ResNode> resTree = resourceService.queryResourceTree();
        return renderSuccess("tree", JSON.toJSON(resTree));
    }

    @PostMapping("addRes")
    public Map createResNode(SysResource sysResource) {
        sysResource.setResType("URL");
        Integer count = resourceService.insertNewResource(sysResource);
        return renderSuccess("COUNT", count);
    }

}
