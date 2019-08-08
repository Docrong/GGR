package com.boco.eoms.workplan.model;

/**
 * <p>Title: 作业计划模板执行内容�?/p>
 * <p>Description: 作业计划模板信息，其中包括模板执行内容的名称，周期，表格，默认填写等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.*;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_MODELEXECUTE"
 */

public class TawwpModelExecute implements Serializable, DataObject {

    /**
     *
     */
    private static final long serialVersionUID = -8723260640673505859L;

    private String id; // 标识

    private String name; // 作业计划模板执行内容名称

    private String origin; // 来源 0:内部 1:接口

    private String cycle; // 周期

    private String remark; // 执行帮助

    private String note;//备注

    private String format; // 填写格式

    private String validate; // 验证格式

    private String formId; // 执行表格标识

    private String deleted; // 删除标志

    private String cruser; // 创建�?

    private String crtime; // 创建时间

    private String serialNo; // 序列�?

    private String xlsX; // excel文件位置（横坐标�?

    private String xlsY; // excel文件位置（纵坐标�?

    private String netTypeId; // 网元类型

    private String monthFlag; // 第几个月

    private TawwpModelGroup tawwpModelGroup; // 作业计划执行内容组织关系

    private TawwpModelPlan tawwpModelPlan; // 作业计划模板对象

    private String isHoliday; // 是否安排节假日排计划

    private String isWeekend;// 是否安排周末作计�?

    private String executeMonth;// 执行月份

    private int executeTimes; // 第几月执�?

    private String botype; // 业务类型

    private String executedeptlevel; // 执行单位级别

    private String appdesc; // 适用说明

    private String fileFlag; // 是否需要上传附件

    private String executeDay = "5"; //执行日期（由用户选择）

    private String accessories; // 指导文件
    private String taskid;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(String fileFlag) {
        this.fileFlag = fileFlag;
    }

    public String getBotype() {
        return botype;
    }

    public void setBotype(String botype) {
        this.botype = botype;
    }

    public String getExecutedeptlevel() {
        return executedeptlevel;
    }

    public void setExecutedeptlevel(String executedeptlevel) {
        this.executedeptlevel = executedeptlevel;
    }

    public String getAppdesc() {
        return appdesc;
    }

    public void setAppdesc(String appdesc) {
        this.appdesc = appdesc;
    }

    public String getExecuteMonth() {
        return executeMonth;
    }

    public void setExecuteMonth(String executeMonth) {
        this.executeMonth = executeMonth;
    }

    public int getExecuteTimes() {
        return executeTimes;
    }

    public void setExecuteTimes(int executeTimes) {
        this.executeTimes = executeTimes;
    }

    public TawwpModelExecute() {

    }

    public TawwpModelExecute(String _name, String _botype, String _executedeptlevel, String _appdesc, TawwpModelPlan _tawwpModelPlan,
                             TawwpModelGroup _tawwpModelGroup, String _origin, String _cycle,
                             String _remark, String _note, String _accessories, String _format, String _validate, String _formId,
                             String _netTypeId, String _deleted, String _cruser, String _crtime,
                             String _serialNo, String _xlsX, String _xlsY, String _isHoliday, String _isWeekend, String _fileFlag, String _executeMonth, int _executeTimes,
                             String executeDay) {
        this.name = _name;
        this.botype = _botype;
        this.executedeptlevel = _executedeptlevel;
        this.appdesc = _appdesc;
        this.tawwpModelPlan = _tawwpModelPlan;
        this.tawwpModelGroup = _tawwpModelGroup;
        this.origin = _origin;
        this.cycle = _cycle;
        this.remark = _remark;
        this.note = _note;
        this.accessories = _accessories;
        this.format = _format;
        this.validate = _validate;
        this.formId = _formId;
        this.deleted = _deleted;
        this.crtime = _crtime;
        this.cruser = _cruser;
        this.serialNo = _serialNo;
        this.xlsX = _xlsX;
        this.xlsY = _xlsY;
        this.netTypeId = _netTypeId;
        this.isHoliday = _isHoliday;
        this.isWeekend = _isWeekend;
        this.executeMonth = _executeMonth;
        this.executeTimes = _executeTimes;
        this.fileFlag = _fileFlag;
        this.accessories = _accessories;
        this.executeDay = executeDay;
    }

