package com.boco.eoms.sheet.citycustom.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:地市集客业务工单
 * </p>
 * <p>
 * Description:地市集客业务工单
 * </p>
 * <p>
 * Fri Sep 28 14:06:48 CST 2012
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class CityCustomLink extends BaseLink {
	
	/**
	*
	* 完成情况
	*
	*/     
	private java.lang.String linkGroupComplete;									

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String linkSendObject;
		
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