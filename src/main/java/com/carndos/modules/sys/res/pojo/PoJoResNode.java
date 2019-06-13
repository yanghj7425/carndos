package com.carndos.modules.sys.res.pojo;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PoJoResNode implements Serializable {

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
     * 1 有效, 0 无效
     * sysResource status
     */
    private Integer resStatus;


    private List<PoJoResNode> children;

    public PoJoResNode() {
        children = CollectionUtil.newArrayList();
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

    public List<PoJoResNode> getChildren() {
        return children;
    }

    public void setChildren(List<PoJoResNode> children) {
        this.children = children;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PoJoResNode poJoResNode = (PoJoResNode) o;

        return new EqualsBuilder()
                .append(id, poJoResNode.id)
                .append(resFid, poJoResNode.resFid)
                .append(label, poJoResNode.label)
                .append(resDesc, poJoResNode.resDesc)
                .append(resUrl, poJoResNode.resUrl)
                .append(resStatus, poJoResNode.resStatus)
                .append(children, poJoResNode.children)
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
        return "PoJoResNode{" +
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
