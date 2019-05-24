package com.boco.eoms.testcard.controller;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

public class TawTestcardApplyForm extends ValidatorForm {
	public final static int ADD = 1;

	public final static int EDIT = 2;

	private int strutsAction;

	private Collection beanCollectionDN;

	private Collection beCollec;

	private Collection beCollep;

	private int id;

	private String userId;

	private String deptId;

	private String userName;

	private String deptName;

	private String formName;

	private String cardtype;
	
	private String cardtypename;

	private String cardpackage;

	private String cardpackagename;

	private String leaveid;

	private String leaveidname;

	private String applyreason;

	private String insertTime;

	private String status;

	private String statusName;

	private String phoneNumber;

	private String auditJson;
	
	private String auditOrgName;
	
	private String beginTime;
	
	private String endTime;

	private String auditUser;
	
	private String auditTime;
	
	private String auditInfo;
	
	private int formId;
	
	private String auditflag;
	
	private int taskId;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAuditOrgName() {
		return auditOrgName;
	}

	public void setAuditOrgName(String auditOrgName) {
		this.auditOrgName = auditOrgName;
	}

	public String getAuditJson() {
		return auditJson;
	}

	public void setAuditJson(String auditJson) {
		this.auditJson = auditJson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getApplyreason() {
		return applyreason;
	}

	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}

	public String getCardpackage() {
		return cardpackage;
	}

	public void setCardpackage(String cardpackage) {
		this.cardpackage = cardpackage;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getLeaveid() {
		return leaveid;
	}

	public void setLeaveid(String leaveid) {
		this.leaveid = leaveid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStrutsAction() {
		return strutsAction;
	}

	public void setStrutsAction(int strutsAction) {
		this.strutsAction = strutsAction;
	}

	public Collection getBeanCollectionDN() {
		return beanCollectionDN;
	}

	public void setBeanCollectionDN(Collection beanCollectionDN) {
		this.beanCollectionDN = beanCollectionDN;
	}

	public Collection getBeCollec() {
		return beCollec;
	}

	public void setBeCollec(Collection beCollec) {
		this.beCollec = beCollec;
	}

	public Collection getBeCollep() {
		return beCollep;
	}

	public void setBeCollep(Collection beCollep) {
		this.beCollep = beCollep;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardpackagename() {
		return cardpackagename;
	}

	public void setCardpackagename(String cardpackagename) {
		this.cardpackagename = cardpackagename;
	}

	public String getCardtypename() {
		return cardtypename;
	}

	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename;
	}

	public String getLeaveidname() {
		return leaveidname;
	}

	public void setLeaveidname(String leaveidname) {
		this.leaveidname = leaveidname;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getAuditflag() {
		return auditflag;
	}

	public void setAuditflag(String auditflag) {
		this.auditflag = auditflag;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

}
