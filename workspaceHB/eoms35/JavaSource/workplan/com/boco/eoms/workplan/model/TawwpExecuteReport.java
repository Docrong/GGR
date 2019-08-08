package com.boco.eoms.workplan.model;

/**
 * <p>Title: 执行作业计划汇报类</p>
 * <p>Description: 执行作业计划汇报类信息，其中包括汇报人，周报月报标志，汇报内容，时间等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.*;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_EXECUTEREPORT"
 */

public class TawwpExecuteReport
        implements Serializable, DataObject {

    private String id; //标识
    private String deptId; //部门
    private String reportType; //汇报类型 0：周报 1：月报
    private String startDate; //汇报的开始时间
    private String endDate; //汇报的结束时间
    private String content; //汇报信息
    private String reportUser; //汇报人
    private String crtime; //创建时间 （按创建时间排序）
    private String reportFlag; //注意表示 0：正常 1：提醒
    private String remark; //备注
    private String advice; //建议

    private TawwpMonthPlan tawwpMonthPlan; //月度作业计划对象

    public TawwpExecuteReport() {
    }


    public TawwpExecuteReport(String _deptId, String _reportType,
                              String _startDate, String _endDate, String _content,
                              String _reportUser, String _crtime,
                              String _reportFlag, String _remark, String _advice,
                              TawwpMonthPlan _tawwpMonthPlan) {
        this.deptId = _deptId;
        this.reportType = _reportType;
        this.startDate = _startDate;
        this.endDate = _endDate;
        this.content = _content;
        this.reportUser = _reportUser;
        this.crtime = _crtime;
        this.reportFlag = _reportFlag;
        this.remark = _remark;
        this.advice = _advice;
        this.tawwpMonthPlan = _tawwpMonthPlan;
    }

    /**
     * @hibernate.id column="ID"
     * length="32"
     * unsaved-value="null"
     * generator-class="uuid.hex"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column="REMARK"
     * length="200"
     * not-null="false"
     * update="false"
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @hibernate.property column="STARTDATE"
     * length="19"
     * not-null="false"
     * update="false"
     */
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @hibernate.property column="ENDDATE"
     * length="19"
     * not-null="false"
     * update="false"
     */
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @hibernate.property column="DEPTID"
     * length="10"
     * not-null="true"
     * update="false"
     */
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @hibernate.property column="CRTIME"
     * length="19"
     * not-null="true"
     * update="false"
     */
    public String getCrtime() {
        return crtime;
    }

    public void setCrtime(String crtime) {
        this.crtime = crtime;
    }

    /**
     * @hibernate.property column="CONTENT"
     * length="200"
     * not-null="false"
     * update="false"
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @hibernate.property column="ADVICE"
     * length="200"
     * not-null="false"
     * update="false"
     */
    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    /**
     * @hibernate.property column="REPORTFLAG"
     * length="1"
     * not-null="false"
     * update="false"
     */
    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    /**
     * @hibernate.property column="REPORTTYPE"
     * length="1"
     * not-null="false"
     * update="false"
     */
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * @hibernate.property column="REPORTUSER"
     * length="20"
     * not-null="true"
     * update="false"
     */
    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    /**
     * @hibernate.many-to-one column="MONTH_PLAN_ID"
     * cascade="none"
     * not-null="true"
     */
    public TawwpMonthPlan getTawwpMonthPlan() {
        return tawwpMonthPlan;
    }

    public void setTawwpMonthPlan(TawwpMonthPlan tawwpMonthPlan) {
        this.tawwpMonthPlan = tawwpMonthPlan;
    }

}
