package com.boco.eoms.businessupport.serviceprepare.model;

import java.io.Serializable;

/**
 * 流程类型表
 *
 * @author yangliangliang
 */
public class ProcessType implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 流程id
     */
    private String flowId;
    /**
     * 名称
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
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


}
