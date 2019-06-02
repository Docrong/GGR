package com.boco.eoms.filemanager.form;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-1
 * Time: 10:48:32
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class ReportListBean {
    private String flowId;
    private String reportId;
    private String reportName;
    private String sendDeptName;
    private String topicId;
    private String topicName;
    private String acceptDeptId;
    private String acceptDeptName;
    private String sendTime;
    private String uploadTime;
    private int status;
    private String statusName;
    private int reject;
    private int overtime;
    private String overtimeName;
    private String dealUserId;
    private String dealUserName;
    private String replyInfo;
    private String acceptContact;
    private String[] fileUrl=null;
    private String deadline;
    
    //wangsixuan add:
    private String isAudit;
    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public String getOvertimeName() {
        return overtimeName;
    }

    public void setOvertimeName(String overtimeName) {
        this.overtimeName = overtimeName;
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public String getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(String replyInfo) {
        this.replyInfo = replyInfo;
    }

    public String getAcceptContact() {
        return acceptContact;
    }

    public void setAcceptContact(String acceptContact) {
        this.acceptContact = acceptContact;
    }

    public String[] getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String[] fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getSendDeptName() {
        return sendDeptName;
    }

    public void setSendDeptName(String sendDeptName) {
        this.sendDeptName = sendDeptName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

	public int getReject() {
		return reject;
	}

	public void setReject(int reject) {
		this.reject = reject;
	}

	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
}
