package com.boco.eoms.sheet.groupcheck.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:集客投诉核查工单
 * </p>
 * <p>
 * Description:集客投诉核查工单
 * </p>
 * <p>
 * Wed Nov 08 15:11:37 GMT+08:00 2017
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class GroupCheckLink extends BaseLink {
	
	/**
	*
	* 自动发现核查原因
	*
	*/     
	private java.lang.String linkAutoFind;									
	
	/**
	*
	* 核查说明
	*
	*/     
	private java.lang.String linkExplain;									
	
	/**
	*
	* 联系人
	*
	*/     
	private java.lang.String linkContact;									
	
	/**
	*
	* 联系人电话
	*
	*/     
	private java.lang.String linkContactPhone;									
	
	/**
	*
	* 备用1
	*
	*/     
	private java.lang.String linkStand1;									
	
	/**
	*
	* 备用2
	*
	*/     
	private java.lang.String linkStand2;									
	
	/**
	*
	* 备用3
	*
	*/     
	private java.lang.String linkStand3;									
	
	/**
	*
	* 备用4
	*
	*/     
	private java.lang.String linkStand4;									
	
	/**
	*
	* 备用5
	*
	*/     
	private java.lang.String linkStand5;									

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String linkSendObject;
		
	public void setLinkAutoFind(java.lang.String linkAutoFind) {
		this.linkAutoFind= linkAutoFind; 
	}
 	
	public java.lang.String getLinkAutoFind() {
		return this.linkAutoFind;
 	}	 	
		
	public void setLinkExplain(java.lang.String linkExplain) {
		this.linkExplain= linkExplain; 
	}
 	
	public java.lang.String getLinkExplain() {
		return this.linkExplain;
 	}	 	
		
	public void setLinkContact(java.lang.String linkContact) {
		this.linkContact= linkContact; 
	}
 	
	public java.lang.String getLinkContact() {
		return this.linkContact;
 	}	 	
		
	public void setLinkContactPhone(java.lang.String linkContactPhone) {
		this.linkContactPhone= linkContactPhone; 
	}
 	
	public java.lang.String getLinkContactPhone() {
		return this.linkContactPhone;
 	}	 	
		
	public void setLinkStand1(java.lang.String linkStand1) {
		this.linkStand1= linkStand1; 
	}
 	
	public java.lang.String getLinkStand1() {
		return this.linkStand1;
 	}	 	
		
	public void setLinkStand2(java.lang.String linkStand2) {
		this.linkStand2= linkStand2; 
	}
 	
	public java.lang.String getLinkStand2() {
		return this.linkStand2;
 	}	 	
		
	public void setLinkStand3(java.lang.String linkStand3) {
		this.linkStand3= linkStand3; 
	}
 	
	public java.lang.String getLinkStand3() {
		return this.linkStand3;
 	}	 	
		
	public void setLinkStand4(java.lang.String linkStand4) {
		this.linkStand4= linkStand4; 
	}
 	
	public java.lang.String getLinkStand4() {
		return this.linkStand4;
 	}	 	
		
	public void setLinkStand5(java.lang.String linkStand5) {
		this.linkStand5= linkStand5; 
	}
 	
	public java.lang.String getLinkStand5() {
		return this.linkStand5;
 	}	 	

	
	public java.lang.String getLinkSendObject() {
		return linkSendObject;
	}

	public void setLinkSendObject(java.lang.String linkSendObject) {
		this.linkSendObject = linkSendObject;
	}	 
}