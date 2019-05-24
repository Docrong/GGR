package com.boco.eoms.sheet.transprovincial.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:跨省集客业务工单
 * </p>
 * <p>
 * Description:跨省集客业务工单
 * </p>
 * <p>
 * Thu Sep 27 14:32:21 CST 2012
 * </p>
 * 
 * @author ph
 * @version 3.5
 * 
 */
 
 public class TransprovincialMain extends BaseMain {
	
	/**
 	*
 	* 发起方集客部客户经理
	*
	*/     
	private java.lang.String manager;
 					
	
	
	/**
 	*
 	* 客户经理联系电话
	*
	*/     
	private java.lang.String managerNum;
 					
	
	
	/**
 	*
 	* 产品名称
	*
	*/     
	private java.lang.String prodouct;
 					
	
	
	/**
 	*
 	* 集团编号
	*
	*/     
	private java.lang.String groupNum;
 					
	
	
	/**
 	*
 	* 集团名称
	*
	*/     
	private java.lang.String groupName;
 					
	
	
	/**
 	*
 	* 客户服务等级
	*
	*/     
	private java.lang.String customGrade;
 					
	
	
	/**
 	*
 	* 业务保障等级
	*
	*/     
	private java.lang.String businessGrade;
 					
	
	
	/**
 	*
 	* 任务类型
	*
	*/     
	private java.lang.String taskType;
 					
	
	
	/**
 	*
 	* 所属地市
	*
	*/     
	private java.lang.String area;
 					
	
	
	/**
 	*
 	* 带宽
	*
	*/     
	private java.lang.String bandWidth;
 					
	
	
	/**
 	*
 	* 专线数量
	*
	*/     
	private java.lang.String lineNum;
 					
	
	
	/**
 	*
 	* A端地市
	*
	*/     
	private java.lang.String atypearea;
 					
	
	
	/**
 	*
 	* Z端地市
	*
	*/     
	private java.lang.String ztypearea;
 					
	
	
	/**
 	*
 	* 资源确认汇总表
	*
	*/     
	private java.lang.String resourceReport;
	
	
	/**
 	*
 	* 集团级别
	*
	*/     
	private java.lang.String groupLevel;
	
	
	
	/**
 	*
 	* 集团级别
	*
	*/     
	private java.lang.String mainTaskDescription;
 					
	

	/**
 	*
 	* 保存派发对象
	*
	*/  
	private java.lang.String sendObject;
	
	public void setManager(java.lang.String manager) {
		this.manager= manager; 
	}
 	
	public java.lang.String getManager() {
		return this.manager;
	}			

	
	public void setManagerNum(java.lang.String managerNum) {
		this.managerNum= managerNum; 
	}
 	
	public java.lang.String getManagerNum() {
		return this.managerNum;
	}			

	
	public void setProdouct(java.lang.String prodouct) {
		this.prodouct= prodouct; 
	}
 	
	public java.lang.String getProdouct() {
		return this.prodouct;
	}			

	
	public void setGroupNum(java.lang.String groupNum) {
		this.groupNum= groupNum; 
	}
 	
	public java.lang.String getGroupNum() {
		return this.groupNum;
	}			

	
	public void setGroupName(java.lang.String groupName) {
		this.groupName= groupName; 
	}
 	
	public java.lang.String getGroupName() {
		return this.groupName;
	}			

	
	public void setCustomGrade(java.lang.String customGrade) {
		this.customGrade= customGrade; 
	}
 	
	public java.lang.String getCustomGrade() {
		return this.customGrade;
	}			

	
	public void setBusinessGrade(java.lang.String businessGrade) {
		this.businessGrade= businessGrade; 
	}
 	
	public java.lang.String getBusinessGrade() {
		return this.businessGrade;
	}			

	
	public void setTaskType(java.lang.String taskType) {
		this.taskType= taskType; 
	}
 	
	public java.lang.String getTaskType() {
		return this.taskType;
	}			

	
	public void setArea(java.lang.String area) {
		this.area= area; 
	}
 	
	public java.lang.String getArea() {
		return this.area;
	}			

	
	public void setBandWidth(java.lang.String bandWidth) {
		this.bandWidth= bandWidth; 
	}
 	
	public java.lang.String getBandWidth() {
		return this.bandWidth;
	}			

	
	public void setLineNum(java.lang.String lineNum) {
		this.lineNum= lineNum; 
	}
 	
	public java.lang.String getLineNum() {
		return this.lineNum;
	}			

	
	public void setAtypearea(java.lang.String atypearea) {
		this.atypearea= atypearea; 
	}
 	
	public java.lang.String getAtypearea() {
		return this.atypearea;
	}			

	
	public void setZtypearea(java.lang.String ztypearea) {
		this.ztypearea= ztypearea; 
	}
 	
	public java.lang.String getZtypearea() {
		return this.ztypearea;
	}			

	
	public void setResourceReport(java.lang.String resourceReport) {
		this.resourceReport= resourceReport; 
	}
 	
	public java.lang.String getResourceReport() {
		return this.resourceReport;
	}			



	public java.lang.String getSendObject() {
		return sendObject;
	}

	public void setSendObject(java.lang.String sendObject) {
		this.sendObject = sendObject;
	}

	public java.lang.String getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(java.lang.String groupLevel) {
		this.groupLevel = groupLevel;
	}

	public java.lang.String getMainTaskDescription() {
		return mainTaskDescription;
	}

	public void setMainTaskDescription(java.lang.String mainTaskDescription) {
		this.mainTaskDescription = mainTaskDescription;
	}
 }