/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.qo;

import java.util.Date;

/**
 * <p>
 * Title:wps中各个流程中，记录用户工单主信息的对象
 * </p>
 * <p>
 * Description:
 * wps中各个流程中，记录用户工单主信息的对象：mainBO，本类为所有mainBO对象的基类。所有流程中的lmainBO对象均需要继承该对象 方法
 * 1.在自己的Library中的Dependencies中将eomsTemplate加入。
 * 2.是在BO-->Properties-->Desciption-->Inherit from中选中本基类。 3.在自己的mainbo中填入自己的特色字段
 * </p>
 * <p>
 * Date:2007-8-3 10:24:03
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class MainQO {

    /**
     * 主键
     */
    private String id;

    /**
     * 工单id
     */
    private String sheetId;

    /**
     * 工单标题
     */
    private String title;

    /**
     * 接收时限
     */
    private Date acceptLimit;

    /**
     * 完成时限
     */
    private Date completeLimit;

    /**
     * 附件地址
     */
    private String accessories;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送组织类型，用户，部门，角色
     */
    private String sendOrgType;

    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 发送部门id,注：由于dept的主键为int故这里使用Integer类型，建议以后改为统一string
     */
    private Integer sendDeptId;

    /**
     * 发送角色ID,注：由于role的主键为int故这里使用Integer类型，建议以后改为统一string
     */
    private Integer sendRoleId;

    /**
     * 发送人联系方式
     */
    private String sendContact;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 结束信息（归档内容）
     */
    private String endResult;

    /**
     * 工单状态，未结束,已归档,已结束未归档
     */
    private String status;

    /**
     * 归档满意度
     */
    private Integer holdStatisfied;

    /**
     * 结束工单用户id
     */
    private String endUserId;

    /**
     * 结束工单用户部门
     */
    private Integer endDeptId;

    /**
     * 结束工单用户角色
     */
    private Integer endRoleId;

    /**
     * 工单有效否
     */
    private String deleted;

    /**
     * wps的流程实例号
     */
    private String piid;

    /**
     * 当A流程触发B流程时，A流程的工单名
     */
    private String parentSheetName;

    /**
     * 当A流程触发B流程时，A流程的工单流水号
     */
    private String parendSheetId;

    /**
     * 模板名称（预留）
     */
    private String templateName;

    /**
     * @return the acceptLimit
     */
    public Date getAcceptLimit() {
        return acceptLimit;
    }

    /**
     * @param acceptLimit
     *            the acceptLimit to set
     */
    public void setAcceptLimit(Date acceptLimit) {
        this.acceptLimit = acceptLimit;
    }

    /**
     * @return the accessories
     */
    public String getAccessories() {
        return accessories;
    }

    /**
     * @param accessories
     *            the accessories to set
     */
    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    /**
     * @return the completeLimit
     */
    public Date getCompleteLimit() {
        return completeLimit;
    }

    /**
     * @param completeLimit
     *            the completeLimit to set
     */
    public void setCompleteLimit(Date completeLimit) {
        this.completeLimit = completeLimit;
    }

    /**
     * @return the endDeptId
     */
    public Integer getEndDeptId() {
        return endDeptId;
    }

    /**
     * @param endDeptId
     *            the endDeptId to set
     */
    public void setEndDeptId(Integer endDeptId) {
        this.endDeptId = endDeptId;
    }

    /**
     * @return the endResult
     */
    public String getEndResult() {
        return endResult;
    }

    /**
     * @param endResult
     *            the endResult to set
     */
    public void setEndResult(String endResult) {
        this.endResult = endResult;
    }

    /**
     * @return the endRoleId
     */
    public Integer getEndRoleId() {
        return endRoleId;
    }

    /**
     * @param endRoleId
     *            the endRoleId to set
     */
    public void setEndRoleId(Integer endRoleId) {
        this.endRoleId = endRoleId;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the endUserId
     */
    public String getEndUserId() {
        return endUserId;
    }

    /**
     * @param endUserId
     *            the endUserId to set
     */
    public void setEndUserId(String endUserId) {
        this.endUserId = endUserId;
    }

    /**
     * @return the holdStatisfied
     */
    public Integer getHoldStatisfied() {
        return holdStatisfied;
    }

    /**
     * @param holdStatisfied
     *            the holdStatisfied to set
     */
    public void setHoldStatisfied(Integer holdStatisfied) {
        this.holdStatisfied = holdStatisfied;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the parendSheetId
     */
    public String getParendSheetId() {
        return parendSheetId;
    }

    /**
     * @param parendSheetId
     *            the parendSheetId to set
     */
    public void setParendSheetId(String parendSheetId) {
        this.parendSheetId = parendSheetId;
    }

    /**
     * @return the parentSheetName
     */
    public String getParentSheetName() {
        return parentSheetName;
    }

    /**
     * @param parentSheetName
     *            the parentSheetName to set
     */
    public void setParentSheetName(String parentSheetName) {
        this.parentSheetName = parentSheetName;
    }

    /**
     * @return the piid
     */
    public String getPiid() {
        return piid;
    }

    /**
     * @param piid
     *            the piid to set
     */
    public void setPiid(String piid) {
        this.piid = piid;
    }

    /**
     * @return the sendContact
     */
    public String getSendContact() {
        return sendContact;
    }

    /**
     * @param sendContact
     *            the sendContact to set
     */
    public void setSendContact(String sendContact) {
        this.sendContact = sendContact;
    }

    /**
     * @return the sendDeptId
     */
    public Integer getSendDeptId() {
        return sendDeptId;
    }

    /**
     * @param sendDeptId
     *            the sendDeptId to set
     */
    public void setSendDeptId(Integer sendDeptId) {
        this.sendDeptId = sendDeptId;
    }

 

    /**
     * @return the sendRoleId
     */
    public Integer getSendRoleId() {
        return sendRoleId;
    }

    /**
     * @param sendRoleId
     *            the sendRoleId to set
     */
    public void setSendRoleId(Integer sendRoleId) {
        this.sendRoleId = sendRoleId;
    }

    /**
     * @return the sendTime
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     *            the sendTime to set
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the sendUserId
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * @param sendUserId
     *            the sendUserId to set
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * @return the sheetId
     */
    public String getSheetId() {
        return sheetId;
    }

    /**
     * @return the deleted
     */
    public String getDeleted() {
        return deleted;
    }
    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
    /**
     * @return the sendOrgType
     */
    public String getSendOrgType() {
        return sendOrgType;
    }
    /**
     * @param sendOrgType the sendOrgType to set
     */
    public void setSendOrgType(String sendOrgType) {
        this.sendOrgType = sendOrgType;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @param sheetId
     *            the sheetId to set
     */
    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }


    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName
     *            the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
