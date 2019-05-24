package com.boco.eoms.sheet.transprovincial.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:跨省集客业务工单
 * </p>
 * <p>
 * Description:跨省集客业务工单
 * </p>
 * <p>
 * Thu Sep 27 14:32:21 CST 2012
 * </p>
 * 
 * @author ph
 * @version 3.5
 * 
 */
 
 public class TransprovincialLink extends BaseLink {
	
	/**
	*
	* 分派结果
	*
	*/     
	private java.lang.String assignmentResult;									
	
	/**
	*
	* 设计结果
	*
	*/     
	private java.lang.String desginResult;									
	
	/**
	*
	* 审核结果
	*
	*/     
	private java.lang.String examineResult;									
	
	/**
	*
	* 施工情况
	*
	*/     
	private java.lang.String constractCondition;									
	
	/**
	*
	* 完成情况
	*
	*/     
	private java.lang.String completeResult;									
	
	/**
	*
	* 验收情况
	*
	*/     
	private java.lang.String acceptanceCondition;									
	
	/**
	*
	* 验收结果
	*
	*/     
	private java.lang.String acceptanceResult;									
	
	/**
	*
	* 开通结果
	*
	*/     
	private java.lang.String completionResult;									
	
	/**
	*
	* 回复结果
	*
	*/     
	private java.lang.String replyResult;									
	
	/**
	*
	* 回复结果
	*
	*/     
	private java.lang.String linkGroupComplete;									

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String linkSendObject;
		
	public void setAssignmentResult(java.lang.String assignmentResult) {
		this.assignmentResult= assignmentResult; 
	}
 	
	public java.lang.String getAssignmentResult() {
		return this.assignmentResult;
 	}	 	
		
	public void setDesginResult(java.lang.String desginResult) {
		this.desginResult= desginResult; 
	}
 	
	public java.lang.String getDesginResult() {
		return this.desginResult;
 	}	 	
		
	public void setExamineResult(java.lang.String examineResult) {
		this.examineResult= examineResult; 
	}
 	
	public java.lang.String getExamineResult() {
		return this.examineResult;
 	}	 	
		
	public void setConstractCondition(java.lang.String constractCondition) {
		this.constractCondition= constractCondition; 
	}
 	
	public java.lang.String getConstractCondition() {
		return this.constractCondition;
 	}	 	
		
	public void setCompleteResult(java.lang.String completeResult) {
		this.completeResult= completeResult; 
	}
 	
	public java.lang.String getCompleteResult() {
		return this.completeResult;
 	}	 	
		
	public void setAcceptanceCondition(java.lang.String acceptanceCondition) {
		this.acceptanceCondition= acceptanceCondition; 
	}
 	
	public java.lang.String getAcceptanceCondition() {
		return this.acceptanceCondition;
 	}	 	
		
	public void setAcceptanceResult(java.lang.String acceptanceResult) {
		this.acceptanceResult= acceptanceResult; 
	}
 	
	public java.lang.String getAcceptanceResult() {
		return this.acceptanceResult;
 	}	 	
		
	public void setCompletionResult(java.lang.String completionResult) {
		this.completionResult= completionResult; 
	}
 	
	public java.lang.String getCompletionResult() {
		return this.completionResult;
 	}	 	
		
	public void setReplyResult(java.lang.String replyResult) {
		this.replyResult= replyResult; 
	}
 	
	public java.lang.String getReplyResult() {
		return this.replyResult;
 	}	 	
		
	public void setLinkGroupComplete(java.lang.String linkGroupComplete) {
		this.linkGroupComplete= linkGroupComplete; 
	}
 	
	public java.lang.String getLinkGroupComplete() {
		return this.linkGroupComplete;
 	}	 	

	
	public java.lang.String getLinkSendObject() {
		return linkSendObject;
	}

	public void setLinkSendObject(java.lang.String linkSendObject) {
		this.linkSendObject = linkSendObject;
	}	 
}