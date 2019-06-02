package com.boco.eoms.filemanager.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by .
 * User: wangsixuan
 * Date: 2008-12-10
 * Time: 15:29:50
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class SearchListForm  extends ActionForm {
	private String flowId;
	private String reportId;
	private String reportName;
	private String acceptDeptId;
    private String acceptDeptName;
    private String uploadTime;
    private String startTime;
    private String endTime;
    private String overtime;
    private String overtimeQuery;
    private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAcceptDeptId() {
		return acceptDeptId;
	}
	public void setAcceptDeptId(String acceptDeptId) {
		this.acceptDeptId = acceptDeptId;
	}
	public String getAcceptDeptName() {
		return acceptDeptName;
	}
	public void setAcceptDeptName(String acceptDeptName) {
		this.acceptDeptName = acceptDeptName;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getOvertimeQuery() {
		return overtimeQuery;
	}
	public void setOvertimeQuery(String overtimeQuery) {
		this.overtimeQuery = overtimeQuery;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
   
}
