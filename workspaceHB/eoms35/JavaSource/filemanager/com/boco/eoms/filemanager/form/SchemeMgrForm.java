package com.boco.eoms.filemanager.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-15
 * Time: 14:16:37
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class SchemeMgrForm  extends ActionForm {
    private String act;
    private String schemeId;
    private String topicId;
    private String title;
    private String reportDescription;
    private String faultClass;
    private String faultClassName;
    private String sendDeptId;
    private String sendDeptName;
    private String createUser;
    private String createUserName;
    private String acceptDeptId;
    private String acceptDeptName;
    private String[] fileUrl=null;
    private String specialtyId;
    private String specialtyName;
    private String sendContact;
    private String topicName;
    private String cycleType;
    private String cycle;
    private String cycleDescription;
    private int schemeAhead;
    private String schemeTime;
    private String combinType;
    private String combinTypeName;
    
//wagnsixuan add
    private String auditUserId; //审核人ID
    private String reportUserId; //接收人ID
    private String auditUserName; //审核人NAME
    private String reportUserName; //接收人NAME
    private String isAudit;//是否审核

	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getFaultClass() {
        return faultClass;
    }

    public void setFaultClass(String faultClass) {
        this.faultClass = faultClass;
    }

    public String getSendDeptId() {
        return sendDeptId;
    }

    public void setSendDeptId(String sendDeptId) {
        this.sendDeptId = sendDeptId;
    }

    public String getSendDeptName() {
        return sendDeptName;
    }

    public void setSendDeptName(String sendDeptName) {
        this.sendDeptName = sendDeptName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(String specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getSendContact() {
        return sendContact;
    }

    public void setSendContact(String sendContact) {
        this.sendContact = sendContact;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String[] getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String[] fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getFaultClassName() {
        return faultClassName;
    }

    public void setFaultClassName(String faultClassName) {
        this.faultClassName = faultClassName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycleDescription() {
        return cycleDescription;
    }

    public void setCycleDescription(String cycleDescription) {
        this.cycleDescription = cycleDescription;
    }

    public int getSchemeAhead() {
        return schemeAhead;
    }

    public void setSchemeAhead(int schemeAhead) {
        this.schemeAhead = schemeAhead;
    }

    public String getSchemeTime() {
        return schemeTime;
    }

    public void setSchemeTime(String schemeTime) {
        this.schemeTime = schemeTime;
    }

	public String getCombinType() {
		return combinType;
	}

	public void setCombinType(String combinType) {
		this.combinType = combinType;
	}

	public String getCombinTypeName() {
		return combinTypeName;
	}

	public void setCombinTypeName(String combinTypeName) {
		this.combinTypeName = combinTypeName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getReportUserName() {
		return reportUserName;
	}

	public void setReportUserName(String reportUserName) {
		this.reportUserName = reportUserName;
	}
}
