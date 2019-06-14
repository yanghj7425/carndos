package com.carndos.modules.commons.util;

import com.carndos.modules.res.entity.SysResource;
import com.carndos.modules.res.pojo.ResBO;

/**
 * this class transfer pojo object to entity object  vice versa
 */
public class PoJoUtils {

    private PoJoUtils() {

    }

    /**
     * @param poJoResNode Instances that want to be transformed into ResBO objects
     * @return SysResource
     * translate ResBO Object to SysResource Object
     */
    public static SysResource transferResNode2SysResource(ResBO poJoResNode) {
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
     * @return ResBO
     */
    public static ResBO transferSysResource2ResNode(SysResource sysResource) {
        ResBO resBO = new ResBO();
        resBO.setId(sysResource.getId());
        resBO.setResFid(sysResource.getResFid());
        resBO.setLabel(sysResource.getResName());
        resBO.setResUrl(sysResource.getResUrl());
        resBO.setResDesc(sysResource.getResDesc());
        resBO.setResStatus(sysResource.getResStatus());
        return resBO;
    }


}
