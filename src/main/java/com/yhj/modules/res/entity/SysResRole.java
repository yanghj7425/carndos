package com.yhj.modules.res.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * This class holds the mapping of resources and roles submitted by the web client
 */
public class SysResRole implements Serializable {
    private static final long serialVersionUID = -3802489055911093129L;
    // 主键
    private Integer id;

    // 角色 ID
    private String roleId;

    // 资源 ID
    private String resId;

    // 资源状态
    private String resStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SysResRole that = (SysResRole) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(roleId, that.roleId)
                .append(resId, that.resId)
                .append(resStatus, that.resStatus)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(roleId)
                .append(resId)
                .append(resStatus)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SysResRole{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", redId='" + resId + '\'' +
                ", resStatus='" + resStatus + '\'' +
                '}';
    }
}
