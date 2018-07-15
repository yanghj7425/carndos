package com.yhj.web.entity.res;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
    private String accessResRole;

    /**
     * 资源
     */
    private String accessResUrl;

    public ResRole(Long id, String accessResRole, String accessResUrl) {
        this.id = id;
        this.accessResRole = accessResRole;
        this.accessResUrl = accessResUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessResRole() {
        return accessResRole;
    }

    public void setAccessResRole(String accessResRole) {
        this.accessResRole = accessResRole;
    }

    public String getAccessResUrl() {
        return accessResUrl;
    }

    public void setAccessResUrl(String accessResUrl) {
        this.accessResUrl = accessResUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("accessResRole", accessResRole)
                .append("accessResUrl", accessResUrl)
                .toString();
    }
}