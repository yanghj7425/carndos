package com.yhj.modules.res.controller;

import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.res.entity.SysResource;
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
        List<Map<String, Object>> resTree = resourceService.queryResourceTree();
        return renderSuccess("tree", resTree);
    }

    @PostMapping("addRes")
    public Map createResource(SysResource sysResource) {
        Long primaryKey = resourceService.insertNewResource(sysResource);
        return renderSuccess("KEY", primaryKey);
    }

    @PostMapping("updateRes")
    public Map updateResource(SysResource sysResource) {
        resourceService.updateResource(sysResource);

        return renderSuccess();
    }

}
