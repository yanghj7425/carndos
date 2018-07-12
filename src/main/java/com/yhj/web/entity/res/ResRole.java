package com.yhj.web.entity.res;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

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
    private String resRole;

    /**
     * 资源
     */
    private String resUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResRole() {
        return resRole;
    }

    public void setResRole(String resRole) {
        this.resRole = resRole;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public ResRole(Long id, String resRole, String resUrl) {
        this.id = id;
        this.resRole = resRole;
        this.resUrl = resUrl;
    }


    public ResRole(String resRole, String resUrl) {
        this.resRole = resRole;
        this.resUrl = resUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("resRole", resRole)
                .append("resUrl", resUrl)
                .toString();
    }
}