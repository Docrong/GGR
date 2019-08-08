package com.boco.eoms.workplan.model;

/**
 * <p>Title: 月度作业计划类</p>
 * <p>Description: 月度作业计划信息，其中包括名称，网元，执行类型，部门，系统类型，网元类别等信息</p>
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
 * @hibernate.class table="TAW_WP_MONTHPLAN"
 */

public class TawwpMonthPlan
        implements Serializable, DataObject, Comparable {

    private String id; //标识
    private String name; //年度作业计划名称
    private String executeType; //填写类型（0：多人填写一份 1：多人填写多份 2：顺序填写）
    private String deptId; //所属部门
    private String sysTypeId; //系统类别
    private String netTypeId; //网元类型
    private String yearFlag; //年度标识
    private String monthFlag; //月度标识
    private String deleted; //删除标志
    private String cruser; //创建人
    private String crtime; //创建时间
    private String constituteState; //制定状态 0：待审批 1：通过 2：驳回
    private String executeState; //执行状态 0：未执行 1：执行中 2：归档 3:考核中（当制定状态为1时，执行状态才能由0变成1。）
    private String principal; //月度作业计划负责人（负责填写周报月度和归档）
    private String unicomType; //联通分类 联通系统专用
    private String reportFlag; //联通上报标识 联通系统专用
    private String yearPlanId; //年度作业计划ID
    private String checkUserId; //审批用户


    private TawwpModelPlan tawwpModelPlan; //作业计划模板对象
    private TawwpYearPlan tawwpYearPlan; //年度作业计划对象

    private TawwpNet tawwpNet; //网元信息
    private Set tawwpMonthExecutes = new HashSet(); //对应的月度作业计划执行内容集合
    private Set tawwpMonthChecks = new HashSet(); //月度作业计划的审批信息集合
    private Set tawwpExecuteReports = new HashSet(); //月度作业计划的汇报总结
    private Set tawwpExecuteContents = new HashSet(); //执行作业计划执行内容集合

    public TawwpMonthPlan() {
    }

    public TawwpMonthPlan(String _name, String _executeType, TawwpNet _tawwpNet,
                          String _deptId, String _sysTypeId, String _netTypeId,
                          String _deleted, String _cruser, String _crtime,
                          String _yearFlag, String _monthFlag,
                          String _constituteState, String _executeState,
                          String _principal, TawwpModelPlan _tawwpModelPlan,
                          TawwpYearPlan _tawwpYearPlan, Set _tawwpMonthExecutes,
                          Set _tawwpMonthChecks) {
        this.name = _name;
        this.executeType = _executeType;
        this.deptId = _deptId;
        this.tawwpNet = _tawwpNet;
        this.sysTypeId = _sysTypeId;
        this.netTypeId = _netTypeId;
        this.yearFlag = _yearFlag;
        this.monthFlag = _monthFlag;
        this.deleted = _deleted;
        this.cruser = _cruser;
        this.crtime = _crtime;
        this.constituteState = _constituteState;
        this.executeState = _executeState;
        this.principal = _principal;
        this.tawwpModelPlan = _tawwpModelPlan;
        this.tawwpYearPlan = _tawwpYearPlan;
        this.tawwpMonthExecutes = _tawwpMonthExecutes;
        this.tawwpMonthChecks = _tawwpMonthChecks;
        if (_tawwpYearPlan.getUnicomType() != null)
            this.unicomType = _tawwpYearPlan.getUnicomType();
    }

    public TawwpMonthPlan(String _name, String _executeType, TawwpNet _tawwpNet,
                          String _deptId, String _sysTypeId, String _netTypeId,
                          String _yearFlag, String _monthFlag,
                          String _deleted, String _cruser, String _crtime,
                          String _constituteState, String _executeState,
                          String _principal, TawwpModelPlan _tawwpModelPlan,
                          TawwpYearPlan _tawwpYearPlan) {
        this.name = _name;
        this.executeType = _executeType;
        this.deptId = _deptId;
        this.tawwpNet = _tawwpNet;
        this.sysTypeId = _sysTypeId;
        this.netTypeId = _netTypeId;
        this.yearFlag = _yearFlag;
        this.monthFlag = _monthFlag;
        this.deleted = _deleted;
        this.cruser = _cruser;
        this.crtime = _crtime;
        this.constituteState = _constituteState;
        this.executeState = _executeState;
        this.principal = _principal;
        this.tawwpModelPlan = _tawwpModelPlan;
        this.tawwpYearPlan = _tawwpYearPlan;
        if (_tawwpYearPlan.getUnicomType() != null)
            this.unicomType = _tawwpYearPlan.getUnicomType();
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
     * @hibernate.property column="NAMES"
     * length="100"
     * not-null="true"
     * update="true"
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property column="SYSTYPEID"
     * length="10"
     * not-null="false"
     * update="false"
     */

    public String getSysTypeId() {
        return sysTypeId;
    }

    public void setSysTypeId(String sysTypeId) {
        this.sysTypeId = sysTypeId;
    }

    /**
     * @hibernate.property column="NETTYPEID"
     * length="10"
     * not-null="false"
     * update="false"
     */

    public String getNetTypeId() {
        return netTypeId;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId;
    }

    /**
     * @hibernate.property column="YEARFLAG"
     * length="4"
     * not-null="true"
     * update="true"
     */
    public String getYearFlag() {
        return yearFlag;
    }

    public void setYearFlag(String yearFlag) {
        this.yearFlag = yearFlag;
    }

    /**
     * @hibernate.property column="MONTHFLAG"
     * length="2"
     * not-null="true"
     * update="false"
     */
    public String getMonthFlag() {
        return monthFlag;
    }

    public void setMonthFlag(String monthFlag) {
        this.monthFlag = monthFlag;
    }

    /**
     * @hibernate.property column="EXECUTETYPE"
     * length="1"
     * not-null="true"
     * update="true"
     */
    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    /**
     * @hibernate.property column="EXECUTESTATE"
     * length="1"
     * not-null="true"
     * update="true"
     */
    public String getExecuteState() {
        return executeState;
    }

    public void setExecuteState(String executeState) {
        this.executeState = executeState;
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
     * @hibernate.property column="CONSTITUTESTATE"
     * length="1"
     * not-null="true"
     * update="true"
     */
    public String getConstituteState() {
        return constituteState;
    }

    public void setConstituteState(String constituteState) {
        this.constituteState = constituteState;
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
     * @hibernate.property column="CRUSER"
     * length="20"
     * not-null="true"
     * update="false"
     */
    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    /**
     * @hibernate.property column="DELETED"
     * length="1"
     * not-null="true"
     * update="true"
     */
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @hibernate.property column="PRINCIPAL"
     * length="30"
     * not-null="false"
     * update="true"
     */
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * @hibernate.property column="UNICOMTYPE"
     * length="30"
     * not-null="false"
     * update="true"
     */
    public String getUnicomType() {
        return unicomType;
    }

    public void setUnicomType(String unicomType) {
        this.unicomType = unicomType;
    }

    /**
     * @hibernate.property column="REPORTFLAG"
     * length="2"
     * not-null="false"
     * update="true"
     */
    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    /**
     * @hibernate.many-to-one column="MODEL_PLAN_ID"
     * cascade="none"
     * not-null="false"
     */
    public TawwpModelPlan getTawwpModelPlan() {
        return tawwpModelPlan;
    }

    public void setTawwpModelPlan(TawwpModelPlan tawwpModelPlan) {
        this.tawwpModelPlan = tawwpModelPlan;
    }

    /**
     * @hibernate.many-to-one column="YEAR_PLAN_ID"
     * cascade="none"
     * not-null="false"
     */
    public TawwpYearPlan getTawwpYearPlan() {
        return tawwpYearPlan;
    }

    public void setTawwpYearPlan(TawwpYearPlan tawwpYearPlan) {
        this.tawwpYearPlan = tawwpYearPlan;
    }

    /**
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="save-update"
     * @hibernate.collection-key column="MONTH_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpMonthCheck"
     */
    public Set getTawwpMonthChecks() {
        return tawwpMonthChecks;
    }

    public void setTawwpMonthChecks(Set tawwpMonthChecks) {
        this.tawwpMonthChecks = tawwpMonthChecks;
    }

    /**
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="save-update"
     * @hibernate.collection-key column="MONTH_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpMonthCheck"
     */
    public Set getTawwpExecuteContents() {
        return tawwpExecuteContents;
    }

    public void setTawwpExecuteContents(Set tawwpExecuteContents) {
        this.tawwpExecuteContents = tawwpExecuteContents;
    }


    /**
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="all"
     * @hibernate.collection-key column="MONTH_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpMonthExecute"
     */
    public Set getTawwpMonthExecutes() {
        return tawwpMonthExecutes;
    }

    public void setTawwpMonthExecutes(Set tawwpMonthExecutes) {
        this.tawwpMonthExecutes = tawwpMonthExecutes;
    }

    /**
     * @hibernate.set inverse="true"
     * lazy="true"
     * @hibernate.collection-key column="MONTH_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpMonthExecute"
     */
    public Set getTawwpExecuteReports() {
        return tawwpExecuteReports;
    }

    public void setTawwpExecuteReports(Set tawwpExecuteReports) {
        this.tawwpExecuteReports = tawwpExecuteReports;
    }

    /**
     * @hibernate.many-to-one column="NETID"
     * cascade="none"
     * not-null="false"
     */
    public TawwpNet getTawwpNet() {
        return tawwpNet;
    }

    public void setTawwpNet(TawwpNet tawwpNet) {
        this.tawwpNet = tawwpNet;
    }

    public int compareTo(Object otherObject) {
        TawwpMonthPlan other = (TawwpMonthPlan) otherObject;
        if ((yearFlag + monthFlag).compareTo(other.getYearFlag() + other.getMonthFlag()) < 0)
            return 1;     //比较以时间作为标准，返回1代表对象大
        else if ((yearFlag + monthFlag).compareTo(other.getYearFlag() + other.getMonthFlag()) > 0)
            return -1;    //返回－1代表对象小
        else return 0;           //0表示相等
    }

    public String getYearPlanId() {
        return yearPlanId;
    }

    public void setYearPlanId(String yearPlanId) {
        this.yearPlanId = yearPlanId;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }


}
