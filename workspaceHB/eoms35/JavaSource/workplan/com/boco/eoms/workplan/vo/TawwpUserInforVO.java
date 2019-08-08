package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 用户作业计划执行信息汇总类</p>
 * <p>Description: 提供用户所有要执行的作业计划，月度作业计划，年度作业计划等数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.util.List;

public class TawwpUserInforVO {

    private String cruser; //用户
    private String cruserName; //用户名
    private String yearFlag; //年度
    private String monthFlag; //月度
    private List yearPlanList; //年度需要执行的作业计划
    private List monthPlanList; //月度需要执行的作业计划
    private List executePlanList; //日常需要执行的作业计划
    private List executePlanDutyList; //值班需要执行的作业计划

    private int dutyCount; //值班执行数量
    private int dayCount; //日常执行数量

    public String getCruser() {
        if (cruser == null) {
            cruser = "";
        }

        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public String getCruserName() {
        if (cruserName == null) {
            cruserName = "";
        }

        return cruserName;
    }

    public void setCruserName(String cruserName) {
        this.cruserName = cruserName;
    }

    public List getExecutePlanDutyList() {
        return executePlanDutyList;
    }

    public void setExecutePlanDutyList(List executePlanDutyList) {
        this.executePlanDutyList = executePlanDutyList;
    }

    public List getExecutePlanList() {

        return executePlanList;
    }

    public void setExecutePlanList(List executePlanList) {
        this.executePlanList = executePlanList;
    }

    public String getMonthFlag() {
        if (monthFlag == null) {
            monthFlag = "";
        }

        return monthFlag;
    }

    public void setMonthFlag(String monthFlag) {
        this.monthFlag = monthFlag;
    }

    public List getMonthPlanList() {
        return monthPlanList;
    }

    public void setMonthPlanList(List monthPlanList) {
        this.monthPlanList = monthPlanList;
    }

    public String getYearFlag() {
        if (yearFlag == null) {
            yearFlag = "";
        }

        return yearFlag;
    }

    public void setYearFlag(String yearFlag) {
        this.yearFlag = yearFlag;
    }

    public List getYearPlanList() {

        return yearPlanList;
    }

    public void setYearPlanList(List yearPlanList) {
        this.yearPlanList = yearPlanList;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getDutyCount() {
        return dutyCount;
    }

    public void setDutyCount(int dutyCount) {
        this.dutyCount = dutyCount;
    }

}
