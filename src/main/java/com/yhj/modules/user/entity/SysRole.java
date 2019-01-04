package com.yhj.modules.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_role")
public class SysRole implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * 角色状态:1 有效, 0 无效
     */
    @Column(name = "role_status")
    private Integer roleStatus;

    /**
     * 角色创建时间
     */
    @Column(name = "role_create_time")
    private Date roleCreateTime;

    /**
     * 角色删除时间
     */
    @Column(name = "role_delete_time")
    private Date roleDeleteTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取角色描述
     *
     * @return role_desc - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * 获取角色状态:1 有效, 0 无效
     *
     * @return role_status - 角色状态:1 有效, 0 无效
     */
    public Integer getRoleStatus() {
        return roleStatus;
    }

    /**
     * 设置角色状态:1 有效, 0 无效
     *
     * @param roleStatus 角色状态:1 有效, 0 无效
     */
    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    /**
     * 获取角色创建时间
     *
     * @return role_create_time - 角色创建时间
     */
    public Date getRoleCreateTime() {
        return roleCreateTime;
    }

    /**
     * 设置角色创建时间
     *
     * @param roleCreateTime 角色创建时间
     */
    public void setRoleCreateTime(Date roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    /**
     * 获取角色删除时间
     *
     * @return role_delete_time - 角色删除时间
     */
    public Date getRoleDeleteTime() {
        return roleDeleteTime;
    }

    /**
     * 设置角色删除时间
     *
     * @param roleDeleteTime 角色删除时间
     */
    public void setRoleDeleteTime(Date roleDeleteTime) {
        this.roleDeleteTime = roleDeleteTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("roleName", roleName)
                .append("roleDesc", roleDesc)
                .append("roleStatus", roleStatus)
                .append("roleCreateTime", roleCreateTime)
                .append("roleDeleteTime", roleDeleteTime)
                .toString();
    }
}