    public TawwpModelExecute(String _name, String _botype, String _executedeptlevel, String _appdesc, TawwpModelPlan _tawwpModelPlan,
                             TawwpModelGroup _tawwpModelGroup, String _origin, String _cycle,
                             String _remark, String _accessories, String _format, String _validate, String _formId,
                             String _netTypeId, String _deleted, String _cruser, String _crtime,
                             String _serialNo, String _xlsX, String _xlsY, String _isHoliday, String _isWeekend, String _fileFlag, String _executeMonth, int _executeTimes,
                             String executeDay) {
        this.name = _name;
        this.botype = _botype;
        this.executedeptlevel = _executedeptlevel;
        this.appdesc = _appdesc;
        this.tawwpModelPlan = _tawwpModelPlan;
        this.tawwpModelGroup = _tawwpModelGroup;
        this.origin = _origin;
        this.cycle = _cycle;
        this.remark = _remark;
        this.accessories = _accessories;
        this.format = _format;
        this.validate = _validate;
        this.formId = _formId;
        this.deleted = _deleted;
        this.crtime = _crtime;
        this.cruser = _cruser;
        this.serialNo = _serialNo;
        this.xlsX = _xlsX;
        this.xlsY = _xlsY;
        this.netTypeId = _netTypeId;
        this.isHoliday = _isHoliday;
        this.isWeekend = _isWeekend;
        this.executeMonth = _executeMonth;
        this.executeTimes = _executeTimes;
        this.fileFlag = _fileFlag;
        this.accessories = _accessories;
        this.executeDay = executeDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column="NAMES" length="100" not-null="true"
     * update="true"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property column="ORIGIN" length="1" not-null="true"
     * update="false"
     */
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @hibernate.property column="CRTIME" length="19" not-null="true"
     * update="false"
     */
    public String getCrtime() {
        return crtime;
    }

    public void setCrtime(String crtime) {
        this.crtime = crtime;
    }

    /**
     * @hibernate.property column="CRUSER" length="20" not-null="true"
     * update="false"
     */
    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    /**
     * @hibernate.property column="DELETED" length="1" not-null="true"
     * update="true"
     */
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @hibernate.many-to-one column="MODEL_PLAN_ID" cascade="none"
     * not-null="true"
     */
    public TawwpModelPlan getTawwpModelPlan() {
        return tawwpModelPlan;
    }

    public void setTawwpModelPlan(TawwpModelPlan tawwpModelPlan) {
        this.tawwpModelPlan = tawwpModelPlan;
    }

    /**
     * @hibernate.property column="CYCLES" length="1" not-null="true"
     * update="true"
     */
    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    /**
     * @hibernate.property column="FORMAT" length="100" not-null="false"
     * update="true"
     */
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @hibernate.property column="FORMID" length="255" not-null="false"
     * update="true"
     */
    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * @hibernate.property column="REMARK" length="255" not-null="false"
     * update="true"
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @hibernate.property column="VALIDATES" length="255" not-null="false"
     * update="true"
     */
    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    /**
     * @hibernate.property column="SERIALNO" length="5" not-null="false"
     * update="true"
     */
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @hibernate.property column="XLSX" length="3" not-null="false"
     * update="true"
     */
    public String getXlsX() {
        return xlsX;
    }

    public void setXlsX(String xlsX) {
        this.xlsX = xlsX;
    }

    /**
     * @hibernate.property column="XLSY" length="3" not-null="false"
     * update="true"
     */
    public String getXlsY() {
        return xlsY;
    }

    public void setXlsY(String xlsY) {
        this.xlsY = xlsY;
    }

    /**
     * @hibernate.many-to-one column="MODEL_GROUP_ID" cascade="none"
     * not-null="false"
     */
    public TawwpModelGroup getTawwpModelGroup() {
        return tawwpModelGroup;
    }

    public void setTawwpModelGroup(TawwpModelGroup tawwpModelGroup) {
        this.tawwpModelGroup = tawwpModelGroup;
    }

    /**
     * @hibernate.property column="NETTYPEID" length="10" not-null="false"
     * update="false"
     */
    public String getNetTypeId() {
        return netTypeId;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((this.id == null) || ((TawwpModelExecute) obj).id == null)
            return false;
        return this.id.equals(((TawwpModelExecute) obj).id);
    }

    public String getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

    public String getIsWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(String isWeekend) {
        this.isWeekend = isWeekend;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getExecuteDay() {
        return executeDay;
    }

    public void setExecuteDay(String executeDay) {
        this.executeDay = executeDay;
    }

    public String getMonthFlag() {
        return monthFlag;
    }

    public void setMonthFlag(String monthFlag) {
        this.monthFlag = monthFlag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
