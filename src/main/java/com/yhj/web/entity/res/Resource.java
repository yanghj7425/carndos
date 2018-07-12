package com.yhj.web.entity.res;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Resource implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 资源名称
     */
    @Column(name = "res_name")
    private String resName;

    /**
     * 资源类型
     */
    @Column(name = "res_type")
    private String resType;

    /**
     * 资源访问URL
     */
    @Column(name = "res_url")
    private String resUrl;

    /**
     * 资源状态: 1 有效, 0 无效
     */
    @Column(name = "res_status")
    private Integer resStatus;

    /**
     * 资源描述
     */
    @Column(name = "res_desc")
    private String resDesc;

    /**
     * 创建时间
     */
    @Column(name = "res_create_time")
    private Date resCreateTime;

    /**
     * 删除时间
     */
    @Column(name = "res_delete_time")
    private Date resDeleteTime;

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
     * 获取资源名称
     *
     * @return res_name - 资源名称
     */
    public String getResName() {
        return resName;
    }

    /**
     * 设置资源名称
     *
     * @param resName 资源名称
     */
    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    /**
     * 获取资源类型
     *
     * @return res_type - 资源类型
     */
    public String getResType() {
        return resType;
    }

    /**
     * 设置资源类型
     *
     * @param resType 资源类型
     */
    public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }

    /**
     * 获取资源访问URL
     *
     * @return res_url - 资源访问URL
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 设置资源访问URL
     *
     * @param resUrl 资源访问URL
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl == null ? null : resUrl.trim();
    }

    /**
     * 获取资源状态: 1 有效, 0 无效
     *
     * @return res_status - 资源状态: 1 有效, 0 无效
     */
    public Integer getResStatus() {
        return resStatus;
    }

    /**
     * 设置资源状态: 1 有效, 0 无效
     *
     * @param resStatus 资源状态: 1 有效, 0 无效
     */
    public void setResStatus(Integer resStatus) {
        this.resStatus = resStatus;
    }

    /**
     * 获取资源描述
     *
     * @return res_desc - 资源描述
     */
    public String getResDesc() {
        return resDesc;
    }

    /**
     * 设置资源描述
     *
     * @param resDesc 资源描述
     */
    public void setResDesc(String resDesc) {
        this.resDesc = resDesc == null ? null : resDesc.trim();
    }

    /**
     * 获取创建时间
     *
     * @return res_create_time - 创建时间
     */
    public Date getResCreateTime() {
        return resCreateTime;
    }

    /**
     * 设置创建时间
     *
     * @param resCreateTime 创建时间
     */
    public void setResCreateTime(Date resCreateTime) {
        this.resCreateTime = resCreateTime;
    }

    /**
     * 获取删除时间
     *
     * @return res_delete_time - 删除时间
     */
    public Date getResDeleteTime() {
        return resDeleteTime;
    }

    /**
     * 设置删除时间
     *
     * @param resDeleteTime 删除时间
     */
    public void setResDeleteTime(Date resDeleteTime) {
        this.resDeleteTime = resDeleteTime;
    }


    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resName='" + resName + '\'' +
                ", resType='" + resType + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", resStatus=" + resStatus +
                ", resDesc='" + resDesc + '\'' +
                ", resCreateTime=" + resCreateTime +
                ", resDeleteTime=" + resDeleteTime +
                '}';
    }
}