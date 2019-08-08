package com.boco.eoms.workplan.model;

/**
 * <p>Title: 月度作业计划执行人类</p>
 * <p>Description: 月度作业计划执行人信息，其中包括用户名称，执行月度作业计划，执行状态</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import java.util.*;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_MONTHEXECUTEUSER"
 */

public class TawwpMonthExecuteUser implements Serializable, DataObject {

    private String id; // 标识

    private String executer; // 执行人

    private String executerType; // 执行人类型（0：表示人员，1：表示部门，2：表示组 3：表示班次）

    private String executeRoom; // 执行机房（执行人为“班次”）

    private String executeDuty; // 执行班次（执行人为“班次”）

    private String state; // 0：审批通过 1：归档 2:驳回 3:考核中 4:确认后开始执行（执行中）

    private String content; // 执行总结（个人）

    private String yearFlag; // 年度

    private String monthFlag; // 月度

    private String reportDate; // 汇报时间

    private TawwpMonthPlan tawwpMonthPlan; // 月度作业计划

    /**
     * 关联月计划id
     */
    private String monthPlanId;

    public TawwpMonthExecuteUser() {
    }

    /**
     * @hibernate.id column="ID" length="32" unsaved-value="null"
     * generator-class="uuid.hex"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column="EXECUTER" length="100" not-null="false"
     * update="false"
     */
    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    /**
     * @hibernate.property column="EXECUTERTYPE" length="1" not-null="false"
     * update="false"
     */
    public String getExecuterType() {
        return executerType;
    }

    public void setExecuterType(String executerType) {
        this.executerType = executerType;
    }

    /**
     * @hibernate.property column="EXECUTEROOM" length="100" not-null="false"
     * update="false"
     */
    public String getExecuteRoom() {
        return executeRoom;
    }

    public void setExecuteRoom(String executeRoom) {
        this.executeRoom = executeRoom;
    }

    /**
     * @hibernate.property column="EXECUTEDUTY" length="1" not-null="false"
     * update="false"
     */
    public String getExecuteDuty() {
        return executeDuty;
    }

    public void setExecuteDuty(String executeDuty) {
        this.executeDuty = executeDuty;
    }

    /**
     * @hibernate.property column="YEARFLAG" length="4" not-null="true"
     * update="false"
     */
    public String getYearFlag() {
        return yearFlag;
    }

    public void setYearFlag(String yearFlag) {
        this.yearFlag = yearFlag;
    }

    /**
     * @hibernate.property column="MONTHFLAG" length="2" not-null="true"
     * update="false"
     */
    public String getMonthFlag() {
        return monthFlag;
    }

    public void setMonthFlag(String monthFlag) {
        this.monthFlag = monthFlag;
    }

    /**
     * @hibernate.property column="CONTENT" length="200" not-null="false"
     * update="false"
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @hibernate.property column="REPORTDATE" length="19" not-null="false"
     * update="false"
     */
    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @hibernate.property column="STATE" length="1" not-null="true"
     * update="true"
     */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @hibernate.many-to-one column="MONTH_PLAN_ID" cascade="none"
     * not-null="true"
     */
    public TawwpMonthPlan getTawwpMonthPlan() {
        return tawwpMonthPlan;
    }

    public void setTawwpMonthPlan(TawwpMonthPlan tawwpMonthPlan) {
        this.tawwpMonthPlan = tawwpMonthPlan;
    }

    /**
     * @return the monthPlanId
     */
    public String getMonthPlanId() {
        return monthPlanId;
    }

    /**
     * @param monthPlanId the monthPlanId to set
     */
    public void setMonthPlanId(String monthPlanId) {
        this.monthPlanId = monthPlanId;
    }

}
