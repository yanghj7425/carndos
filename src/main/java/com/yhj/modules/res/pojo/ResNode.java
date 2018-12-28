package com.yhj.modules.res.pojo;

import com.google.common.collect.Lists;
import com.yhj.modules.res.entity.SysResource;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class ResNode {
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

    public ResNode(SysResource sysResource) {
        this.id = sysResource.getId();
        this.resFid = sysResource.getResFid();
        this.label = sysResource.getResName();
        this.resUrl = sysResource.getResUrl();
        this.children = Lists.newArrayList();
    }

    private List<ResNode> children;


    public void setId(Long id) {
        this.id = id;
    }

    public void setResFid(Long resFid) {
        this.resFid = resFid;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public void setChildren(List<ResNode> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public Long getResFid() {
        return resFid;
    }

    public String getLabel() {
        return label;
    }

    public String getResDesc() {
        return resDesc;
    }

    public List<ResNode> getChildren() {
        return children;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ResNode resNode = (ResNode) o;

        return new EqualsBuilder()
                .append(id, resNode.id)
                .append(resFid, resNode.resFid)
                .append(label, resNode.label)
                .append(resDesc, resNode.resDesc)
                .append(children, resNode.children)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(resFid)
                .append(label)
                .append(resDesc)
                .append(children)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ResNode{" +
                "id=" + id +
                ", resFid=" + resFid +
                ", label='" + label + '\'' +
                ", resDesc='" + resDesc + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", children=" + children +
                '}';
    }
}
