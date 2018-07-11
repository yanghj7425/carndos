package com.yhj.web.entity.res;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @description 访问资源实体类
 * @author YHJ
 */
public class Resource {

    //访问资源地址
    private String resUrl;

    //访问资源角色
    private String resRole;


    public Resource(String resUrl, String resRole) {
        this.resUrl = resUrl;
        this.resRole = resRole;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getResRole() {
        return resRole;
    }

    public void setResRole(String resRole) {
        this.resRole = resRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return new EqualsBuilder()
                .append(resUrl, resource.resUrl)
                .append(resRole, resource.resRole)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(resUrl)
                .append(resRole)
                .toHashCode();
    }
}
