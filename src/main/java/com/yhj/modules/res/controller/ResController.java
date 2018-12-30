package com.yhj.modules.res.controller;

import com.alibaba.fastjson.JSON;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.res.pojo.ResNode;
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

        List<Map<String, Object>> resTree = resourceService.queryResourceTree();
        return renderSuccess("tree", resTree);
    }

    @PostMapping("addResNode")
    public Map createResNode(ResNode resNode) {
        resourceService.insertNewResource(resNode);
        Long primaryKey = resNode.getId();
        return renderSuccess("KEY", primaryKey);
    }

}
