package com.boco.eoms.workplan.model;

/**
 * <p>Title: 月度作业计划执行内容�?/p>
 * <p>Description: 月度作业计划执行内容信息，其中包括年度执行内容的名称，执行人，执行时间，周期，表格，默认填写�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_MONTHEXECUTE"
 */

public class TawwpMonthExecute implements Serializable, DataObject {

	private String id; // 标识

	private String name; // 年度作业计划执行内容名称

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

	private String command; // 命令标识(智能巡检接口)

	private String commandName; // 命令名称(智能巡检接口)

	private String executer; // 执行�?

	private String executerType; // 执行人类型（0：表示人员，1：表示部门，2：表示组 3：表示班次）

	private String executeRoom; // 执行机房（执行人为“班次”）

	private String executeDuty; // 执行班次（执行人为“班次”）

	private String executeDate; // 执行时间（执行列�?通过时间序列形式表示如：�?001000000100000010000001000111”，1标识当日执行�?

	private String executeFlag; // 当月执行内容的执行状�?0：未执行 1：执行完�?

	private String isHoliday; // 是否安排节假日排计划

	private String isWeekend;// 是否安排周末作计�?

	private String botype; // 业务类型

	private String executedeptlevel; // 执行单位级别

	private String appdesc; // 适用说明
	
	private String accessories; //指导文件
	
	private String qualityCheckUser; // 质检人

	private String startHH; // 每天计划开始时�jian间细化到小�时

	private String endHH; // 每天计划结束时间间细化到小�时

	private TawwpModelGroup tawwpModelGroup; // 作业计划执行内容组织关系

	private TawwpMonthPlan tawwpMonthPlan; // 月度作业计划对象

	private TawwpYearExecute tawwpYearExecute; // 年度作业计划执行内容

	private Set tawwpExecuteContents = new HashSet(); // 对应的作业计划执行内�?

	private String fileFlag; // 是否需要上传附件

	private String executeDay; // 执行日期（由用户选择）

