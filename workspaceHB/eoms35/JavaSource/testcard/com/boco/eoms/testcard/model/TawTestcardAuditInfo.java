package com.boco.eoms.testcard.model;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;

public class TawTestcardAuditInfo {

	ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
	.getInstance().getBean("itawSystemUserManager");

	private int id;

	private String auditUser;

	private String auditUserName;

	private String auditTime;
	
	private String auditInfo;
	
	private String status;
	
	private String statusname;

	private int formId;
	
	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusname() {
    	if(StaticMethod.null2String(this.status).equals("9")){
    		this.statusname="草稿";
    	}
    	if(StaticMethod.null2String(this.status).equals("10")){
    		this.statusname="待审核";
    	}
    	if(StaticMethod.null2String(this.status).equals("11")){
    		this.statusname="驳回";
    	}
    	if(StaticMethod.null2String(this.status).equals("12")){
    		this.statusname="审核通过";
    	}
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getAuditUserName() {
		this.auditUserName = userMgr.getUserByuserid(auditUser).getUsername();
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	
}
