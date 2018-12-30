package com.yhj.modules.res.pojo;

import com.google.common.collect.Lists;
import com.yhj.modules.res.entity.SysResource;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ResNode implements Serializable {

    private static final long serialVersionUID = -291851707773983421L;

    private List<ResNode> children;
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
    private String resName;

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
//        this.children = Lists.newArrayList();
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
        sysResource.setResName(getResName());
        sysResource.setResStatus(getResStatus());
        return sysResource;
    }

    public List<ResNode> getChildren() {
        return children;
    }

    public void setChildren(List<ResNode> children) {
        this.children = children;
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

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
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
