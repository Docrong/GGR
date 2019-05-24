package com.boco.eoms.workbench.report.webapp.form;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;

public class TawWorkbenchReportForm extends BaseForm{
	private String id;
	private String reportPerson;//报告人userid
	private String reportPersonName;//报告人username
	private String reportTime;//报告时间
	private String summary;//小结
	private String tomorrowTarget;//明日目标  用于日报
	private String auditer;//审核人
	private String reportType;//报告类型：日报0，周报1
	private String auditStatus;// 2审核，1已提交未审核，0未提交
	private String auditTime;//审核时间
	private String auditName;//审核人姓名
	private String deleted;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}

	
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	
	public String getTomorrowTarget() {
		return tomorrowTarget;
	}
	public void setTomorrowTarget(String tomorrowTarget) {
		this.tomorrowTarget = tomorrowTarget;
	}

	
	public String getAuditer() {
		return auditer;
	}
	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	
	public String getReportPersonName() {
		return reportPersonName;
	}
	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

}
