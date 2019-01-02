package com.yhj.modules.res.pojo;

import com.google.common.collect.Lists;
import com.yhj.modules.res.entity.SysResource;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

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

    private List<ResNode> children;

    public ResNode() {
        children = Lists.newArrayList();
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

    public List<ResNode> getChildren() {
        return children;
    }

    public void setChildren(List<ResNode> children) {
        this.children = children;
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
                .append(resUrl, resNode.resUrl)
                .append(resStatus, resNode.resStatus)
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
                .append(resUrl)
                .append(resStatus)
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
                ", resStatus=" + resStatus +
                ", children=" + children +
                '}';
    }
}
