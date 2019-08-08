package com.boco.eoms.km.kmAuditerStep.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识管理审核步骤
 * </p>
 * <p>
 * Description:知识管理审核步骤
 * </p>
 * <p>
 * Mon May 04 14:42:01 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 戴志刚
 * @moudle.getVersion() 1.0
 */
public class KmAuditerStepForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 锟斤拷锟�
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 知识库主键id
     */
    private String kmId;

    public String getKmId() {
        return kmId;
    }

    public void setKmId(String kmId) {
        this.kmId = kmId;
    }

    /**
     * 创建时间
     */
    private java.lang.String createTime;

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getCreateTime() {
        return this.createTime;
    }

    /**
     * 操作时间
     */
    private java.lang.String operateTime;

    public void setOperateTime(java.lang.String operateTime) {
        this.operateTime = operateTime;
    }

    public java.lang.String getOperateTime() {
        return this.operateTime;
    }

    /**
     * 操作人类型
     */
    private java.lang.String operateType;

    public void setOperateType(java.lang.String operateType) {
        this.operateType = operateType;
    }

    public java.lang.String getOperateType() {
        return this.operateType;
    }

    /**
     * 操作人
     */
    private java.lang.String operateId;

    public void setOperateId(java.lang.String operateId) {
        this.operateId = operateId;
    }

    public java.lang.String getOperateId() {
        return this.operateId;
    }


    /**
     * 下一步操作者类型
     */
    private java.lang.String toOrgType;

    public void setToOrgType(java.lang.String toOrgType) {
        this.toOrgType = toOrgType;
    }

    public java.lang.String getToOrgType() {
        return this.toOrgType;
    }

    /**
     * 下一步操作者
     */
    private java.lang.String toOrgId;

    public void setToOrgId(java.lang.String toOrgId) {
        this.toOrgId = toOrgId;
    }

    public java.lang.String getToOrgId() {
        return this.toOrgId;
    }

    /**
     * 审核结果
     */
    private java.lang.String auditResult;

    public void setAuditResult(java.lang.String auditResult) {
        this.auditResult = auditResult;
    }

    public java.lang.String getAuditResult() {
        return this.auditResult;
    }

    /**
     * 备注
     */
    private java.lang.String remark;

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 状态:1-未完成,2-完成
     */
    private java.lang.String state;

    public void setState(java.lang.String state) {
        this.state = state;
    }

    public java.lang.String getState() {
        return this.state;
    }

}