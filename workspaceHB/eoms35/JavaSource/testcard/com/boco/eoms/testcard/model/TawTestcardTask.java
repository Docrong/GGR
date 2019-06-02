package com.boco.eoms.testcard.model;

public class TawTestcardTask {

	private int id;

	private int formId;
	
	private String auditOrg;
	
	private String auditType;
	
	private int taskStatus;

	public String getAuditOrg() {
		return auditOrg;
	}

	public void setAuditOrg(String auditOrg) {
		this.auditOrg = auditOrg;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}
	
}
