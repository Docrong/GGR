package com.boco.eoms.sheet.equipmentinstallation.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:设备安装流程
 * </p>
 * <p>
 * Description:设备安装流程
 * </p>
 * <p>
 * Tue Oct 09 14:09:24 GMT+08:00 2018
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class EquipmentInstallationLink extends BaseLink {
	
	/**
	*
	* 备注
	*
	*/     
	private java.lang.String linkDescr;									
	
	/**
	*
	* 预留1
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
	* 安装设备类型
	*
	*/     
	private java.lang.String linkEquipmentType;									
	
	/**
	*
	* 设备编号
	*
	*/     
	private java.lang.String linkEquipmentNum;									
	
	/**
	*
	* 安装时间
	*
	*/     
	private java.util.Date linkEquipmentTime;									
	
	/**
	*
	* MINI设备安装合作协议
	*
	*/     
	private java.lang.String linkEquipmentAgree;									
	
	/**
	*
	* 用户回执单
	*
	*/     
	private java.lang.String linkUserReceipt;									
	
	/**
	*
	* MINI设备运行信息表
	*
	*/     
	private java.lang.String linkInformation;									
	
	/**
	*
	* 现场施工照片
	*
	*/     
	private java.lang.String linkScenePhoto;									
	
	/**
	*
	* 收费照片
	*
	*/     
	private java.lang.String linkChargePhoto;									

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String linkSendObject;
		
	public void setLinkDescr(java.lang.String linkDescr) {
		this.linkDescr= linkDescr; 
	}
 	
	public java.lang.String getLinkDescr() {
		return this.linkDescr;
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
		
	public void setLinkEquipmentType(java.lang.String linkEquipmentType) {
		this.linkEquipmentType= linkEquipmentType; 
	}
 	
	public java.lang.String getLinkEquipmentType() {
		return this.linkEquipmentType;
 	}	 	
		
	public void setLinkEquipmentNum(java.lang.String linkEquipmentNum) {
		this.linkEquipmentNum= linkEquipmentNum; 
	}
 	
	public java.lang.String getLinkEquipmentNum() {
		return this.linkEquipmentNum;
 	}	 	
		
	public void setLinkEquipmentTime(java.util.Date linkEquipmentTime) {
		this.linkEquipmentTime= linkEquipmentTime; 
	}
 	
	public java.util.Date getLinkEquipmentTime() {
		return this.linkEquipmentTime;
 	}	 	
		
	public void setLinkEquipmentAgree(java.lang.String linkEquipmentAgree) {
		this.linkEquipmentAgree= linkEquipmentAgree; 
	}
 	
	public java.lang.String getLinkEquipmentAgree() {
		return this.linkEquipmentAgree;
 	}	 	
		
	public void setLinkUserReceipt(java.lang.String linkUserReceipt) {
		this.linkUserReceipt= linkUserReceipt; 
	}
 	
	public java.lang.String getLinkUserReceipt() {
		return this.linkUserReceipt;
 	}	 	
		
	public void setLinkInformation(java.lang.String linkInformation) {
		this.linkInformation= linkInformation; 
	}
 	
	public java.lang.String getLinkInformation() {
		return this.linkInformation;
 	}	 	
		
	public void setLinkScenePhoto(java.lang.String linkScenePhoto) {
		this.linkScenePhoto= linkScenePhoto; 
	}
 	
	public java.lang.String getLinkScenePhoto() {
		return this.linkScenePhoto;
 	}	 	
		
	public void setLinkChargePhoto(java.lang.String linkChargePhoto) {
		this.linkChargePhoto= linkChargePhoto; 
	}
 	
	public java.lang.String getLinkChargePhoto() {
		return this.linkChargePhoto;
 	}	 	

	
	public java.lang.String getLinkSendObject() {
		return linkSendObject;
	}

	public void setLinkSendObject(java.lang.String linkSendObject) {
		this.linkSendObject = linkSendObject;
	}	 
}