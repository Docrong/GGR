package com.boco.eoms.sheet.commonchangedeploy.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:通用变更配置工单
 * </p>
 * <p>
 * Description:通用变更配置工单
 * </p>
 * <p>
 * Fri Jun 12 09:08:01 CST 2009
 * </p>
 * 
 * @author yangwei
 * @version 3.5
 * 
 */
 
 public class CommonChangeDeployLink extends BaseLink {
	
	/**
	*
	* 审批结果
	*
	*/     
	private java.lang.String linkIsCheck;									
	
	/**
	*
	* 审批意见
	*
	*/     
	private java.lang.String linkCheckComment;									
	
	/**
	*
	* 实施负责人及联系方式
	*
	*/     
	private java.lang.String linkManagerContact;									
	
	/**
	*
	* 抄送对象
	*
	*/     
	private java.lang.String linkCopyObject;									
	
	/**
	*
	* 计划开始时间
	*
	*/     
	private java.util.Date linkPlanStartTime;									
	
	/**
	*
	* 计划结束时间
	*
	*/     
	private java.util.Date linkPlanEndTime;									
	
	/**
	*
	* 影响网元范围
	*
	*/     
	private java.lang.String linkEffectCellScope;									
	
	/**
	*
	* 是否影响业务
	*
	*/     
	private java.lang.String linkEffectBusiness;									
	
	/**
	*
	* 影响业务范围及时长
	*
	*/     
	private java.lang.String linkEffectCondition;									
	
	/**
	*
	* 影响网管情况
	*
	*/     
	private java.lang.String linkEffectNetInstance;									
	
	/**
	*
	* 涉及业务部门
	*
	*/     
	private java.lang.String linkBusinessDept;									
	
	/**
	*
	* 是否通知客服
	*
	*/     
	private java.lang.String linkIsSendToFront;									
	
	/**
	*
	* 实施方案
	*
	*/     
	private java.lang.String linkExecuteAccessories;									
	
	/**
	*
	* 实施结果
	*
	*/     
	private java.lang.String linkCutResult;									
	
	/**
	*
	* 失败原因
	*
	*/     
	private java.lang.String linkFailedReason;									
	
	/**
	*
	* 是否完全按照方案实施
	*
	*/     
	private java.lang.String linkIsPlan;									
	
	/**
	*
	* 实施情况说明
	*
	*/     
	private java.lang.String linkCutComment;									
	
	/**
	*
	* 影响业务情况说明
	*
	*/     
	private java.lang.String linkEffectBusinessInstance;									
	
	/**
	*
	* 测试报告
	*
	*/     
	private java.lang.String linkTestReport;									
	
	/**
	*
	* 告警情况记录
	*
	*/     
	private java.lang.String linkAlertRecord;									
	
	/**
	*
	* 统计报告异常说明
	*
	*/     
	private java.lang.String linkUnnormalComment;									
	
	/**
	*
	* 是否示范案例
	*
	*/     
	private java.lang.String mainIfDemonstrateCase;									
	
	/**
	*
	* 案例关键字
	*
	*/     
	private java.lang.String linkCasesMainKey;									
	
	/**
	*
	* 执行情况分析
	*
	*/     
	private java.lang.String linkCutAnalyse;									

		
	public void setLinkIsCheck(java.lang.String linkIsCheck) {
		this.linkIsCheck= linkIsCheck; 
	}
 	
	public java.lang.String getLinkIsCheck() {
		return this.linkIsCheck;
 	}	 	
		
	public void setLinkCheckComment(java.lang.String linkCheckComment) {
		this.linkCheckComment= linkCheckComment; 
	}
 	
	public java.lang.String getLinkCheckComment() {
		return this.linkCheckComment;
 	}	 	
		
	public void setLinkManagerContact(java.lang.String linkManagerContact) {
		this.linkManagerContact= linkManagerContact; 
	}
 	
	public java.lang.String getLinkManagerContact() {
		return this.linkManagerContact;
 	}	 	
		
	public void setLinkCopyObject(java.lang.String linkCopyObject) {
		this.linkCopyObject= linkCopyObject; 
	}
 	
	public java.lang.String getLinkCopyObject() {
		return this.linkCopyObject;
 	}	 	
		
	public void setLinkPlanStartTime(java.util.Date linkPlanStartTime) {
		this.linkPlanStartTime= linkPlanStartTime; 
	}
 	
	public java.util.Date getLinkPlanStartTime() {
		return this.linkPlanStartTime;
 	}	 	
		
	public void setLinkPlanEndTime(java.util.Date linkPlanEndTime) {
		this.linkPlanEndTime= linkPlanEndTime; 
	}
 	
	public java.util.Date getLinkPlanEndTime() {
		return this.linkPlanEndTime;
 	}	 	
		
	public void setLinkEffectCellScope(java.lang.String linkEffectCellScope) {
		this.linkEffectCellScope= linkEffectCellScope; 
	}
 	
	public java.lang.String getLinkEffectCellScope() {
		return this.linkEffectCellScope;
 	}	 	
		
	public void setLinkEffectBusiness(java.lang.String linkEffectBusiness) {
		this.linkEffectBusiness= linkEffectBusiness; 
	}
 	
	public java.lang.String getLinkEffectBusiness() {
		return this.linkEffectBusiness;
 	}	 	
		
	public void setLinkEffectCondition(java.lang.String linkEffectCondition) {
		this.linkEffectCondition= linkEffectCondition; 
	}
 	
	public java.lang.String getLinkEffectCondition() {
		return this.linkEffectCondition;
 	}	 	
		
	public void setLinkEffectNetInstance(java.lang.String linkEffectNetInstance) {
		this.linkEffectNetInstance= linkEffectNetInstance; 
	}
 	
	public java.lang.String getLinkEffectNetInstance() {
		return this.linkEffectNetInstance;
 	}	 	
		
	public void setLinkBusinessDept(java.lang.String linkBusinessDept) {
		this.linkBusinessDept= linkBusinessDept; 
	}
 	
	public java.lang.String getLinkBusinessDept() {
		return this.linkBusinessDept;
 	}	 	
		
	public void setLinkIsSendToFront(java.lang.String linkIsSendToFront) {
		this.linkIsSendToFront= linkIsSendToFront; 
	}
 	
	public java.lang.String getLinkIsSendToFront() {
		return this.linkIsSendToFront;
 	}	 	
		
	public void setLinkExecuteAccessories(java.lang.String linkExecuteAccessories) {
		this.linkExecuteAccessories= linkExecuteAccessories; 
	}
 	
	public java.lang.String getLinkExecuteAccessories() {
		return this.linkExecuteAccessories;
 	}	 	
		
	public void setLinkCutResult(java.lang.String linkCutResult) {
		this.linkCutResult= linkCutResult; 
	}
 	
	public java.lang.String getLinkCutResult() {
		return this.linkCutResult;
 	}	 	
		
	public void setLinkFailedReason(java.lang.String linkFailedReason) {
		this.linkFailedReason= linkFailedReason; 
	}
 	
	public java.lang.String getLinkFailedReason() {
		return this.linkFailedReason;
 	}	 	
		
	public void setLinkIsPlan(java.lang.String linkIsPlan) {
		this.linkIsPlan= linkIsPlan; 
	}
 	
	public java.lang.String getLinkIsPlan() {
		return this.linkIsPlan;
 	}	 	
		
	public void setLinkCutComment(java.lang.String linkCutComment) {
		this.linkCutComment= linkCutComment; 
	}
 	
	public java.lang.String getLinkCutComment() {
		return this.linkCutComment;
 	}	 	
		
	public void setLinkEffectBusinessInstance(java.lang.String linkEffectBusinessInstance) {
		this.linkEffectBusinessInstance= linkEffectBusinessInstance; 
	}
 	
	public java.lang.String getLinkEffectBusinessInstance() {
		return this.linkEffectBusinessInstance;
 	}	 	
		
	public void setLinkTestReport(java.lang.String linkTestReport) {
		this.linkTestReport= linkTestReport; 
	}
 	
	public java.lang.String getLinkTestReport() {
		return this.linkTestReport;
 	}	 	
		
	public void setLinkAlertRecord(java.lang.String linkAlertRecord) {
		this.linkAlertRecord= linkAlertRecord; 
	}
 	
	public java.lang.String getLinkAlertRecord() {
		return this.linkAlertRecord;
 	}	 	
		
	public void setLinkUnnormalComment(java.lang.String linkUnnormalComment) {
		this.linkUnnormalComment= linkUnnormalComment; 
	}
 	
	public java.lang.String getLinkUnnormalComment() {
		return this.linkUnnormalComment;
 	}	 	
		
	public void setMainIfDemonstrateCase(java.lang.String mainIfDemonstrateCase) {
		this.mainIfDemonstrateCase= mainIfDemonstrateCase; 
	}
 	
	public java.lang.String getMainIfDemonstrateCase() {
		return this.mainIfDemonstrateCase;
 	}	 	
		
	public void setLinkCasesMainKey(java.lang.String linkCasesMainKey) {
		this.linkCasesMainKey= linkCasesMainKey; 
	}
 	
	public java.lang.String getLinkCasesMainKey() {
		return this.linkCasesMainKey;
 	}	 	
		
	public void setLinkCutAnalyse(java.lang.String linkCutAnalyse) {
		this.linkCutAnalyse= linkCutAnalyse; 
	}
 	
	public java.lang.String getLinkCutAnalyse() {
		return this.linkCutAnalyse;
 	}	 	

}