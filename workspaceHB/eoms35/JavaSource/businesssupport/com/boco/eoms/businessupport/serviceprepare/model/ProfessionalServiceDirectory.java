package com.boco.eoms.businessupport.serviceprepare.model;

import java.io.Serializable;

/**
 * 专业服务目录
 *
 * @author yangliangliang
 */

public class ProfessionalServiceDirectory implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 专业服务名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标示
     */
    private String deleted;
    /**
     * 排序
     */
    private String sortId;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

}
