package com.yhj.modules.res.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @description 角色资源映射
 */
public class ResRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 映射id
     */
    private Long id;

    /**
     * 角色
     */
    private String roleName;

    /**
     * 资源 URL
     */
    private String resUrl;


    /**
     * 资源名称
     */
    private String resName;


    /**
     * 资源描述
     */
    private String resDesc;


    public ResRole(Long id, String roleName, String resUrl, String resName, String resDesc) {
        this.id = id;
        this.roleName = roleName;
        this.resUrl = resUrl;
        this.resName = resName;
        this.resDesc = resDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ResRole resRole = (ResRole) o;

        return new EqualsBuilder()
                .append(id, resRole.id)
                .append(roleName, resRole.roleName)
                .append(resUrl, resRole.resUrl)
                .append(resName, resRole.resName)
                .append(resDesc, resRole.resDesc)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(roleName)
                .append(resUrl)
                .append(resName)
                .append(resDesc)
                .toHashCode();
    }
}