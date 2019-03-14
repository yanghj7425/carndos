package com.carndos.modules.sys.res.controller;

import com.alibaba.fastjson.JSON;
import com.carndos.modules.commons.controller.BaseController;
import com.carndos.modules.commons.util.PoJoUtils;
import com.carndos.modules.sys.res.entity.SysResource;
import com.carndos.modules.sys.res.pojo.PoJoResNode;
import com.carndos.modules.sys.res.service.ResRoleService;
import com.carndos.modules.sys.res.service.ResourceService;
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


    @Autowired
    private ResRoleService resRoleService;

    /**
     * @return map
     * @description query access control list tree
     */
    @GetMapping("tree")
    public Map queryResTree() {
        List<PoJoResNode> resTree = resourceService.queryResTree();
        return renderSuccess("tree", JSON.toJSON(resTree));
    }

    /**
     * @param poJoResNode been added data
     * @return primary key
     * @description insert one tree node,that web client submit
     */
    @PostMapping("create")
    public Map createResource(PoJoResNode poJoResNode) {
        SysResource sysResource = PoJoUtils.transferResNode2SysResource(poJoResNode);
        Long primaryKey = resourceService.insertNewResource(sysResource);
        return renderSuccess(primaryKey);
    }

    /**
     * @param poJoResNode be updated node
     * @return Map
     * @description update a resource by Id
     */
    @PostMapping("update")
    public Map updateResource(PoJoResNode poJoResNode) {
        resourceService.updateResource(PoJoUtils.transferResNode2SysResource(poJoResNode));
        return renderSuccess();
    }

    /**
     * assign resource to a role
     * This two parameters  holds the mapping of resources and roles submitted by the web client
     *
     * @param roleIds the array of role`s id
     * @param resId   SysResource Id
     * @return map
     */
    @PostMapping("assign")
    public Map saveResToRole(@RequestParam("roleIds") String[] roleIds, @RequestParam("resId") String resId) {
        List<Integer> list = resRoleService.saveResToRole(resId, roleIds);
        return renderSuccess(list);
    }

    /**
     * @param resId Resource ID parameter
     * @return role`s ids array
     * @description this method will return a role`s id array, that roles can access resource which id is resId
     */
    @GetMapping("assigned/{resId}")
    public Map queryResAssignedRoleIds(@PathVariable("resId") String resId) {
        List<Integer> roleIds = resRoleService.queryResAssignedRoleIds(resId);
        return renderSuccess("roleIds", roleIds);
    }


}
