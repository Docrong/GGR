package com.work.ggr.model;

import java.util.Date;


/**
 * <p>
 * Title:挂牌整治工单
 * </p>
 * <p>
 * Description:挂牌整治工单
 * </p>
 * <p>
 * Wed Aug 15 18:19:11 CST 2018
 * </p>
 * 
 * @author chenzijian
 * @version 3.5
 * 
 */
 
 public class ListedRegulationMain  {
	
	/**
 	*
 	* 网元名称
	*
	*/     
	private java.lang.String mainNetName;
 					
	
	
	/**
 	*
 	* 挂牌时间
	*
	*/     
	private java.util.Date mainListedTime;
 					
	
	
	/**
 	*
 	* 挂牌主题
	*
	*/     
	private java.lang.String mainListedTitle;
 					
	
	
	/**
 	*
 	* 挂牌类型
	*
	*/     
	private java.lang.String mainListedType;
 					
	
	
	/**
 	*
 	* 挂牌周期
	*
	*/     
	private java.lang.String mainListedCyc;
 					
	
	
	/**
 	*
 	* 挂牌描述
	*
	*/     
	private java.lang.String mainListedDesc;
 					
	
	
	/**
 	*
 	* 告警唯一标识
	*
	*/     
	private java.lang.String mainListedAddInfo;
 					
	
	
	/**
 	*
 	* 是否复发挂牌
	*
	*/     
	private java.lang.String mainIfReListed;
 					
	/**
	 * 确实是否复发挂牌
	 */
	private String confirmMainIfReListed;
	
	/**
 	*
 	* 摘牌时间
	*
	*/     
	private java.util.Date mainDelistingTime;
 					
	
	
	/**
 	*
 	* 地市
	*
	*/     
	private java.lang.String mainCity;
 					
	
	
	/**
 	*
 	* 区县
	*
	*/     
	private java.lang.String mainCountry;
 					
	
	
	/**
 	*
 	* 专业
	*
	*/     
	private java.lang.String mainProfessional;
 					
	
	
	/**
 	*
 	* 厂家
	*
	*/     
	private java.lang.String mainEquipmentFactory;
 					
	
	
	/**
 	*
 	* 设备类型
	*
	*/     
	private java.lang.String mainEquipmentType;
 					
	
	
	/**
 	*
 	* 网络一级分类
	*
	*/     
	private java.lang.String mainNetSortOne;
 					
	
	
	/**
 	*
 	* 网络二级分类
	*
	*/     
	private java.lang.String mainNetSortTwo;
 					
	
	
	/**
 	*
 	* 网络三级分类
	*
	*/     
	private java.lang.String mainNetSortThree;
 					
	
	
	/**
 	*
 	* 网元关联的派单到人角色
	*
	*/     
	private java.lang.String mainPersonalRoleId;
 					
	
	
	/**
 	*
 	* 受理审批时限
	*
	*/     
	private java.util.Date mainExamineAcceptLimit;
 					
	
	
	/**
 	*
 	* 派单对象
	*
	*/     
	private java.lang.String mainSendObject;
 					
	
	
	/**
 	*
 	* 整改报告时限
	*
	*/     
	private java.util.Date mainUploadReportLimit;
 					
	
	
	/**
 	*
 	* 阶段回复周期
	*
	*/     
	private java.lang.String mainPhaseReplyCyc;
 					
	
	
	/**
 	*
 	* 默认审批对象
	*
	*/     
	private java.lang.String mainExamineObject;
 					
	
	
	/**
 	*
 	* 默认责任主体
	*
	*/     
	private java.lang.String mainResponsibility;
 					
	

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String sendObject;
	
	/**
	 * 网管告警流水号
	 */
	private String mainAlarmNum;
	/**
	 * 告警清除时间
	 */
	private Date mainAlarmSolveDate;
	
	

	public void setMainNetName(java.lang.String mainNetName) {
		this.mainNetName= mainNetName; 
	}
 	
	public java.lang.String getMainNetName() {
		return this.mainNetName;
	}			

	
	public void setMainListedTime(java.util.Date mainListedTime) {
		this.mainListedTime= mainListedTime; 
	}
 	
	public java.util.Date getMainListedTime() {
		return this.mainListedTime;
	}			

	
	public void setMainListedTitle(java.lang.String mainListedTitle) {
		this.mainListedTitle= mainListedTitle; 
	}
 	
	public java.lang.String getMainListedTitle() {
		return this.mainListedTitle;
	}			

	
	public void setMainListedType(java.lang.String mainListedType) {
		this.mainListedType= mainListedType; 
	}
 	
	public java.lang.String getMainListedType() {
		return this.mainListedType;
	}			

	
	public void setMainListedCyc(java.lang.String mainListedCyc) {
		this.mainListedCyc= mainListedCyc; 
	}
 	
	public java.lang.String getMainListedCyc() {
		return this.mainListedCyc;
	}			

	
	public void setMainListedDesc(java.lang.String mainListedDesc) {
		this.mainListedDesc= mainListedDesc; 
	}
 	
	public java.lang.String getMainListedDesc() {
		return this.mainListedDesc;
	}			

	
	public void setMainListedAddInfo(java.lang.String mainListedAddInfo) {
		this.mainListedAddInfo= mainListedAddInfo; 
	}
 	
	public java.lang.String getMainListedAddInfo() {
		return this.mainListedAddInfo;
	}			

	
	public void setMainIfReListed(java.lang.String mainIfReListed) {
		this.mainIfReListed= mainIfReListed; 
	}
 	
	public java.lang.String getMainIfReListed() {
		return this.mainIfReListed;
	}			

	
	public void setMainDelistingTime(java.util.Date mainDelistingTime) {
		this.mainDelistingTime= mainDelistingTime; 
	}
 	
	public java.util.Date getMainDelistingTime() {
		return this.mainDelistingTime;
	}			

	
	public void setMainCity(java.lang.String mainCity) {
		this.mainCity= mainCity; 
	}
 	
	public java.lang.String getMainCity() {
		return this.mainCity;
	}			

	
	public void setMainCountry(java.lang.String mainCountry) {
		this.mainCountry= mainCountry; 
	}
 	
	public java.lang.String getMainCountry() {
		return this.mainCountry;
	}			

	
	public void setMainProfessional(java.lang.String mainProfessional) {
		this.mainProfessional= mainProfessional; 
	}
 	
	public java.lang.String getMainProfessional() {
		return this.mainProfessional;
	}			

	
	public void setMainEquipmentFactory(java.lang.String mainEquipmentFactory) {
		this.mainEquipmentFactory= mainEquipmentFactory; 
	}
 	
	public java.lang.String getMainEquipmentFactory() {
		return this.mainEquipmentFactory;
	}			

	
	public void setMainEquipmentType(java.lang.String mainEquipmentType) {
		this.mainEquipmentType= mainEquipmentType; 
	}
 	
	public java.lang.String getMainEquipmentType() {
		return this.mainEquipmentType;
	}			

	
	public void setMainNetSortOne(java.lang.String mainNetSortOne) {
		this.mainNetSortOne= mainNetSortOne; 
	}
 	
	public java.lang.String getMainNetSortOne() {
		return this.mainNetSortOne;
	}			

	
	public void setMainNetSortTwo(java.lang.String mainNetSortTwo) {
		this.mainNetSortTwo= mainNetSortTwo; 
	}
 	
	public java.lang.String getMainNetSortTwo() {
		return this.mainNetSortTwo;
	}			

	
	public void setMainNetSortThree(java.lang.String mainNetSortThree) {
		this.mainNetSortThree= mainNetSortThree; 
	}
 	
	public java.lang.String getMainNetSortThree() {
		return this.mainNetSortThree;
	}			

	
	public void setMainPersonalRoleId(java.lang.String mainPersonalRoleId) {
		this.mainPersonalRoleId= mainPersonalRoleId; 
	}
 	
	public java.lang.String getMainPersonalRoleId() {
		return this.mainPersonalRoleId;
	}			

	
	public void setMainExamineAcceptLimit(java.util.Date mainExamineAcceptLimit) {
		this.mainExamineAcceptLimit= mainExamineAcceptLimit; 
	}
 	
	public java.util.Date getMainExamineAcceptLimit() {
		return this.mainExamineAcceptLimit;
	}			

	
	public void setMainSendObject(java.lang.String mainSendObject) {
		this.mainSendObject= mainSendObject; 
	}
 	
	public java.lang.String getMainSendObject() {
		return this.mainSendObject;
	}			

	
	public void setMainUploadReportLimit(java.util.Date mainUploadReportLimit) {
		this.mainUploadReportLimit= mainUploadReportLimit; 
	}
 	
	public java.util.Date getMainUploadReportLimit() {
		return this.mainUploadReportLimit;
	}			

	
	public void setMainPhaseReplyCyc(java.lang.String mainPhaseReplyCyc) {
		this.mainPhaseReplyCyc= mainPhaseReplyCyc; 
	}
 	
	public java.lang.String getMainPhaseReplyCyc() {
		return this.mainPhaseReplyCyc;
	}			

	
	public void setMainExamineObject(java.lang.String mainExamineObject) {
		this.mainExamineObject= mainExamineObject; 
	}
 	
	public java.lang.String getMainExamineObject() {
		return this.mainExamineObject;
	}			

	
	public void setMainResponsibility(java.lang.String mainResponsibility) {
		this.mainResponsibility= mainResponsibility; 
	}
 	
	public java.lang.String getMainResponsibility() {
		return this.mainResponsibility;
	}			



	public java.lang.String getSendObject() {
		return sendObject;
	}

	public void setSendObject(java.lang.String sendObject) {
		this.sendObject = sendObject;
	}

	public String getMainAlarmNum() {
		return mainAlarmNum;
	}

	public void setMainAlarmNum(String mainAlarmNum) {
		this.mainAlarmNum = mainAlarmNum;
	}

	public Date getMainAlarmSolveDate() {
		return mainAlarmSolveDate;
	}

	public void setMainAlarmSolveDate(Date mainAlarmSolveDate) {
		this.mainAlarmSolveDate = mainAlarmSolveDate;
	}

	public String getConfirmMainIfReListed() {
		return confirmMainIfReListed;
	}

	public void setConfirmMainIfReListed(String confirmMainIfReListed) {
		this.confirmMainIfReListed = confirmMainIfReListed;
	}
	
	
 }