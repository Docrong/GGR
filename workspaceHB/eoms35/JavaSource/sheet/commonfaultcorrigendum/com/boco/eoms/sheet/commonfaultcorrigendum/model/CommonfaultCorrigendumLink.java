package com.boco.eoms.sheet.commonfaultcorrigendum.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:故障工单勘误流程
 * </p>
 * <p>
 * Description:故障工单勘误流程
 * </p>
 * <p>
 * Mon Sep 29 11:24:17 CST 2014
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class CommonfaultCorrigendumLink extends BaseLink {
	
	/**
	*
	* 处理措施
	*
	*/     
	private java.lang.String linkMeasures;									
	
	/**
	*
	* linkSpareOne
	*
	*/     
	private java.lang.String linkSpareOne;									
	
	/**
	*
	* linkSpareTwo
	*
	*/     
	private java.lang.String linkSpareTwo;									
	
	/**
	*
	* linkSpareThree
	*
	*/     
	private java.lang.String linkSpareThree;									
	
	/**
	*
	* linkSpareFour
	*
	*/     
	private java.lang.String linkSpareFour;									
	
	/**
	*
	* linkSpareFive
	*
	*/     
	private java.lang.String linkSpareFive;									

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String linkSendObject;
		
	public void setLinkMeasures(java.lang.String linkMeasures) {
		this.linkMeasures= linkMeasures; 
	}
 	
	public java.lang.String getLinkMeasures() {
		return this.linkMeasures;
 	}	 	
		
	public void setLinkSpareOne(java.lang.String linkSpareOne) {
		this.linkSpareOne= linkSpareOne; 
	}
 	
	public java.lang.String getLinkSpareOne() {
		return this.linkSpareOne;
 	}	 	
		
	public void setLinkSpareTwo(java.lang.String linkSpareTwo) {
		this.linkSpareTwo= linkSpareTwo; 
	}
 	
	public java.lang.String getLinkSpareTwo() {
		return this.linkSpareTwo;
 	}	 	
		
	public void setLinkSpareThree(java.lang.String linkSpareThree) {
		this.linkSpareThree= linkSpareThree; 
	}
 	
	public java.lang.String getLinkSpareThree() {
		return this.linkSpareThree;
 	}	 	
		
	public void setLinkSpareFour(java.lang.String linkSpareFour) {
		this.linkSpareFour= linkSpareFour; 
	}
 	
	public java.lang.String getLinkSpareFour() {
		return this.linkSpareFour;
 	}	 	
		
	public void setLinkSpareFive(java.lang.String linkSpareFive) {
		this.linkSpareFive= linkSpareFive; 
	}
 	
	public java.lang.String getLinkSpareFive() {
		return this.linkSpareFive;
 	}	 	

	
	public java.lang.String getLinkSendObject() {
		return linkSendObject;
	}

	public void setLinkSendObject(java.lang.String linkSendObject) {
		this.linkSendObject = linkSendObject;
	}	 
}