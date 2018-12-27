package com.yhj.modules.res.controller;

import com.google.common.collect.Maps;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.res.entity.ResRole;
import com.yhj.modules.res.entity.SysResource;
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
    private ResRoleService resRoleService;


    @Autowired
    private ResourceService resourceService;

    @GetMapping("resInfo")
    public Map queryResList() {
        Map<String, Object> map = Maps.newHashMap();
        List<ResRole> list = resRoleService.queryAll();
        map.put("list", list);
        return renderSuccess(map);
    }

    @PostMapping("addRes")
    public Map createResNode(SysResource sysResource) {
        Map<String, Object> map = Maps.newHashMap();
        resourceService.insertNewResource(sysResource);
        return renderSuccess(map);
    }

}
