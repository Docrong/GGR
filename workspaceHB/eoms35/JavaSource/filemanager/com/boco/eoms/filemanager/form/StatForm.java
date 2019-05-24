package com.boco.eoms.filemanager.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-26
 * Time: 10:15:28
 * Boco Corporation
 * 部门：亿阳信通软件研究院 EOMS
 * 地址：北京市海淀区亮甲店130号亿阳大厦 12层3室
 * To change this template use File | Settings | File Templates.
 */
public class StatForm   extends ActionForm {
    private String act;
    private int statType;
    private String startTime;
    private String endTime;
    private String sort1_deptid;//sendDeptId;
    private String sort2_deptid; //acceptDeptId;
    private String report_id;
    private String report_id_name;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public int getStatType() {
        return statType;
    }

    public void setStatType(int statType) {
        this.statType = statType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSort1_deptid() {
        return sort1_deptid;
    }

    public void setSort1_deptid(String sort1_deptid) {
        this.sort1_deptid = sort1_deptid;
    }

    public String getSort2_deptid() {
        return sort2_deptid;
    }

    public void setSort2_deptid(String sort2_deptid) {
        this.sort2_deptid = sort2_deptid;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getReport_id_name() {
        return report_id_name;
    }

    public void setReport_id_name(String report_id_name) {
        this.report_id_name = report_id_name;
    }

}
