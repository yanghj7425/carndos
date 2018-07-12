package com.yhj.web.entity.res;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResRole {

    /**
     * 资源ID
     */
    private String resRole;

    /**
     * 角色ID
     */
    private String resUrl;


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

    public ResRole(String resRole, String resUrl) {
        this.resRole = resRole;
        this.resUrl = resUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ResRole)) return false;

        ResRole resRole1 = (ResRole) o;

        return new EqualsBuilder()
                .append(resRole, resRole1.resRole)
                .append(resUrl, resRole1.resUrl)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(resRole)
                .append(resUrl)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("resRole", resRole)
                .append("resUrl", resUrl)
                .toString();
    }
}