	public String getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}

	public TawwpMonthExecute() {
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

	public TawwpMonthExecute(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _cycle,
			String _remark, String _format, String _validate, String _formId,
			String _deleted, String _cruser, String _crtime, String _serialNo,
			String _xlsX, String _xlsY, String _netTypeId, String _command,
			String _executer, String _executerType, String _executeRoom,
			String _executeDuty, String _executeDate, String _executeFlag,
			TawwpModelGroup _tawwpModelGroup, TawwpMonthPlan _tawwpMonthPlan,
			TawwpYearExecute _tawwpYearExecute, Set tawwpExecuteContents,
			String _isHoliday, String _isWeekend, String _fileFlag,
			String _qualityCheckUser, String _executeDay) {
		this.name = _name;
		this.botype = _botype;
		this.executedeptlevel = _executedeptlevel;
		this.appdesc = _appdesc;
		this.cycle = _cycle;
		this.remark = _remark;
		this.format = _format;
		this.validate = _validate;
		this.formId = _formId;
		this.deleted = _deleted;
		this.cruser = _cruser;
		this.crtime = _crtime;

		if (null != _tawwpYearExecute.getSerialNo())
			this.serialNo = _tawwpYearExecute.getSerialNo();
		else
			this.serialNo = _serialNo;
		;

		if (null != _tawwpYearExecute.getXlsX())
			this.xlsX = _tawwpYearExecute.getXlsX();
		else
			this.xlsX = _xlsX;

		if (null != _tawwpYearExecute.getXlsY())
			this.xlsY = _tawwpYearExecute.getXlsY();
		else
			this.xlsY = _xlsY;

		this.netTypeId = _netTypeId;
		this.command = _command;
		this.executer = _executer;
		this.executeFlag = _executeFlag;
		this.executerType = _executerType;
		this.executeRoom = _executeRoom;
		this.executeDuty = _executeDuty;
		this.executeDate = _executeDate;
		this.tawwpModelGroup = _tawwpModelGroup;
		this.tawwpMonthPlan = _tawwpMonthPlan;
		this.tawwpYearExecute = _tawwpYearExecute;
		this.tawwpExecuteContents = tawwpExecuteContents;
		this.isHoliday = _isHoliday;
		this.isWeekend = _isWeekend;
		this.fileFlag = _fileFlag;
		this.qualityCheckUser = _qualityCheckUser;
		this.executeDay = _executeDay;
	}
	public TawwpMonthExecute(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _cycle,
			String _remark,String _note, String _format, String _validate, String _formId,
			String _deleted, String _cruser, String _crtime, String _serialNo,
			String _xlsX, String _xlsY, String _netTypeId, String _command,
			String _executer, String _executerType, String _executeRoom,
			String _executeDuty, String _executeDate, String _executeFlag,
			TawwpModelGroup _tawwpModelGroup, TawwpMonthPlan _tawwpMonthPlan,
			TawwpYearExecute _tawwpYearExecute, Set tawwpExecuteContents,
			String _isHoliday, String _isWeekend, String _fileFlag,
			String _qualityCheckUser, String _executeDay) {
		this.name = _name;
		this.botype = _botype;
		this.executedeptlevel = _executedeptlevel;
		this.appdesc = _appdesc;
		this.cycle = _cycle;
		this.remark = _remark;
		this.note = _note;
		this.format = _format;
		this.validate = _validate;
		this.formId = _formId;
		this.deleted = _deleted;
		this.cruser = _cruser;
		this.crtime = _crtime;

		if (null != _tawwpYearExecute.getSerialNo())
			this.serialNo = _tawwpYearExecute.getSerialNo();
		else
			this.serialNo = _serialNo;
		;

		if (null != _tawwpYearExecute.getXlsX())
			this.xlsX = _tawwpYearExecute.getXlsX();
		else
			this.xlsX = _xlsX;

		if (null != _tawwpYearExecute.getXlsY())
			this.xlsY = _tawwpYearExecute.getXlsY();
		else
			this.xlsY = _xlsY;

		this.netTypeId = _netTypeId;
		this.command = _command;
		this.executer = _executer;
		this.executeFlag = _executeFlag;
		this.executerType = _executerType;
		this.executeRoom = _executeRoom;
		this.executeDuty = _executeDuty;
		this.executeDate = _executeDate;
		this.tawwpModelGroup = _tawwpModelGroup;
		this.tawwpMonthPlan = _tawwpMonthPlan;
		this.tawwpYearExecute = _tawwpYearExecute;
		this.tawwpExecuteContents = tawwpExecuteContents;
		this.isHoliday = _isHoliday;
		this.isWeekend = _isWeekend;
		this.fileFlag = _fileFlag;
		this.qualityCheckUser = _qualityCheckUser;
		this.executeDay = _executeDay;
	}

	/**
	 * @hibernate.id column="ID" length="32" unsaved-value="null"
	 *               generator-class="uuid.hex"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="NAMES" length="100" not-null="true"
	 *                     update="true"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="NETTYPEID" length="10" not-null="false"
	 *                     update="false"
	 */
	public String getNetTypeId() {
		return netTypeId;
	}

	public void setNetTypeId(String netTypeId) {
		this.netTypeId = netTypeId;
	}

	/**
	 * @hibernate.property column="REMARK" length="200" not-null="false"
	 *                     update="true"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.property column="SERIALNO" length="20" not-null="false"
	 *                     update="true"
	 */
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @hibernate.property column="COMMAND" length="100" not-null="false"
	 *                     update="true"
	 */
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @hibernate.property column="COMMANDNAME" length="100" not-null="false"
	 *                     update="true"
	 */
	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	/**
	 * @hibernate.property column="CRTIME" length="19" not-null="true"
	 *                     update="false"
	 */
	public String getCrtime() {
		return crtime;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}

	/**
	 * @hibernate.property column="CRUSER" length="20" not-null="true"
	 *                     update="false"
	 */
	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	/**
	 * @hibernate.property column="CYCLES" length="3" not-null="true"
	 *                     update="false"
	 */
	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	/**
	 * @hibernate.property column="DELETED" length="1" not-null="true"
	 *                     update="true"
	 */
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @hibernate.property column="EXECUTEDATE" length="31" not-null="false"
	 *                     update="true"
	 */
	public String getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}

	/**
	 * @hibernate.property column="EXECUTEDUTY" length="1" not-null="false"
	 *                     update="true"
	 */
	public String getExecuteDuty() {
		return executeDuty;
	}

	public void setExecuteDuty(String executeDuty) {
		this.executeDuty = executeDuty;
	}

	/**
	 * @hibernate.property column="EXECUTER" length="250" not-null="false"
	 *                     update="true"
	 */
	public String getExecuter() {
		return executer;
	}

	public void setExecuter(String executer) {
		this.executer = executer;
	}

	/**
	 * @hibernate.property column="EXECUTEROOM" length="100" not-null="false"
	 *                     update="true"
	 */
	public String getExecuteRoom() {
		return executeRoom;
	}

	public void setExecuteRoom(String executeRoom) {
		this.executeRoom = executeRoom;
	}

	/**
	 * @hibernate.property column="EXECUTETYPE" length="1" not-null="false"
	 *                     update="true"
	 */
	public String getExecuterType() {
		return executerType;
	}

	public void setExecuterType(String executerType) {
		this.executerType = executerType;
	}

	/**
	 * @hibernate.property column="FORMAT" length="50" not-null="false"
	 *                     update="true"
	 */
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @hibernate.property column="FORMID" length="50" not-null="false"
	 *                     update="true"
	 */
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * @hibernate.property column="XLSX" length="3" not-null="false"
	 *                     update="true"
	 */
	public String getXlsX() {
		return xlsX;
	}

	public void setXlsX(String xlsX) {
		this.xlsX = xlsX;
	}

	/**
	 * @hibernate.property column="XLSY" length="3" not-null="false"
	 *                     update="true"
	 */
	public String getXlsY() {
		return xlsY;
	}

	public void setXlsY(String xlsY) {
		this.xlsY = xlsY;
	}

	/**
	 * @hibernate.property column="EXECUTEFLAG" length="1" not-null="false"
	 *                     update="true"
	 */
	public String getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(String executeFlag) {
		this.executeFlag = executeFlag;
	}

	/**
	 * @hibernate.property column="VALIDATES" length="30" not-null="false"
	 *                     update="true"
	 */
	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	/**
	 * @hibernate.many-to-one column="MONTH_PLAN_ID" cascade="none"
	 *                        not-null="true"
	 */
	public TawwpMonthPlan getTawwpMonthPlan() {
		return tawwpMonthPlan;
	}

	public void setTawwpMonthPlan(TawwpMonthPlan tawwpMonthPlan) {
		this.tawwpMonthPlan = tawwpMonthPlan;
	}

	/**
	 * @hibernate.many-to-one column="MODEL_GROUP_ID" cascade="none"
	 *                        not-null="false"
	 */
	public TawwpModelGroup getTawwpModelGroup() {
		return tawwpModelGroup;
	}

	public void setTawwpModelGroup(TawwpModelGroup tawwpModelGroup) {
		this.tawwpModelGroup = tawwpModelGroup;
	}

	/**
	 * @hibernate.many-to-one column="YEAR_EXECUTE_ID" cascade="none"
	 *                        not-null="false"
	 */
	public TawwpYearExecute getTawwpYearExecute() {
		return tawwpYearExecute;
	}

	public void setTawwpYearExecute(TawwpYearExecute tawwpYearExecute) {
		this.tawwpYearExecute = tawwpYearExecute;
	}

	/**
	 * @hibernate.set inverse="true" lazy="true" cascade="save-update"
	 * @hibernate.collection-key column="MONTH_EXECUTE_ID"
	 * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpExecuteContent"
	 */
	public Set getTawwpExecuteContents() {
		return tawwpExecuteContents;
	}

	public void setTawwpExecuteContents(Set tawwpExecuteContents) {
		this.tawwpExecuteContents = tawwpExecuteContents;
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

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public String getQualityCheckUser() {
		return qualityCheckUser;
	}

	public String getExecuteDay() {
		return executeDay;
	}

	public void setQualityCheckUser(String qualityCheckUser) {
		this.qualityCheckUser = qualityCheckUser;
	}

	public void setExecuteDay(String executeDay) {
		this.executeDay = executeDay;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
