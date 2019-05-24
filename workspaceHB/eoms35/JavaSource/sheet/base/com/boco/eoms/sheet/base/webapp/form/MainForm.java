/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

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
 * String:2007-8-3 10:24:03
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class MainForm extends BaseForm implements java.io.Serializable {

    /**
     * @return the ishold
     */
    public String getIshold() {
        return ishold;
    }

    /**
     * @param ishold
     *            the ishold to set
     */
    public void setIshold(String ishold) {
        this.ishold = ishold;
    }

    /**
     * @return the sendPostId
     */
    public String getSendPostId() {
        return sendPostId;
    }

    /**
     * @param sendPostId
     *            the sendPostId to set
     */
    public void setSendPostId(String sendPostId) {
        this.sendPostId = sendPostId;
    }

    private String sendPostId;

    private String ishold;

    private String endPostId;

    private String result;

    private String holdStatisfied;
    
    private String toDeptId;
    
    private String cancelReason;
    

	/**
	 * @return Returns the toDeptId.
	 */
	public String getToDeptId() {
		return toDeptId;
	}
	/**
	 * @param toDeptId The toDeptId to set.
	 */
	public void setToDeptId(String toDeptId) {
		this.toDeptId = toDeptId;
	}
    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    private String id;

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
    private String acceptLimit;

    /**
     * 完成时限
     */
    private String completeLimit;

    /**
     * 附件地址
     */
    private String accessories;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送组织类型，用户，部门，角色
     */
    private String sendOrgType;

    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 发送部门id,注：由于dept的主键为int故这里使用String类型，建议以后改为统一string
     */
    private String sendDeptId;

    /**
     * 发送角色ID,注：由于role的主键为int故这里使用String类型，建议以后改为统一string
     */
    private String sendRoleId;

    /**
     * 发送人联系方式
     */
    private String sendContact;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 结束信息（归档内容）
     */
    private String endResult;

    /**
     * 工单状态，未结束,已归档,已结束未归档
     */
    private String status;

    /**
     * 结束工单用户id
     */
    private String endUserId;

    /**
     * 结束工单用户部门
     */
    private String endDeptId;

    /**
     * 结束工单用户角色
     */
    private String endRoleId;

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
    private String parentSheetId;

    /**
     * 模板名称（预留）
     */
    private String templateName;

    /**
     * 要启动的流程模板名称
     */
    private String processTemplateName;

    /**
     * 要启动的流程模板对应的操作名称
     */
    private String operateName;

    /**
     * 页面所选择的处理对象名称
     */
    private String dealPerformer;

    /**
     * 页面所选择的抄送对象名称
     */
    private String copyPerformer;

    /**
     * 页面所选择的审核对象名称
     */
    private String auditPerformer;

    /**
     * 页面上所选择的要驳回的阶段ID
     */
    private String phaseId;

    /**
     * @return Returns the phaseId.
     */
    public String getPhaseId() {
        return phaseId;
    }

    /**
     * @param phaseId
     *            The phaseId to set.
     */
    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    /**
     * 流程对应的aiid
     * 
     * @return
     */
    private String aiid;

    /**
     * @return Returns the aiid.
     */
    public String getAiid() {
        return aiid;
    }

    /**
     * @param aiid
     *            The aiid to set.
     */
    public void setAiid(String aiid) {
        this.aiid = aiid;
    }

    /**
     * @return Returns the auditPerformer.
     */
    public String getAuditPerformer() {
        return auditPerformer;
    }

    /**
     * @param auditPerformer
     *            The auditPerformer to set.
     */
    public void setAuditPerformer(String auditPerformer) {
        this.auditPerformer = auditPerformer;
    }

    /**
     * @return Returns the copyPerformer.
     */
    public String getCopyPerformer() {
        return copyPerformer;
    }

    /**
     * @param copyPerformer
     *            The copyPerformer to set.
     */
    public void setCopyPerformer(String copyPerformer) {
        this.copyPerformer = copyPerformer;
    }

    /**
     * @return Returns the dealPerformer.
     */
    public String getDealPerformer() {
        return dealPerformer;
    }

    /**
     * @param dealPerformer
     *            The dealPerformer to set.
     */
    public void setDealPerformer(String dealPerformer) {
        this.dealPerformer = dealPerformer;
    }

    /**
     * @return Returns the operateName.
     */
    public String getOperateName() {
        return operateName;
    }

    /**
     * @param operateName
     *            The operateName to set.
     */
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    /**
     * @return Returns the processTemplateName.
     */
    public String getProcessTemplateName() {
        return processTemplateName;
    }

    /**
     * @param processTemplateName
     *            The processTemplateName to set.
     */
    public void setProcessTemplateName(String processTemplateName) {
        this.processTemplateName = processTemplateName;
    }

    /**
     * 用户信息
     */
    private TawSystemSessionForm tawSystemSessionForm;

    /**
     * @return the acceptLimit
     */
    public TawSystemSessionForm getTawSystemSessionForm() {
        return tawSystemSessionForm;
    }

    /**
     * @param acceptLimit
     *            the acceptLimit to set
     */
    public void setTawSystemSessionForm(
            TawSystemSessionForm tawSystemSessionForm) {
        this.tawSystemSessionForm = tawSystemSessionForm;
    }

    /**
     * @return the acceptLimit
     */
    public String getAcceptLimit() {
        return acceptLimit;
    }

    /**
     * @param acceptLimit
     *            the acceptLimit to set
     */
    public void setAcceptLimit(String acceptLimit) {
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
    public String getCompleteLimit() {
        return completeLimit;
    }

    /**
     * @param completeLimit
     *            the completeLimit to set
     */
    public void setCompleteLimit(String completeLimit) {
        this.completeLimit = completeLimit;
    }

    /**
     * @return the endDeptId
     */
    public String getEndDeptId() {
        return endDeptId;
    }

    /**
     * @param endDeptId
     *            the endDeptId to set
     */
    public void setEndDeptId(String endDeptId) {
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
    public String getEndRoleId() {
        return endRoleId;
    }

    /**
     * @param endRoleId
     *            the endRoleId to set
     */
    public void setEndRoleId(String endRoleId) {
        this.endRoleId = endRoleId;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime) {
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

    public String getHoldStatisfied() {
        return holdStatisfied;
    }

    public void setHoldStatisfied(String holdStatisfied) {
        this.holdStatisfied = holdStatisfied;
    }

    /**
     * @return the parentSheetId
     */
    public String getParentSheetId() {
        return parentSheetId;
    }

    /**
     * @param parendSheetId
     *            the parendSheetId to set
     */
    public void setParentSheetId(String parentSheetId) {
        this.parentSheetId = parentSheetId;
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
    public String getSendDeptId() {
        return sendDeptId;
    }

    /**
     * @param sendDeptId
     *            the sendDeptId to set
     */
    public void setSendDeptId(String sendDeptId) {
        this.sendDeptId = sendDeptId;
    }

    /**
     * @return the sendRoleId
     */
    public String getSendRoleId() {
        return sendRoleId;
    }

    /**
     * @param sendRoleId
     *            the sendRoleId to set
     */
    public void setSendRoleId(String sendRoleId) {
        this.sendRoleId = sendRoleId;
    }

    /**
     * @return the sendTime
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     *            the sendTime to set
     */
    public void setSendTime(String sendTime) {
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
     * @param deleted
     *            the deleted to set
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
     * @param sendOrgType
     *            the sendOrgType to set
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
     * @param status
     *            the status to set
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

    /**
     * @return the endPostId
     */
    public String getEndPostId() {
        return endPostId;
    }

    /**
     * @param endPostId
     *            the endPostId to set
     */
    public void setEndPostId(String endPostId) {
        this.endPostId = endPostId;
    }

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
}
