package com.boco.eoms.km.log.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识操作日统计表
 * </p>
 * <p>
 * Description:知识操作日统计表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 */
public class KmOperateDateLogForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 操作日期
     */
    private java.util.Date operateDate;

    public void setOperateDate(java.util.Date operateDate) {
        this.operateDate = operateDate;
    }

    public java.util.Date getOperateDate() {
        return this.operateDate;
    }

    /**
     * 操作人
     */
    private java.lang.String operateUser;

    public void setOperateUser(java.lang.String operateUser) {
        this.operateUser = operateUser;
    }

    public java.lang.String getOperateUser() {
        return this.operateUser;
    }

    /**
     * 操作部门
     */
    private java.lang.String operateDept;

    public void setOperateDept(java.lang.String operateDept) {
        this.operateDept = operateDept;
    }

    public java.lang.String getOperateDept() {
        return this.operateDept;
    }

    /**
     * 新增操作次数
     */
    private java.lang.Integer addCount;

    public void setAddCount(java.lang.Integer addCount) {
        this.addCount = addCount;
    }

    public java.lang.Integer getAddCount() {
        return this.addCount;
    }

    /**
     * 新增操作积分
     */
    private java.lang.Integer addScore;

    public void setAddScore(java.lang.Integer addScore) {
        this.addScore = addScore;
    }

    public java.lang.Integer getAddScore() {
        return this.addScore;
    }

    /**
     * 修改操作次数
     */
    private java.lang.Integer modifyCount;

    public void setModifyCount(java.lang.Integer modifyCount) {
        this.modifyCount = modifyCount;
    }

    public java.lang.Integer getModifyCount() {
        return this.modifyCount;
    }

    /**
     * 修改操作积分
     */
    private java.lang.Integer modifyScore;

    public void setModifyScore(java.lang.Integer modifyScore) {
        this.modifyScore = modifyScore;
    }

    public java.lang.Integer getModifyScore() {
        return this.modifyScore;
    }

    /**
     * 失效操作次数
     */
    private java.lang.Integer overCount;

    public void setOverCount(java.lang.Integer overCount) {
        this.overCount = overCount;
    }

    public java.lang.Integer getOverCount() {
        return this.overCount;
    }

    /**
     * 失效操作积分
     */
    private java.lang.Integer overScore;

    public void setOverScore(java.lang.Integer overScore) {
        this.overScore = overScore;
    }

    public java.lang.Integer getOverScore() {
        return this.overScore;
    }

    /**
     * 删除操作次数
     */
    private java.lang.Integer deleteCount;

    public void setDeleteCount(java.lang.Integer deleteCount) {
        this.deleteCount = deleteCount;
    }

    public java.lang.Integer getDeleteCount() {
        return this.deleteCount;
    }

    /**
     * 删除操作积分
     */
    private java.lang.Integer deleteScore;

    public void setDeleteScore(java.lang.Integer deleteScore) {
        this.deleteScore = deleteScore;
    }

    public java.lang.Integer getDeleteScore() {
        return this.deleteScore;
    }

    /**
     * 上传文件次数
     */
    private java.lang.Integer upCount;

    public void setUpCount(java.lang.Integer upCount) {
        this.upCount = upCount;
    }

    public java.lang.Integer getUpCount() {
        return this.upCount;
    }

    /**
     * 上传文件积分
     */
    private java.lang.Integer upScore;

    public void setUpScore(java.lang.Integer upScore) {
        this.upScore = upScore;
    }

    public java.lang.Integer getUpScore() {
        return this.upScore;
    }

    /**
     * 下载文件次数
     */
    private java.lang.Integer downCount;

    public void setDownCount(java.lang.Integer downCount) {
        this.downCount = downCount;
    }

    public java.lang.Integer getDownCount() {
        return this.downCount;
    }

    /**
     * 下载文件积分
     */
    private java.lang.Integer downScore;

    public void setDownScore(java.lang.Integer downScore) {
        this.downScore = downScore;
    }

    public java.lang.Integer getDownScore() {
        return this.downScore;
    }

    /**
     * 引用知识次数
     */
    private java.lang.Integer useCount;

    public void setUseCount(java.lang.Integer useCount) {
        this.useCount = useCount;
    }

    public java.lang.Integer getUseCount() {
        return this.useCount;
    }

    /**
     * 引用知识积分
     */
    private java.lang.Integer useScore;

    public void setUseScore(java.lang.Integer useScore) {
        this.useScore = useScore;
    }

    public java.lang.Integer getUseScore() {
        return this.useScore;
    }

    /**
     * 评价知识次数
     */
    private java.lang.Integer opinionCount;

    public void setOpinionCount(java.lang.Integer opinionCount) {
        this.opinionCount = opinionCount;
    }

    public java.lang.Integer getOpinionCount() {
        return this.opinionCount;
    }

    /**
     * 评价知识积分
     */
    private java.lang.Integer opinionScore;

    public void setOpinionScore(java.lang.Integer opinionScore) {
        this.opinionScore = opinionScore;
    }

    public java.lang.Integer getOpinionScore() {
        return this.opinionScore;
    }

    /**
     * 提出修改次数
     */
    private java.lang.Integer adviceCount;

    public void setAdviceCount(java.lang.Integer adviceCount) {
        this.adviceCount = adviceCount;
    }

    public java.lang.Integer getAdviceCount() {
        return this.adviceCount;
    }

    /**
     * 提出修改积分
     */
    private java.lang.Integer adviceScore;

    public void setAdviceScore(java.lang.Integer adviceScore) {
        this.adviceScore = adviceScore;
    }

    public java.lang.Integer getAdviceScore() {
        return this.adviceScore;
    }

    /**
     * 审核通过次数
     */
    private java.lang.Integer auditOkCount;

    public void setAuditOkCount(java.lang.Integer auditOkCount) {
        this.auditOkCount = auditOkCount;
    }

    public java.lang.Integer getAuditOkCount() {
        return this.auditOkCount;
    }

    /**
     * 审核通过积分
     */
    private java.lang.Integer auditOkScore;

    public void setAuditOkScore(java.lang.Integer auditOkScore) {
        this.auditOkScore = auditOkScore;
    }

    public java.lang.Integer getAuditOkScore() {
        return this.auditOkScore;
    }

    /**
     * 审核驳回次数
     */
    private java.lang.Integer auditBackCount;

    public void setAuditBackCount(java.lang.Integer auditBackCount) {
        this.auditBackCount = auditBackCount;
    }

    public java.lang.Integer getAuditBackCount() {
        return this.auditBackCount;
    }

    /**
     * 审核驳回积分
     */
    private java.lang.Integer auditBackScore;

    public void setAuditBackScore(java.lang.Integer auditBackScore) {
        this.auditBackScore = auditBackScore;
    }

    public java.lang.Integer getAuditBackScore() {
        return this.auditBackScore;
    }


}