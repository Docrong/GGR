package com.boco.eoms.businessupport.serviceprepare.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程任务表
 *
 * @author yangliangliang
 */
public class ProcessTasks implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 工单id
     */
    private String sheetkey;
    /**
     * 服务id
     */
    private String servicId;
    /**
     * 服务中文名
     */
    private String serviceCnName;
    /**
     * parentTaskId
     */
    private String parentTaskId;
    /**
     * 流程类型表id
     */
    private String processTypeId;
    /**
     * 任务单状态
     */
    private String status;
    /**
     * 开始时间
     */
    private Date createtime;
    /**
     * 结束时间
     */
    private Date endtime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 处理人
     */
    private String dealRoleId;
    /**
     * 产生的流程任务Id
     */
    private String parentLinkId;
    /**
     * 删除标示
     */
    private String deleted;

    public String getParentLinkId() {
        return parentLinkId;
    }

    public void setParentLinkId(String parentLinkId) {
        this.parentLinkId = parentLinkId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServicId() {
        return servicId;
    }

    public void setServicId(String servicId) {
        this.servicId = servicId;
    }

    public String getSheetkey() {
        return sheetkey;
    }

    public void setSheetkey(String sheetkey) {
        this.sheetkey = sheetkey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDealRoleId() {
        return dealRoleId;
    }

    public void setDealRoleId(String dealRoleId) {
        this.dealRoleId = dealRoleId;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getServiceCnName() {
        return serviceCnName;
    }

    public void setServiceCnName(String serviceCnName) {
        this.serviceCnName = serviceCnName;
    }

}
