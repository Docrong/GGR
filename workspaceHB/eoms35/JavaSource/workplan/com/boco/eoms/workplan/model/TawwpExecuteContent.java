package com.boco.eoms.workplan.model;

/**
 * <p>Title: 执行作业计划执行内容�?/p>
 * <p>Description: 执行作业计划执行内容类信息，其中包括执行时间，执行内容，备注，附件，特殊表格�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import java.util.*;

import com.boco.eoms.common.oo.*;

/**
 * @hibernate.class table="TAW_WP_EXECUTECONTENT"
 */

public class TawwpExecuteContent implements Serializable, DataObject {

	private String id; // 标识

	private String name; // 执行内容名称

	private String botype; // 业务类型

	private String executedeptlevel; // 执行单位级别

	private String appdesc; // 适用说明

	private String startDate; // 计划开始时�?

	private String endDate; // 计划结束时间

	private String crtime; // 创建时间 （按创建时间排序�?

	private String cruser; // 执行�?

	private String content; // 执行内容

	private String remark; // 备注

	private String cycle; // 周期

	private String deptId; // 部门标识

	private String executeDept; // 执行部门

	private String formId; // 附加表格格式信息

	private String formDataId; // 附加表格信息（附加表格字符串，用","分割�?

	private String workSheetId; // 工单编号（接口记录）

	private String eligibility; // 合格标志 0：合�?1：不合格

	private String executeFlag; // 填写标志 0：未填写 1：按时填�?2：超时填�?3

	private String checkContent; // 领导审批意见（备用属性）

	private String command; // 命令标识(智能巡检接口)

	private String commandName; // 命令名称(智能巡检接口)

	private String executer; // 执行人信�?
	private String executerType; // 执行人类型（0：表示人员，1：表示部门，2：表示组 3：表示班次）

	private String executeRoom; // 执行机房（执行人为“班次”）

	private String executeDuty; // 执行班次（执行人为“班次”）

	private String monthPlanExecuteFlag; // 月度作业计划的执行标识（0：多人填写一份，1：多人填写多份）

	private String qualityCheckUser; // 质检人

	private String qualityCheckFlag; // 是否已质检 参数-""：不进行质检 "0"：未质检 "1"：质检通过 "2"：质检不通过

	private String qualityCheckDevice; // 质检意见

	private TawwpMonthExecute tawwpMonthExecute; // 月度作业计划执行内容对象

	private TawwpMonthPlan tawwpMonthPlan; // 月度作业计划对象

	private Set tawwpExecuteContentUsers = new HashSet(); // 对应的作业计划执行内容（个人�?

	private Set tawwpExecuteFiles = new HashSet(); // 对应的执行内容附�?

	private String fileFlag; // 是否需要上传附件
	
	private String extendremark;// 执行帮助
	
	private String accessories; // 指导文件
	
	private String normalFlag; // h合格标志
	
	private String reason; //补填原因

