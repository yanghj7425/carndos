package com.carndos.modules.commons.util;

import com.carndos.modules.sys.res.entity.SysResource;
import com.carndos.modules.sys.res.pojo.PoJoResNode;

/**
 * this class transfer pojo object to entity object  vice versa
 */
public class PoJoUtils {

    private PoJoUtils() {

    }

    /**
     * @param poJoResNode Instances that want to be transformed into PoJoResNode objects
     * @return SysResource
     * translate PoJoResNode Object to SysResource Object
     */
    public static SysResource transferResNode2SysResource(PoJoResNode poJoResNode) {
        SysResource sysResource = new SysResource();
        sysResource.setId(poJoResNode.getId());
        sysResource.setResType("URL");
        sysResource.setResDesc(poJoResNode.getResDesc());
        sysResource.setResFid(poJoResNode.getResFid());
        sysResource.setResUrl(poJoResNode.getResUrl());
        sysResource.setResName(poJoResNode.getLabel());
        sysResource.setResStatus(poJoResNode.getResStatus());
        return sysResource;
    }

    /**
     * @param sysResource Instances that want to be transformed into SysResource objects
     * @return PoJoResNode
     */
    public static PoJoResNode transferSysResource2ResNode(SysResource sysResource) {
        PoJoResNode poJoResNode = new PoJoResNode();
        poJoResNode.setId(sysResource.getId());
        poJoResNode.setResFid(sysResource.getResFid());
        poJoResNode.setLabel(sysResource.getResName());
        poJoResNode.setResUrl(sysResource.getResUrl());
        poJoResNode.setResDesc(sysResource.getResDesc());
        poJoResNode.setResStatus(sysResource.getResStatus());
        return poJoResNode;
    }


}
