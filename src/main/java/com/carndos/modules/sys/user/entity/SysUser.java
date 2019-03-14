package com.carndos.modules.sys.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "sys_user")
public class SysUser implements Serializable, UserState {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "user_passwd")
    private String userPasswd;

    /**
     * 用户状态: 1 有效 , 0 无效
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 用户描述
     */
    @Column(name = "user_desc")
    private String userDesc;

    /**
     * 添加时间
     */
    @Column(name = "user_create_time")
    private Date userCreateTime;

    /**
     * 删除时间
     */
    @Column(name = "user_delete_time")
    private Date userDeleteTime;


    @Transient
    private List<SysUserRole> roles;


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
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户密码
     *
     * @return user_passwd - 用户密码
     */
    public String getUserPasswd() {
        return userPasswd;
    }

    /**
     * 设置用户密码
     *
     * @param userPasswd 用户密码
     */
    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd == null ? null : userPasswd.trim();
    }

    /**
     * 获取用户状态: 1 有效 , 0 无效
     *
     * @return user_status - 用户状态: 1 有效 , 0 无效
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态: 1 有效 , 0 无效
     *
     * @param userStatus 用户状态: 1 有效 , 0 无效
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取用户描述
     *
     * @return user_desc - 用户描述
     */
    public String getUserDesc() {
        return userDesc;
    }

    /**
     * 设置用户描述
     *
     * @param userDesc 用户描述
     */
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }

    /**
     * 获取添加时间
     *
     * @return user_create_time - 添加时间
     */
    public Date getUserCreateTime() {
        return userCreateTime;
    }

    /**
     * 设置添加时间
     *
     * @param userCreateTime 添加时间
     */
    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    /**
     * 获取删除时间
     *
     * @return user_delete_time - 删除时间
     */
    public Date getUserDeleteTime() {
        return userDeleteTime;
    }

    /**
     * 设置删除时间
     *
     * @param userDeleteTime 删除时间
     */
    public void setUserDeleteTime(Date userDeleteTime) {
        this.userDeleteTime = userDeleteTime;
    }

    public List<SysUserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysUserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .append("userPasswd", userPasswd)
                .append("roles", roles)
                .toString();
    }
}