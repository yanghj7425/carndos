package com.carndos.modules.res.pojo;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @description 角色资源映射
 */
@Data
public class ResRoleBO implements Serializable {

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


    public ResRoleBO(Long id, String roleName, String resUrl, String resName, String resDesc) {
        this.id = id;
        this.roleName = roleName;
        this.resUrl = resUrl;
        this.resName = resName;
        this.resDesc = resDesc;
    }
}