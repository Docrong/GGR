package com.boco.eoms.sheet.focusresourceerrata.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:集客资源勘误
 * </p>
 * <p>
 * Description:集客资源勘误
 * </p>
 * <p>
 * Thu May 10 09:23:09 CST 2018
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class FocusResourceErrataLink extends BaseLink {
	
	/**
	*
	* 电路核查状态
	*
	*/     
	private java.lang.String linkCheckState;									
	
	/**
	*
	* 业务类型
	*
	*/     
	private java.lang.String linkReserved1;									
	
	/**
	*
	* 预留2
	*
	*/     
	private java.lang.String linkReserved2;									
	
	/**
	*
	* 预留3
	*
	*/     
	private java.lang.String linkReserved3;									
	
	/**
	*
	* 预留4
	*
	*/     
	private java.lang.String linkReserved4;									
	
	/**
	*
	* 预留5
	*
	*/     
	private java.lang.String linkReserved5;									
	
	/**
	*
	* 自动归档
	*
	*/     
	private java.lang.String linkIsMysel;									
	
	/**
	*
	* 审批意见
	*
	*/     
	private java.lang.String linkApprovalOpinion;									
	
	/**
	*
	* 执行结果
	*
	*/     
	private java.lang.String linkResult;									
	
	/**
	*
	* 备注
	*
	*/     
	private java.lang.String linkRemark;									

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String linkSendObject;
		
	public void setLinkCheckState(java.lang.String linkCheckState) {
		this.linkCheckState= linkCheckState; 
	}
 	
	public java.lang.String getLinkCheckState() {
		return this.linkCheckState;
 	}	 	
		
	public void setLinkReserved1(java.lang.String linkReserved1) {
		this.linkReserved1= linkReserved1; 
	}
 	
	public java.lang.String getLinkReserved1() {
		return this.linkReserved1;
 	}	 	
		
	public void setLinkReserved2(java.lang.String linkReserved2) {
		this.linkReserved2= linkReserved2; 
	}
 	
	public java.lang.String getLinkReserved2() {
		return this.linkReserved2;
 	}	 	
		
	public void setLinkReserved3(java.lang.String linkReserved3) {
		this.linkReserved3= linkReserved3; 
	}
 	
	public java.lang.String getLinkReserved3() {
		return this.linkReserved3;
 	}	 	
		
	public void setLinkReserved4(java.lang.String linkReserved4) {
		this.linkReserved4= linkReserved4; 
	}
 	
	public java.lang.String getLinkReserved4() {
		return this.linkReserved4;
 	}	 	
		
	public void setLinkReserved5(java.lang.String linkReserved5) {
		this.linkReserved5= linkReserved5; 
	}
 	
	public java.lang.String getLinkReserved5() {
		return this.linkReserved5;
 	}	 	
		
	public void setLinkIsMysel(java.lang.String linkIsMysel) {
		this.linkIsMysel= linkIsMysel; 
	}
 	
	public java.lang.String getLinkIsMysel() {
		return this.linkIsMysel;
 	}	 	
		
	public void setLinkApprovalOpinion(java.lang.String linkApprovalOpinion) {
		this.linkApprovalOpinion= linkApprovalOpinion; 
	}
 	
	public java.lang.String getLinkApprovalOpinion() {
		return this.linkApprovalOpinion;
 	}	 	
		
	public void setLinkResult(java.lang.String linkResult) {
		this.linkResult= linkResult; 
	}
 	
	public java.lang.String getLinkResult() {
		return this.linkResult;
 	}	 	
		
	public void setLinkRemark(java.lang.String linkRemark) {
		this.linkRemark= linkRemark; 
	}
 	
	public java.lang.String getLinkRemark() {
		return this.linkRemark;
 	}	 	

	
	public java.lang.String getLinkSendObject() {
		return linkSendObject;
	}

	public void setLinkSendObject(java.lang.String linkSendObject) {
		this.linkSendObject = linkSendObject;
	}	 
}