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
 * Title:
 * </p>
 * <p>
 * Description: wps中各个流程中，记录用户每次操作的bo的基类，所有流程中的link对象均需要继承该对象 方法
 * 1.在自己的Library中的Dependencies中将eomsTemplate加入。
 * 2.是在BO-->Properties-->Desciption-->Inherit from中选中本基类。 3.在自己的linkbo中填入自己的特色字段
 * </p>
 * <p>
 * String:2007-8-3 10:42:26
 * </p>
 * 
 * @author 陈元蜀
 * @version 1.0
 *  
 */
public class LinkForm extends BaseForm implements java.io.Serializable {

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 关联main id
	 */
	private String mainId;

	/**
	 * 接收时限
	 */
	private String acceptLimit;

	/**
	 * 完成时限
	 */
	private String completeLimit;

	/**
	 * 操作名称
	 */
	private String operateType;

	/**
	 * 操作时间
	 */
	private String operateTime;

	/**
	 * 操作组织类型,用户、角色、部门
	 */
	private String operateOrgType;

	/**
	 * 操作者用户ID
	 */
	private String operateUserId;

	/**
	 * 操作者部门ID
	 */
	private String operateDeptId;

	/**
	 * 操作者角色id
	 */
	private String operateRoleId;

	/**
	 * 目的对象的类型 部门 角色 人
	 */
	private String toOrgType;

	/**
	 * 目的用户id
	 */
	private String toOrgUserId;

	/**
	 * 目的部门id
	 */
	private String toOrgDeptId;

	/**
	 * 目的角色id
	 */
	private String toOrgRoleId;

	/**
	 * 
	 * 接单类型,不超时,超时
	 */
	private String acceptFlag;

	/**
	 * 接单时间
	 */
	private String acceptTime;

	/**
	 * 完成超时类型 超时 正常
	 */
	private String completeFlag;

	/**
	 * 完成时间
	 */
	private String completeTime;

	/**
	 * 上一条link id
	 */
	private String preLinkId;

	/**
	 * 父流程的linkid 即产生子流程的那条link的主键
	 */
	private String parentLinkId;

	/**
	 * 对应每个工单中的第一条link的主键
	 */
	private String firstLinkId;

	/**
	 * 流程实例号，根据所属的流程不同而不同（不同实例或者不同的父子流程中）
	 */
	private String piid;

	/**
	 * 操作所对应的active的实例id
	 */
	private String aiid;

	/**
	 * WPS的stepID，通常都是一个humantask的id，注意这个id不是humantask的实例id
	 */
	private String activeTemplateId;

	/**
	 * 模板名称 为将来可能出现的模板方式预留，在卓越流程中暂时还没有发现有用到
	 */
	private String templateName;

	/**
	 * 用户信息
	 */
	private TawSystemSessionForm tawSystemSessionForm;

	/**
	 * 联系方式
	 */
	private String contact;

	/**
	 * 转派理由
	 */
	private String transmitReason;

	/**
	 * 驳回理由
	 */
	private String linkRejectCause;

	/**
	 * 处理结果
	 */
	private String linkResult;

	/**
	 * 处理结果
	 */
	private String auditOpinion;

	/**
	 * 阶段ID
	 */

