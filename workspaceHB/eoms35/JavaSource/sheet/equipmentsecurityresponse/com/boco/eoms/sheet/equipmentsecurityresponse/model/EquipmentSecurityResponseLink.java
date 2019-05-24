package com.boco.eoms.sheet.equipmentsecurityresponse.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Description:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Tue Apr 25 11:45:21 CST 2017
 * </p>
 * 
 * @author liuyonggnag
 * @version 3.6
 * 
 */
 
 public class EquipmentSecurityResponseLink extends BaseLink {
	
	/**
	*
	* 审核结果
	*
	*/     
	private java.lang.String linkAuditResult;									
	
	/**
	*
	* 审核意见
	*
	*/     
	private java.lang.String linkAuditOpinion;									
	
	/**
	*
	* 挂起状态
	*
	*/     
	private java.lang.String linkUpStuts;									
	
	/**
	*
	* 挂起原因
	*
	*/     
	private java.lang.String linkUpReason;									
	
	/**
	*
	* 操作描述
	*
	*/     
	private java.lang.String linkOperation;									
	
	/**
	*
	* 解除说明
	*
	*/     
	private java.lang.String linkRelieveState;									
	
	/**
	*
	* 最长处理时间
	*
	*/     
	private java.util.Date mainDealTime;									
	
	/**
	*
	* 预留字段1
	*
	*/     
	private java.lang.String linkExtend1;									
	
	/**
	*
	* 预留字段1
	*
	*/     
	private java.lang.String linkExtend2;									
	
	/**
	*
	* 预留字段1
	*
	*/     
	private java.lang.String linkExtend3;									
	
	/**
	*
	* 预留字段1
	*
	*/     
	private java.lang.String linkExtend4;									
	
	/**
	*
	* 预留字段1
	*
	*/     
	private java.lang.String linkExtend5;									
	
	/**
	*
	* 回复对象
	*
	*/     
	private java.lang.String linkReplyObj;									
	
	/**
	*
	* 回复说明
	*
	*/     
	private java.lang.String linkReplyExp;									
	
	/**
	*
	* 采取措施的时间点
	*
	*/     
	private java.util.Date linkMeasuresTime;									
	
	/**
	*
	* 故障消除时间
	*
	*/     
	private java.util.Date linkRemoveTime;									
	
	/**
	*
	* 故障原因
	*
	*/     
	private java.lang.String linkFaultCause;									
	
	/**
	*
	* 处理措施
	*
	*/     
	private java.lang.String linkDealMeasures;									
	
	/**
	*
	* 是否为最终解决方案
	*
	*/     
	private java.lang.String linkIfSolution;									
	
	/**
	*
	* 备注
	*
	*/     
	private java.lang.String linkRemark;									
	
	/**
	*
	* 质检结果
	*
	*/     
	private java.lang.String linkQualityResult;									
	
	/**
	*
	* 质检概述
	*
	*/     
	private java.lang.String linkQualityView;									
	
	/**
	*
	* 工单驳回描述
	*
	*/     
	private java.lang.String linkReject;									

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String linkSendObject;
		
	public void setLinkAuditResult(java.lang.String linkAuditResult) {
		this.linkAuditResult= linkAuditResult; 
	}
 	
	public java.lang.String getLinkAuditResult() {
		return this.linkAuditResult;
 	}	 	
		
	public void setLinkAuditOpinion(java.lang.String linkAuditOpinion) {
		this.linkAuditOpinion= linkAuditOpinion; 
	}
 	
	public java.lang.String getLinkAuditOpinion() {
		return this.linkAuditOpinion;
 	}	 	
		
	public void setLinkUpStuts(java.lang.String linkUpStuts) {
		this.linkUpStuts= linkUpStuts; 
	}
 	
	public java.lang.String getLinkUpStuts() {
		return this.linkUpStuts;
 	}	 	
		
	public void setLinkUpReason(java.lang.String linkUpReason) {
		this.linkUpReason= linkUpReason; 
	}
 	
	public java.lang.String getLinkUpReason() {
		return this.linkUpReason;
 	}	 	
		
	public void setLinkOperation(java.lang.String linkOperation) {
		this.linkOperation= linkOperation; 
	}
 	
	public java.lang.String getLinkOperation() {
		return this.linkOperation;
 	}	 	
		
	public void setLinkRelieveState(java.lang.String linkRelieveState) {
		this.linkRelieveState= linkRelieveState; 
	}
 	
	public java.lang.String getLinkRelieveState() {
		return this.linkRelieveState;
 	}	 	
		
	public void setMainDealTime(java.util.Date mainDealTime) {
		this.mainDealTime= mainDealTime; 
	}
 	
	public java.util.Date getMainDealTime() {
		return this.mainDealTime;
 	}	 	
		
	public void setLinkExtend1(java.lang.String linkExtend1) {
		this.linkExtend1= linkExtend1; 
	}
 	
	public java.lang.String getLinkExtend1() {
		return this.linkExtend1;
 	}	 	
		
	public void setLinkExtend2(java.lang.String linkExtend2) {
		this.linkExtend2= linkExtend2; 
	}
 	
	public java.lang.String getLinkExtend2() {
		return this.linkExtend2;
 	}	 	
		
	public void setLinkExtend3(java.lang.String linkExtend3) {
		this.linkExtend3= linkExtend3; 
	}
 	
	public java.lang.String getLinkExtend3() {
		return this.linkExtend3;
 	}	 	
		
	public void setLinkExtend4(java.lang.String linkExtend4) {
		this.linkExtend4= linkExtend4; 
	}
 	
	public java.lang.String getLinkExtend4() {
		return this.linkExtend4;
 	}	 	
		
	public void setLinkExtend5(java.lang.String linkExtend5) {
		this.linkExtend5= linkExtend5; 
	}
 	
	public java.lang.String getLinkExtend5() {
		return this.linkExtend5;
 	}	 	
		
	public void setLinkReplyObj(java.lang.String linkReplyObj) {
		this.linkReplyObj= linkReplyObj; 
	}
 	
	public java.lang.String getLinkReplyObj() {
		return this.linkReplyObj;
 	}	 	
		
	public void setLinkReplyExp(java.lang.String linkReplyExp) {
		this.linkReplyExp= linkReplyExp; 
	}
 	
	public java.lang.String getLinkReplyExp() {
		return this.linkReplyExp;
 	}	 	
		
	public void setLinkMeasuresTime(java.util.Date linkMeasuresTime) {
		this.linkMeasuresTime= linkMeasuresTime; 
	}
 	
	public java.util.Date getLinkMeasuresTime() {
		return this.linkMeasuresTime;
 	}	 	
		
	public void setLinkRemoveTime(java.util.Date linkRemoveTime) {
		this.linkRemoveTime= linkRemoveTime; 
	}
 	
	public java.util.Date getLinkRemoveTime() {
		return this.linkRemoveTime;
 	}	 	
		
	public void setLinkFaultCause(java.lang.String linkFaultCause) {
		this.linkFaultCause= linkFaultCause; 
	}
 	
	public java.lang.String getLinkFaultCause() {
		return this.linkFaultCause;
 	}	 	
		
	public void setLinkDealMeasures(java.lang.String linkDealMeasures) {
		this.linkDealMeasures= linkDealMeasures; 
	}
 	
	public java.lang.String getLinkDealMeasures() {
		return this.linkDealMeasures;
 	}	 	
		
	public void setLinkIfSolution(java.lang.String linkIfSolution) {
		this.linkIfSolution= linkIfSolution; 
	}
 	
	public java.lang.String getLinkIfSolution() {
		return this.linkIfSolution;
 	}	 	
		
	public void setLinkRemark(java.lang.String linkRemark) {
		this.linkRemark= linkRemark; 
	}
 	
	public java.lang.String getLinkRemark() {
		return this.linkRemark;
 	}	 	
		
	public void setLinkQualityResult(java.lang.String linkQualityResult) {
		this.linkQualityResult= linkQualityResult; 
	}
 	
	public java.lang.String getLinkQualityResult() {
		return this.linkQualityResult;
 	}	 	
		
	public void setLinkQualityView(java.lang.String linkQualityView) {
		this.linkQualityView= linkQualityView; 
	}
 	
	public java.lang.String getLinkQualityView() {
		return this.linkQualityView;
 	}	 	
		
	public void setLinkReject(java.lang.String linkReject) {
		this.linkReject= linkReject; 
	}
 	
	public java.lang.String getLinkReject() {
		return this.linkReject;
 	}	 	

	
	public java.lang.String getLinkSendObject() {
		return linkSendObject;
	}

	public void setLinkSendObject(java.lang.String linkSendObject) {
		this.linkSendObject = linkSendObject;
	}	 
}