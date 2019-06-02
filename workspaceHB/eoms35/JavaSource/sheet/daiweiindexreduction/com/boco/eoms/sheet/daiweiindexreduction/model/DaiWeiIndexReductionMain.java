package com.boco.eoms.sheet.daiweiindexreduction.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:代维公司指标核减流程
 * </p>
 * <p>
 * Description:代维公司指标核减流程
 * </p>
 * <p>
 * Tue Aug 01 17:34:54 CST 2017
 * </p>
 * 
 * @author wangmingming
 * @version 1.0
 * 
 */
 
 public class DaiWeiIndexReductionMain extends BaseMain {
	
	/**
 	*
 	* 主题
	*
	*/     
	private java.lang.String mainTitle;
 					
	
	
	/**
 	*
 	* 核减指标名称
	*
	*/     
	private java.lang.String mainSubtractIndexName;
 					
	
	
	/**
 	*
 	* 核减专业
	*
	*/     
	private java.lang.String mainSubtractProfessional;
 					
	
	
	/**
 	*
 	* 所属地市
	*
	*/     
	private java.lang.String mainCity;
 					
	
	
	/**
 	*
 	* 所属区县
	*
	*/     
	private java.lang.String mainCounty;
 					
	
	
	/**
 	*
 	* 核减信息确认人
	*
	*/     
	private java.lang.String mainConfirmingPerson;
 					
	
	
	/**
 	*
 	* 核减信息确认人电话
	*
	*/     
	private java.lang.String mainConfirmingTelephone;
 					
	
	
	/**
 	*
 	* 说明
	*
	*/     
	private java.lang.String mainIllustrate;
 					
	
	
	/**
 	*
 	* 预留A
	*
	*/     
	private java.lang.String mainReserveA;
 					
	
	
	/**
 	*
 	* 预留B
	*
	*/     
	private java.lang.String mainReserveB;
 					
	
	
	/**
 	*
 	* 预留C
	*
	*/     
	private java.lang.String mainReserveC;
 					
	
	
	/**
 	*
 	* 预留D
	*
	*/     
	private java.lang.String mainReserveD;
 					
	
	
	/**
 	*
 	* 预留E
	*
	*/     
	private java.lang.String mainReserveE;
 					
	/**
 	*
 	* 核减内容
	*
	*/     
	private java.lang.String mainSubtractContent;
	
	/**
 	*
 	* 申请核减时间
	*
	*/     
	private java.util.Date mainSubtractTime;	 
	/**
 	*
 	* 初审处理时限
	*
	*/     
	private java.util.Date mainProcessTime;	 
	
	/**
	 * 核减原因模板
	 */
	private java.lang.String mainSubtractReason;
	
	/**
	 * 判断是那种类型的工单
	 */
	private java.lang.String newFlag;
	
	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String sendObject;
	
	public void setMainTitle(java.lang.String mainTitle) {
		this.mainTitle= mainTitle; 
	}
 	
	public java.lang.String getMainTitle() {
		return this.mainTitle;
	}			

	
	public void setMainSubtractIndexName(java.lang.String mainSubtractIndexName) {
		this.mainSubtractIndexName= mainSubtractIndexName; 
	}
 	
	public java.lang.String getMainSubtractIndexName() {
		return this.mainSubtractIndexName;
	}			

	
	public void setMainSubtractProfessional(java.lang.String mainSubtractProfessional) {
		this.mainSubtractProfessional= mainSubtractProfessional; 
	}
 	
	public java.lang.String getMainSubtractProfessional() {
		return this.mainSubtractProfessional;
	}			

	
	public void setMainCity(java.lang.String mainCity) {
		this.mainCity= mainCity; 
	}
 	
	public java.lang.String getMainCity() {
		return this.mainCity;
	}			

	
	public void setMainCounty(java.lang.String mainCounty) {
		this.mainCounty= mainCounty; 
	}
 	
	public java.lang.String getMainCounty() {
		return this.mainCounty;
	}			

	
	public void setMainConfirmingPerson(java.lang.String mainConfirmingPerson) {
		this.mainConfirmingPerson= mainConfirmingPerson; 
	}
 	
	public java.lang.String getMainConfirmingPerson() {
		return this.mainConfirmingPerson;
	}			

	
	public void setMainConfirmingTelephone(java.lang.String mainConfirmingTelephone) {
		this.mainConfirmingTelephone= mainConfirmingTelephone; 
	}
 	
	public java.lang.String getMainConfirmingTelephone() {
		return this.mainConfirmingTelephone;
	}			

	
	public void setMainIllustrate(java.lang.String mainIllustrate) {
		this.mainIllustrate= mainIllustrate; 
	}
 	
	public java.lang.String getMainIllustrate() {
		return this.mainIllustrate;
	}			

	
	public void setMainReserveA(java.lang.String mainReserveA) {
		this.mainReserveA= mainReserveA; 
	}
 	
	public java.lang.String getMainReserveA() {
		return this.mainReserveA;
	}			

	
	public void setMainReserveB(java.lang.String mainReserveB) {
		this.mainReserveB= mainReserveB; 
	}
 	
	public java.lang.String getMainReserveB() {
		return this.mainReserveB;
	}			

	
	public void setMainReserveC(java.lang.String mainReserveC) {
		this.mainReserveC= mainReserveC; 
	}
 	
	public java.lang.String getMainReserveC() {
		return this.mainReserveC;
	}			

	
	public void setMainReserveD(java.lang.String mainReserveD) {
		this.mainReserveD= mainReserveD; 
	}
 	
	public java.lang.String getMainReserveD() {
		return this.mainReserveD;
	}			

	
	public void setMainReserveE(java.lang.String mainReserveE) {
		this.mainReserveE= mainReserveE; 
	}
 	
	public java.lang.String getMainReserveE() {
		return this.mainReserveE;
	}			



	public java.lang.String getSendObject() {
		return sendObject;
	}

	public void setSendObject(java.lang.String sendObject) {
		this.sendObject = sendObject;
	}

	public java.util.Date getMainProcessTime() {
		return this.mainProcessTime;
	}

	public void setMainProcessTime(java.util.Date mainProcessTime) {
		this.mainProcessTime = mainProcessTime;
	}

	public java.lang.String getMainSubtractContent() {
		return this.mainSubtractContent;
	}

	public void setMainSubtractContent(java.lang.String mainSubtractContent) {
		this.mainSubtractContent = mainSubtractContent;
	}

	public java.util.Date getMainSubtractTime() {
		return this.mainSubtractTime;
	}

	public void setMainSubtractTime(java.util.Date mainSubtractTime) {
		this.mainSubtractTime = mainSubtractTime;
	}

	public java.lang.String getMainSubtractReason() {
		return mainSubtractReason;
	}

	public void setMainSubtractReason(java.lang.String mainSubtractReason) {
		this.mainSubtractReason = mainSubtractReason;
	}

	public java.lang.String getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(java.lang.String newFlag) {
		this.newFlag = newFlag;
	}
	
	
 }