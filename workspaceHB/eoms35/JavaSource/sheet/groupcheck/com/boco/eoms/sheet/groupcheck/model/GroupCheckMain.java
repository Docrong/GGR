package com.boco.eoms.sheet.groupcheck.model;

import com.boco.eoms.sheet.base.model.BaseMain;

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
 
 public class GroupCheckMain extends BaseMain {
	
	/**
 	*
 	* 集团投诉工单号
	*
	*/     
	private java.lang.String mainGroupSheetId;
 					
	
	
	/**
 	*
 	* 产品实例标识
	*
	*/     
	private java.lang.String mainProductIns;
 					
	
	
	/**
 	*
 	* 电路代号
	*
	*/     
	private java.lang.String mainCircuitCode;
 					
	
	
	/**
 	*
 	* 用户归属地
	*
	*/     
	private java.lang.String mainUserAffilia;
 					
	
	
	/**
 	*
 	* 投诉时间
	*
	*/     
	private java.util.Date mainComplaintTime;
 					
	
	
	/**
 	*
 	* 受理时限
	*
	*/     
	private java.util.Date mainStandTime1;
 					
	
	
	/**
 	*
 	* 处理时限
	*
	*/     
	private java.util.Date mainStandTime2;
 					
	
	
	/**
 	*
 	* 备用1
	*
	*/     
	private java.lang.String mainStand1;
 					
	
	
	/**
 	*
 	* 备用2
	*
	*/     
	private java.lang.String mainStand2;
 					
	
	
	/**
 	*
 	* 备用3
	*
	*/     
	private java.lang.String mainStand31;
 					
	
	
	/**
 	*
 	* 备用4
	*
	*/     
	private java.lang.String mainStand4;
 					
	
	
	/**
 	*
 	* 备用5
	*
	*/     
	private java.lang.String mainStand5;
 					
	

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String sendObject;
	
	public void setMainGroupSheetId(java.lang.String mainGroupSheetId) {
		this.mainGroupSheetId= mainGroupSheetId; 
	}
 	
	public java.lang.String getMainGroupSheetId() {
		return this.mainGroupSheetId;
	}			

	
	public void setMainProductIns(java.lang.String mainProductIns) {
		this.mainProductIns= mainProductIns; 
	}
 	
	public java.lang.String getMainProductIns() {
		return this.mainProductIns;
	}			

	
	public void setMainCircuitCode(java.lang.String mainCircuitCode) {
		this.mainCircuitCode= mainCircuitCode; 
	}
 	
	public java.lang.String getMainCircuitCode() {
		return this.mainCircuitCode;
	}			

	
	public void setMainUserAffilia(java.lang.String mainUserAffilia) {
		this.mainUserAffilia= mainUserAffilia; 
	}
 	
	public java.lang.String getMainUserAffilia() {
		return this.mainUserAffilia;
	}			

	
	public void setMainComplaintTime(java.util.Date mainComplaintTime) {
		this.mainComplaintTime= mainComplaintTime; 
	}
 	
	public java.util.Date getMainComplaintTime() {
		return this.mainComplaintTime;
	}			

	
	public void setMainStandTime1(java.util.Date mainStandTime1) {
		this.mainStandTime1= mainStandTime1; 
	}
 	
	public java.util.Date getMainStandTime1() {
		return this.mainStandTime1;
	}			

	
	public void setMainStandTime2(java.util.Date mainStandTime2) {
		this.mainStandTime2= mainStandTime2; 
	}
 	
	public java.util.Date getMainStandTime2() {
		return this.mainStandTime2;
	}			

	
	public void setMainStand1(java.lang.String mainStand1) {
		this.mainStand1= mainStand1; 
	}
 	
	public java.lang.String getMainStand1() {
		return this.mainStand1;
	}			

	
	public void setMainStand2(java.lang.String mainStand2) {
		this.mainStand2= mainStand2; 
	}
 	
	public java.lang.String getMainStand2() {
		return this.mainStand2;
	}			

	
	public void setMainStand31(java.lang.String mainStand31) {
		this.mainStand31= mainStand31; 
	}
 	
	public java.lang.String getMainStand31() {
		return this.mainStand31;
	}			

	
	public void setMainStand4(java.lang.String mainStand4) {
		this.mainStand4= mainStand4; 
	}
 	
	public java.lang.String getMainStand4() {
		return this.mainStand4;
	}			

	
	public void setMainStand5(java.lang.String mainStand5) {
		this.mainStand5= mainStand5; 
	}
 	
	public java.lang.String getMainStand5() {
		return this.mainStand5;
	}			



	public java.lang.String getSendObject() {
		return sendObject;
	}

	public void setSendObject(java.lang.String sendObject) {
		this.sendObject = sendObject;
	}
 }