/*
 *
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultAuto implements Serializable {
	
	private String id;

	private String complaintType1;

	private String complaintType2;

	private String complaintType3;

	private String complaintType4;

	private String complaintType5;

	private String complaintType6;

	private String complaintType7;

	private String faultSite;

	private String colseSwitch;
	
	private String dealRoleT2;
	
	private String dealUserT2;
	
	private String dealDeptT2;
	
	private String dealRoleT1;
	
	private String dealUserT1;
	
	private String dealDeptT1;
	
	private String dealRole;
	
	private String dealUser;
	
	private String dealDept;
	
	private String remark1;
	
	private String remark2;
	
	private Date createDate;
	
	private String ruleType; //T1T2Role,autoHold,autoT1T2
	
	private String condition;
	
	private String commonFaultDesc;
	
	private String sheetType; //all,handler,auto,half
	
	private String title;
	
	private String equipmentName;
	
	private String alarmType;
	
	private String netSortOne;
	
	private String netSortTwo;
	
	private String netSortThree;
	
	
	
	

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDealDept() {
		return dealDept;
	}

	public void setDealDept(String dealDept) {
		this.dealDept = dealDept;
	}

	public String getDealDeptT1() {
		return dealDeptT1;
	}

	public void setDealDeptT1(String dealDeptT1) {
		this.dealDeptT1 = dealDeptT1;
	}

	public String getDealDeptT2() {
		return dealDeptT2;
	}

	public void setDealDeptT2(String dealDeptT2) {
		this.dealDeptT2 = dealDeptT2;
	}

	public String getDealRole() {
		return dealRole;
	}

	public void setDealRole(String dealRole) {
		this.dealRole = dealRole;
	}

	public String getDealRoleT1() {
		return dealRoleT1;
	}

	public void setDealRoleT1(String dealRoleT1) {
		this.dealRoleT1 = dealRoleT1;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getDealUserT1() {
		return dealUserT1;
	}

	public void setDealUserT1(String dealUserT1) {
		this.dealUserT1 = dealUserT1;
	}

	public String getDealUserT2() {
		return dealUserT2;
	}

	public void setDealUserT2(String dealUserT2) {
		this.dealUserT2 = dealUserT2;
	}
	
	public String getCommonFaultDesc() {
		return commonFaultDesc;
	}

	public void setCommonFaultDesc(String commonFaultDesc) {
		this.commonFaultDesc = commonFaultDesc;
	}



	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDealRoleT2() {
		return dealRoleT2;
	}

	public void setDealRoleT2(String dealRoleT2) {
		this.dealRoleT2 = dealRoleT2;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getColseSwitch() {
		return colseSwitch;
	}

	public void setColseSwitch(String colseSwitch) {
		this.colseSwitch = colseSwitch;
	}

	public String getComplaintType1() {
		return complaintType1;
	}

	public void setComplaintType1(String complaintType1) {
		this.complaintType1 = complaintType1;
	}

	public String getComplaintType2() {
		return complaintType2;
	}

	public void setComplaintType2(String complaintType2) {
		this.complaintType2 = complaintType2;
	}

	public String getComplaintType3() {
		return complaintType3;
	}

	public void setComplaintType3(String complaintType3) {
		this.complaintType3 = complaintType3;
	}

	public String getComplaintType4() {
		return complaintType4;
	}

	public void setComplaintType4(String complaintType4) {
		this.complaintType4 = complaintType4;
	}

	public String getComplaintType5() {
		return complaintType5;
	}

	public void setComplaintType5(String complaintType5) {
		this.complaintType5 = complaintType5;
	}

	public String getComplaintType6() {
		return complaintType6;
	}

	public void setComplaintType6(String complaintType6) {
		this.complaintType6 = complaintType6;
	}

	public String getComplaintType7() {
		return complaintType7;
	}

	public void setComplaintType7(String complaintType7) {
		this.complaintType7 = complaintType7;
	}

	public String getFaultSite() {
		return faultSite;
	}

	public void setFaultSite(String faultSite) {
		this.faultSite = faultSite;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getNetSortOne() {
		return netSortOne;
	}

	public void setNetSortOne(String netSortOne) {
		this.netSortOne = netSortOne;
	}

	public String getNetSortThree() {
		return netSortThree;
	}

	public void setNetSortThree(String netSortThree) {
		this.netSortThree = netSortThree;
	}

	public String getNetSortTwo() {
		return netSortTwo;
	}

	public void setNetSortTwo(String netSortTwo) {
		this.netSortTwo = netSortTwo;
	}
	
	

}
