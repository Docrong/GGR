package com.boco.eoms.filemanager.form;

/**
 * Created by IntelliJ IDEA. User: wangsixuan Date: 2008-12-9 Time: 9:48:32 Boco
 * Corporation ���ţ�������ͨ����о�Ժ EOMS ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class AuditListBean {
	private int auditId;

	private String flowId;

	private int statusOld;

	private int statusNew;

	private String status;

	private String auditUserId;

	private String auditTime;

	private String auditInfo;

	private String auditUserName;

	private String reportName;

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
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

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public int getStatusNew() {
		return statusNew;
	}

	public void setStatusNew(int statusNew) {
		this.statusNew = statusNew;
	}

	public int getStatusOld() {
		return statusOld;
	}

	public void setStatusOld(int statusOld) {
		this.statusOld = statusOld;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}