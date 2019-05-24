package com.boco.eoms.filemanager.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-15
 * Time: 14:25:42
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class ReportMgrBean{
    private String reportId;
    private String topicId;
    private String schemeId;
    private String topicName;
    private String mgrDeptName;
    private String reportStatusName;
    private String overtimeName;
    private String createTime;
    private String reportName;
    private String mgrDept;
    private int reportStatus;
    private int overtime;
    private String deadline;


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

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getMgrDept() {
        return mgrDept;
    }

    public void setMgrDept(String mgrDept) {
        this.mgrDept = mgrDept;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getMgrDeptName() {
        return mgrDeptName;
    }

    public void setMgrDeptName(String mgrDeptName) {
        this.mgrDeptName = mgrDeptName;
    }

    public String getReportStatusName() {
        return reportStatusName;
    }

    public void setReportStatusName(String reportStatusName) {
        this.reportStatusName = reportStatusName;
    }

    public String getOvertimeName() {
        return overtimeName;
    }

    public void setOvertimeName(String overtimeName) {
        this.overtimeName = overtimeName;
    }
}
