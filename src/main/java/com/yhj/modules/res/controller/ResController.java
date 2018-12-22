package com.yhj.modules.res.controller;

import com.google.common.collect.Maps;
import com.yhj.modules.commons.controller.BaseController;
import com.yhj.modules.res.entity.ResRole;
import com.yhj.modules.res.service.ResRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("res")
public class ResController extends BaseController {

    @Autowired
    private ResRoleService resRoleService;

    @GetMapping("resInfo")
    @ResponseBody
    public Map queryResList() {
        Map<String, Object> map = Maps.newHashMap();

        List<ResRole> list = resRoleService.queryAll();
        map.put("list", list);
        return renderSuccess(map);
    }
}
