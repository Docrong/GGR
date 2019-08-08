package com.boco.eoms.workbench.report.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * <a href="TawWorkbenchReport.java.html"><i>View Source</i></a>
 *
 * @author
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_workbench_report"
 */


public class TawWorkbenchReport extends BaseObject {

    private String id;
    private String reportPerson;//报告人userid
    private String reportPersonName;//报告人username
    private String reportTime;//报告时间
    private String summary;//小结
    private String tomorrowTarget;//明日目标  用于日报
    private String auditer;//审核人
    private String auditName;//审核人姓名
    private String reportType;//报告类型：日报0，周报1
    private String auditStatus;// 审核状态 2审核，1已提交未审核，0未提交
    private String auditTime;//审核时间
    private String deleted;

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
     */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getReportPerson() {
        return reportPerson;
    }

    public void setReportPerson(String reportPerson) {
        this.reportPerson = reportPerson;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * @hibernate.property length="500" not-null="false" unique="true"
     */

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getTomorrowTarget() {
        return tomorrowTarget;
    }

    public void setTomorrowTarget(String tomorrowTarget) {
        this.tomorrowTarget = tomorrowTarget;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * @hibernate.property length="8" not-null="false" unique="true"
     */

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @hibernate.property length="8" not-null="false" unique="true"
     */

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    /**
     * @hibernate.property length="100" not-null="false" unique="true"
     */

    public String getReportPersonName() {
        return reportPersonName;
    }

    public void setReportPersonName(String reportPersonName) {
        this.reportPersonName = reportPersonName;
    }


}