	private String phaseId;

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
     * 移交说明
     * add by zhangying
     */
    private String transferReason;

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
	 * @param completeFlag
	 *            The completeFlag to set.
	 */
	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}

	/**
	 * @return the linkRejectCause
	 */
	public String getAuditOpinion() {
		return auditOpinion;
	}

	/**
	 * @param linkRejectCause
	 *            the linkRejectCause to set
	 */
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	/**
	 * @return the linkRejectCause
	 */
	public String getLinkRejectCause() {
		return linkRejectCause;
	}

	/**
	 * @param linkRejectCause
	 *            the linkRejectCause to set
	 */
	public void setLinkRejectCause(String linkRejectCause) {
		this.linkRejectCause = linkRejectCause;
	}

	/**
	 * @return the linkResult
	 */
	public String getLinkResult() {
		return linkResult;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setLinkResult(String linkResult) {
		this.linkResult = linkResult;
	}

	/**
	 * @return the contact
	 */
	public String getTransmitReason() {
		return transmitReason;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setTransmitReason(String transmitReason) {
		this.transmitReason = transmitReason;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

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
	 * @return the acceptTime
	 */
	public String getAcceptTime() {
		return acceptTime;
	}

	/**
	 * @param acceptTime
	 *            the acceptTime to set
	 */
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * @return the acceptFlag
	 */
	public String getAcceptFlag() {
		return acceptFlag;
	}

	/**
	 * @param acceptType
	 *            the acceptType to set
	 */
	public void setAcceptFlag(String acceptFlag) {
		this.acceptFlag = acceptFlag;
	}

	/**
	 * @return the activeTemplateId
	 */
	public String getActiveTemplateId() {
		return activeTemplateId;
	}

	/**
	 * @param activeTemplateId
	 *            the activeTemplateId to set
	 */
	public void setActiveTemplateId(String activeTemplateId) {
		this.activeTemplateId = activeTemplateId;
	}

	/**
	 * @return the aiid
	 */
	public String getAiid() {
		return aiid;
	}

	/**
	 * @param aiid
	 *            the aiid to set
	 */
	public void setAiid(String aiid) {
		this.aiid = aiid;
	}

	/**
	 * @return the commpleteFlag
	 */
	public String getCompleteFlag() {
		return completeFlag;
	}

	/**
	 * @param commpleteFlag
	 *            the commpleteFlag to set
	 */
	public void setCommpleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
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
	 * @return the completeTime
	 */
	public String getCompleteTime() {
		return completeTime;
	}

	/**
	 * @param completeTime
	 *            the completeTime to set
	 */
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	/**
	 * @return the firstLinkId
	 */
	public String getFirstLinkId() {
		return firstLinkId;
	}

	/**
	 * @param firstLinkId
	 *            the firstLinkId to set
	 */
	public void setFirstLinkId(String firstLinkId) {
		this.firstLinkId = firstLinkId;
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
	 * @return the mainId
	 */
	public String getMainId() {
		return mainId;
	}

	/**
	 * @param mainId
	 *            the mainId to set
	 */
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	/**
	 * @return the operateDeptId
	 */
	public String getOperateDeptId() {
		return operateDeptId;
	}

	/**
	 * @param operateDeptId
	 *            the operateDeptId to set
	 */
	public void setOperateDeptId(String operateDeptId) {
		this.operateDeptId = operateDeptId;
	}

	/**
	 * @return the operateOrgType
	 */
	public String getOperateOrgType() {
		return operateOrgType;
	}

	/**
	 * @param operateOrgType
	 *            the operateOrgType to set
	 */
	public void setOperateOrgType(String operateOrgType) {
		this.operateOrgType = operateOrgType;
	}

	/**
	 * @return the operateRoleId
	 */
	public String getOperateRoleId() {
		return operateRoleId;
	}

	/**
	 * @param operateRoleId
	 *            the operateRoleId to set
	 */
	public void setOperateRoleId(String operateRoleId) {
		this.operateRoleId = operateRoleId;
	}

	/**
	 * @return the operateTime
	 */
	public String getOperateTime() {
		return operateTime;
	}

	/**
	 * @param operateTime
	 *            the operateTime to set
	 */
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * @return the operateType
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * @param operateType
	 *            the operateType to set
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * @return the operateUserId
	 */
	public String getOperateUserId() {
		return operateUserId;
	}

	/**
	 * @param operateUserId
	 *            the operateUserId to set
	 */
	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

	/**
	 * @return the parentLinkId
	 */
	public String getParentLinkId() {
		return parentLinkId;
	}

	/**
	 * @param parentLinkId
	 *            the parentLinkId to set
	 */
	public void setParentLinkId(String parentLinkId) {
		this.parentLinkId = parentLinkId;
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
	 * @return the preLinkId
	 */
	public String getPreLinkId() {
		return preLinkId;
	}

	/**
	 * @param preLinkId
	 *            the preLinkId to set
	 */
	public void setPreLinkId(String preLinkId) {
		this.preLinkId = preLinkId;
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
	 * @return the toOrgDeptId
	 */
	public String getToOrgDeptId() {
		return toOrgDeptId;
	}

	/**
	 * @param toOrgDeptId
	 *            the toOrgDeptId to set
	 */
	public void setToOrgDeptId(String toOrgDeptId) {
		this.toOrgDeptId = toOrgDeptId;
	}

	/**
	 * @return the toOrgRoleId
	 */
	public String getToOrgRoleId() {
		return toOrgRoleId;
	}

	/**
	 * @param toOrgRoleId
	 *            the toOrgRoleId to set
	 */
	public void setToOrgRoleId(String toOrgRoleId) {
		this.toOrgRoleId = toOrgRoleId;
	}

	/**
	 * @return the toOrgType
	 */
	public String getToOrgType() {
		return toOrgType;
	}

	/**
	 * @param toOrgType
	 *            the toOrgType to set
	 */
	public void setToOrgType(String toOrgType) {
		this.toOrgType = toOrgType;
	}

	/**
	 * @return the toOrgUserId
	 */
	public String getToOrgUserId() {
		return toOrgUserId;
	}

	/**
	 * @param toOrgUserId
	 *            the toOrgUserId to set
	 */
	public void setToOrgUserId(String toOrgUserId) {
		this.toOrgUserId = toOrgUserId;
	}

	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}

}
