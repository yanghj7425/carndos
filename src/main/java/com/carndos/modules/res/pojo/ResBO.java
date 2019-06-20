package com.carndos.modules.res.pojo;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ResBO implements Serializable {

    private static final long serialVersionUID = -291851707773983421L;
    /**
     * sysResource ID
     */
    private Long id;

    /**
     * sysResource FID
     */
    private Long resFid;

    /**
     * sysResource Name
     */
    private String label;

    /**
     * sysResource Desc
     */
    private String resDesc;

    /**
     * sysResource URL
     */
    private String resUrl;

    /**
     * 1 有效, 0 无效
     * sysResource status
     */
    private Integer resStatus;


    private List<ResBO> children;

    public ResBO() {
        children = CollectionUtil.newArrayList();
    }

    public ResBO(Long id, Long resFid, String label, String resDesc, String resUrl, Integer resStatus, List<ResBO> children) {
        this.id = id;
        this.resFid = resFid;
        this.label = label;
        this.resDesc = resDesc;
        this.resUrl = resUrl;
        this.resStatus = resStatus;
        this.children = children;
    }
}
