package com.boco.eoms.sheet.numberapply.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.5
 * 
 */
 
 public class NumberApplyLink extends BaseLink {
	
	/**
	*
	* 是否通过
	*
	*/     
	private java.lang.String linkIfPass;									
	
	/**
	*
	* 审核意见
	*
	*/     
	private java.lang.String linkPermitsuggest;									
	
									

	/**
 	*
 	* 保存派发对象
	*
	*/ 
	private java.lang.String linkSendObject;
		
	public void setLinkIfPass(java.lang.String linkIfPass) {
		this.linkIfPass= linkIfPass; 
	}
 	
	public java.lang.String getLinkIfPass() {
		return this.linkIfPass;
 	}	 	
		
	public void setLinkPermitsuggest(java.lang.String linkPermitsuggest) {
		this.linkPermitsuggest= linkPermitsuggest; 
	}
 	
	public java.lang.String getLinkPermitsuggest() {
		return this.linkPermitsuggest;
 	}	 	
	
	public java.lang.String getLinkSendObject() {
		return linkSendObject;
	}

	public void setLinkSendObject(java.lang.String linkSendObject) {
		this.linkSendObject = linkSendObject;
	}	 
}