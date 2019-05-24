package com.boco.eoms.filemanager.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-15
 * Time: 14:29:50
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class ReportMgrForm  extends ActionForm {
    private String act;
    private String flowId;
    private String reportId;
    private String topicId;
    private String acceptDeptId;
    private String acceptDeptName;
    private String sendTime;
    private String uploadTime;
    private String reportFileName;
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
    private String combinType;
    
//  add: wangsixuan
    private String isAudit;
    private String auditUserId;
    private String auditUserName;
    
    public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

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

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
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
//ZF add:
    private String[] fileList = null;
    public String[] getFileList() {
        return fileList;
    }
    public void setFileList(String[] fileList) {
        this.fileList = fileList;
    }
    private String templatFile = null;
    public String getTemplatFile() {
        return templatFile;
    }
    public void setTemplatFile(String templatFile) {
        this.templatFile = templatFile;
    }

	public String getCombinType() {
		return combinType;
	}

	public void setCombinType(String combinType) {
		this.combinType = combinType;
	}

	public int getReject() {
		return reject;
	}

	public void setReject(int reject) {
		this.reject = reject;
	}

}