	public String getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}

	public TawwpExecuteContent() {
	}

	public TawwpExecuteContent(String _name, String _botype,
			String _executedeptlevel, String _appdesc, String _startDate,
			String _endDate, String _crtime, String _cruser, String _content,
			String _remark, String _cycle, String _formId, String _formDataId,
			String _deptId, String _workSheetId, String _eligibility,
			String _executeFlag, String _checkContent, String _command,
			String _commandName, String _executer, String _executerType,
			String _executeRoom, String _executeDuty,
			String _monthPlanExecuteFlag, String _fileFlag,
			String _qualityCheckUser, TawwpMonthExecute _tawwpMonthExecute,
			TawwpMonthPlan _tawwpMonthPlan,String _reason) {
		this.name = _name;
		this.botype = _botype;
		this.executedeptlevel = _executedeptlevel;
		this.appdesc = _appdesc;
		this.startDate = _startDate;
		this.endDate = _endDate;
		this.crtime = _crtime;
		this.cruser = _cruser;
		this.content = _content;
		this.remark = _remark;
		this.formId = _formId;
		this.cycle = _cycle;
		this.deptId = _deptId;
		this.formDataId = _formDataId;
		this.workSheetId = _workSheetId;
		this.eligibility = _eligibility;
		this.executeFlag = _executeFlag;
		this.checkContent = _checkContent;
		this.command = _command;
		this.commandName = _commandName;
		this.tawwpMonthPlan = _tawwpMonthPlan;
		this.tawwpMonthExecute = _tawwpMonthExecute;
		this.executer = _executer;
		this.executerType = _executerType;
		this.executeRoom = _executeRoom;
		this.executeDuty = _executeDuty;
		this.monthPlanExecuteFlag = _monthPlanExecuteFlag;
		this.fileFlag = _fileFlag;
		this.qualityCheckUser = _qualityCheckUser;
		this.reason=_reason;
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
	 * @hibernate.property column="CONTENT" length="200" not-null="false"
	 *                     update="true"
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	 * @hibernate.property column="STARTDATE" length="19" not-null="true"
	 *                     update="true"
	 */
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @hibernate.property column="ENDDATE" length="19" not-null="true"
	 *                     update="true"
	 */
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @hibernate.property column="CRTIME" length="19" not-null="false"
	 *                     update="true"
	 */
	public String getCrtime() {
		return crtime;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}

	/**
	 * @hibernate.property column="CRUSER" length="250" not-null="false"
	 *                     update="true"
	 */
	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	/**
	 * @hibernate.property column="EXECUTEFLAG" length=1" not-null="true"
	 *                     update="true"
	 */
	public String getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(String executeFlag) {
		this.executeFlag = executeFlag;
	}

	/**
	 * @hibernate.property column="FORMID" length=20" not-null="false"
	 *                     update="true"
	 */
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * @hibernate.property column="FORMDATAID" length=200" not-null="false"
	 *                     update="true"
	 */
	public String getFormDataId() {
		return formDataId;
	}

	public void setFormDataId(String formDataId) {
		this.formDataId = formDataId;
	}

	/**
	 * @hibernate.property column="ELIGIBILITY" length=1" not-null="true"
	 *                     update="true"
	 */
	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	/**
	 * @hibernate.property column="CHECKCONTENT" length=1" not-null="false"
	 *                     update="true"
	 */
	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
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
	 * @hibernate.property column="DEPTID" length="10" not-null="true"
	 *                     update="false"
	 */
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @hibernate.property column="WORKSHEETID" length=50" not-null="false"
	 *                     update="true"
	 */
	public String getWorkSheetId() {
		return workSheetId;
	}

	public void setWorkSheetId(String workSheetId) {
		this.workSheetId = workSheetId;
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
	 * @hibernate.many-to-one column="MONTH_EXECUTE_ID" cascade="none"
	 *                        not-null="true"
	 */
	public TawwpMonthExecute getTawwpMonthExecute() {
		return tawwpMonthExecute;
	}

	public void setTawwpMonthExecute(TawwpMonthExecute tawwpMonthExecute) {
		this.tawwpMonthExecute = tawwpMonthExecute;
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
	 * @hibernate.set inverse="true" lazy="true" cascade="save-update"
	 * @hibernate.collection-key column="EXECUTE_CONTENT_ID"
	 * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpExecuteContentUser"
	 */
	public Set getTawwpExecuteContentUsers() {
		return tawwpExecuteContentUsers;
	}

	public void setTawwpExecuteContentUsers(Set tawwpExecuteContentUsers) {
		this.tawwpExecuteContentUsers = tawwpExecuteContentUsers;
	}

	/**
	 * @hibernate.set inverse="true" lazy="true" cascade="save-update"
	 * @hibernate.collection-key column="EXECUTE_CONTENT_ID"
	 * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpExecuteFile"
	 */
	public Set getTawwpExecuteFiles() {
		return tawwpExecuteFiles;
	}

	public void setTawwpExecuteFiles(Set tawwpExecuteFiles) {
		this.tawwpExecuteFiles = tawwpExecuteFiles;
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
	 * @hibernate.property column="MONTHPLANEXECUTEFLAG" length="1"
	 *                     not-null="false" update="true"
	 */
	public String getMonthPlanExecuteFlag() {
		return monthPlanExecuteFlag;
	}

	public void setMonthPlanExecuteFlag(String monthPlanExecuteFlag) {
		this.monthPlanExecuteFlag = monthPlanExecuteFlag;
	}

	public String getExecuteDept() {
		return executeDept;
	}

	public void setExecuteDept(String executeDept) {
		this.executeDept = executeDept;
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

	public String getNormalFlag() {
		return normalFlag;
	}

	public void setNormalFlag(String normalFlag) {
		this.normalFlag = normalFlag;
	}

	public String getQualityCheckFlag() {
		return qualityCheckFlag;
	}

	public void setQualityCheckFlag(String qualityCheckFlag) {
		this.qualityCheckFlag = qualityCheckFlag;
	}

	public String getQualityCheckUser() {
		return qualityCheckUser;
	}

	public void setQualityCheckUser(String qualityCheckUser) {
		this.qualityCheckUser = qualityCheckUser;
	}

	public String getQualityCheckDevice() {
		return qualityCheckDevice;
	}

	public void setQualityCheckDevice(String qualityCheckDevice) {
		this.qualityCheckDevice = qualityCheckDevice;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getExtendremark() {
		return extendremark;
	}

	public void setExtendremark(String extendremark) {
		this.extendremark = extendremark;
	}

}
