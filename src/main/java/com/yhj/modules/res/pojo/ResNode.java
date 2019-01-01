package com.yhj.modules.res.pojo;

import com.yhj.modules.res.entity.SysResource;

import java.io.Serializable;

public class ResNode implements Serializable {

    private static final long serialVersionUID = -291851707773983421L;
    /**
     * sysResource ID
     */
    private Long id;

    /**
     * sysResource FID
     */
    private Long resFid;

    /**
     * sysResource Name
     */
    private String label;

    /**
     * sysResource Desc
     */
    private String resDesc;

    /**
     * sysResource URL
     */
    private String resUrl;

    /**
     * 设置资源状态: 1 有效, 0 无效
     * sysResource status
     */
    private Integer resStatus;


    public ResNode() {
    }

    /**
     * @return
     * @description translate ResNode Object to SysResource Object
     */
    public SysResource getSysResource() {
        SysResource sysResource = new SysResource();
        sysResource.setId(getId());
        sysResource.setResType("URL");
        sysResource.setResDesc(getResDesc());
        sysResource.setResFid(getResFid());
        sysResource.setResUrl(getResUrl());
        sysResource.setResName(getLabel());
        sysResource.setResStatus(getResStatus());
        return sysResource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResFid() {
        return resFid;
    }

    public void setResFid(Long resFid) {
        this.resFid = resFid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public Integer getResStatus() {
        return resStatus;
    }

    public void setResStatus(Integer resStatus) {
        this.resStatus = resStatus;
    }
}
