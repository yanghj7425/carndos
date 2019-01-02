package com.yhj.modules.commons.pojo;

import com.yhj.modules.res.entity.SysResource;
import com.yhj.modules.res.pojo.ResNode;

public class PoJoUtils {

    private PoJoUtils() {

    }

    /**
     * @param resNode Instances that want to be transformed into ResNode objects
     * @return SysResource
     * translate ResNode Object to SysResource Object
     */
    public static SysResource transferResNode2SysResource(ResNode resNode) {
        SysResource sysResource = new SysResource();
        sysResource.setId(resNode.getId());
        sysResource.setResType("URL");
        sysResource.setResDesc(resNode.getResDesc());
        sysResource.setResFid(resNode.getResFid());
        sysResource.setResUrl(resNode.getResUrl());
        sysResource.setResName(resNode.getLabel());
        sysResource.setResStatus(resNode.getResStatus());
        return sysResource;
    }

    /**
     * @param sysResource Instances that want to be transformed into SysResource objects
     * @return ResNode
     */
    public static ResNode transferSysResource2ResNode(SysResource sysResource) {
        ResNode resNode = new ResNode();
        resNode.setId(sysResource.getId());
        resNode.setResDesc(sysResource.getResDesc());
        resNode.setLabel(sysResource.getResName());
        resNode.setResStatus(sysResource.getResStatus());
        resNode.setResUrl(sysResource.getResUrl());
        return resNode;
    }


}
