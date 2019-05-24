package com.boco.eoms.sheet.circuitcontrol.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:电路调度申请工单
 * </p>
 * <p>
 * Description:电路调度申请工单
 * </p>
 * <p>
 * Sun Sep 29 16:51:14 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class CircuitControlMain extends BaseMain {
	
	/**
 	*
 	* 申请单号
	*
	*/     
	private java.lang.String applyNum;
 					
	
	
	/**
 	*
 	* 业务类型
	*
	*/     
	private java.lang.String businessType;
 					
	
	
	/**
 	*
 	* 电路名称
	*
	*/     
	private java.lang.String circuitName;
 					
	
	
	/**
 	*
 	* 起点站名
	*
	*/     
	private java.lang.String startName;
 					
	
	
	/**
 	*
 	* 终点站名
	*
	*/     
	private java.lang.String endName;
 					
	
	
	/**
 	*
 	* 备注(速率)
	*
	*/     
	private java.lang.String remarkRate;
 					
	
	
	/**
 	*
 	* 备注
	*
	*/     
	private java.lang.String remark;
 					
	
	
	/**
 	*
 	* 备用一
	*
	*/     
	private java.lang.String spareOne;
 					
	
	
	/**
 	*
 	* 备用二
	*
	*/     
	private java.lang.String spareTwo;
 					
	
	
	/**
 	*
 	* 备用三
	*
	*/     
	private java.lang.String spareThree;
 					
	
	
	/**
 	*
 	* 备用四
	*
	*/     
	private java.lang.String spareFour;
 					
	

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String sendObject;
	
	public void setApplyNum(java.lang.String applyNum) {
		this.applyNum= applyNum; 
	}
 	
	public java.lang.String getApplyNum() {
		return this.applyNum;
	}			

	
	public void setBusinessType(java.lang.String businessType) {
		this.businessType= businessType; 
	}
 	
	public java.lang.String getBusinessType() {
		return this.businessType;
	}			

	
	public void setCircuitName(java.lang.String circuitName) {
		this.circuitName= circuitName; 
	}
 	
	public java.lang.String getCircuitName() {
		return this.circuitName;
	}			

	
	public void setStartName(java.lang.String startName) {
		this.startName= startName; 
	}
 	
	public java.lang.String getStartName() {
		return this.startName;
	}			

	
	public void setEndName(java.lang.String endName) {
		this.endName= endName; 
	}
 	
	public java.lang.String getEndName() {
		return this.endName;
	}			

	
	public void setRemarkRate(java.lang.String remarkRate) {
		this.remarkRate= remarkRate; 
	}
 	
	public java.lang.String getRemarkRate() {
		return this.remarkRate;
	}			

	
	public void setRemark(java.lang.String remark) {
		this.remark= remark; 
	}
 	
	public java.lang.String getRemark() {
		return this.remark;
	}			

	
	public void setSpareOne(java.lang.String spareOne) {
		this.spareOne= spareOne; 
	}
 	
	public java.lang.String getSpareOne() {
		return this.spareOne;
	}			

	
	public void setSpareTwo(java.lang.String spareTwo) {
		this.spareTwo= spareTwo; 
	}
 	
	public java.lang.String getSpareTwo() {
		return this.spareTwo;
	}			

	
	public void setSpareThree(java.lang.String spareThree) {
		this.spareThree= spareThree; 
	}
 	
	public java.lang.String getSpareThree() {
		return this.spareThree;
	}			

	
	public void setSpareFour(java.lang.String spareFour) {
		this.spareFour= spareFour; 
	}
 	
	public java.lang.String getSpareFour() {
		return this.spareFour;
	}			



	public java.lang.String getSendObject() {
		return sendObject;
	}

	public void setSendObject(java.lang.String sendObject) {
		this.sendObject = sendObject;
	}
 }