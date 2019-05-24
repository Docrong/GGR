package com.boco.eoms.workplan.vo;

import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title: 月度作业计划执行内容数据显示类
 * </p>
 * <p>
 * Description: 提供页面的所需的数据封装
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: boco
 * </p>
 * 
 * @author eoms
 * @version 1.0
 */

public class TawwpMonthExecuteVO implements Comparable {

	private String id; // 标识

	private String name; // 年度作业计划执行内容名称

	private String cycle; // 周期编号

	private String cycleName; // 周期名称

	private String remark; // 执行帮助
	
	private String note;//备注

	private String format; // 填写格式

	private String validate; // 验证格式

	private String formId; // 执行表格编号

	private String formName; // 执行表格名称

	private String deleted; // 删除标志

	private String cruser; // 创建人用户名

	private String userName; // 创建人姓名

	private String crtime; // 创建时间

	private String serialNo; // 序列号

	private String xlsX; // excel文件位置(横坐标)

	private String xlsY; // excel文件位置(纵坐标)

	private String netTypeId; // 网元类型编号

	private String netTypeName; // 网元类型名称

	private String command; // 命令标识(智能巡检接口)

	private String commandName; // 命令名称(智能巡检接口)

	private String executer; // 执行人用户名(或编号)信息字符串

	private String executerName; // 执行人姓名(或名称,如部门、岗位)

	private String executerType; // 执行人类型编号(0：表示人员,1：表示部门,2：表示组 3：表示班次)

	private String executerTypeName; // 执行人类型名称

	private String executeRoom; // 执行机房编号(执行人为“班次”)

	private String executeRoomName; // 执行机房名称

	private String executeDuty; // 执行班次编号(执行人为“班次”)

	private String executeDutyName; // 执行班次名称

	private String executeDate; // 执行时间(执行列表,通过时间序列形式表示如：“0001000000100000010000001000111”,1标识当日执行)

	private String dayCount; // 当月天数

	private String isHoliday; // 假日是否排班

	private String isWeekend; // 周末是否排班

	private String botype; // 业务类型

	private String executedeptlevel; // 执行单位级别

	private String appdesc; // 适用说明

	private String startHH; // 每天计划开始时间(细化到小时)

	private String endHH; // 每天计划结束时间(细化到小时)

	private String fileFlag; // 是否上传附件

	private String fileFlagName;

	private String accessories; //指导文件
	 
	private String qualityCheckUser; // 质检人
	
	private String qualityCheckUserName; // 质检人

	private String qualityCheckDevice; // 质检意见

	private TawwpMonthPlan tawwpMonthPlan;

	// 执行班次名称(每天9点、14点、19点班次)
	static public String[] DUTYTIME = { "00:00", "09:00", "14:00", "19:00" };

	// 执行人类型名称数组
	static public String[] EXECUTERTYPE = { "人员", "部门", "岗位", "值班人" };

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

	public TawwpMonthPlan getTawwpMonthPlan() {
		return tawwpMonthPlan;
	}

	public void setTawwpMonthPlan(TawwpMonthPlan tawwpMonthPlan) {
		this.tawwpMonthPlan = tawwpMonthPlan;
	}

	/**
	 * 获取执行班次名称
	 * 
	 * @param _executeDuty
	 *            执行班次编号
	 * @return 执行班次名称
	 */
	public static String getExecuteDutyName(int _executeDuty) {
		// 初始化数据
		String executeDutyName = "";
		// 如果执行班次编号在执行班次范围以内
		if (_executeDuty < TawwpMonthExecuteVO.DUTYTIME.length
				&& _executeDuty >= 0) {
			// 取出执行班次显示名称
			executeDutyName = TawwpMonthExecuteVO.DUTYTIME[_executeDuty];
		}
		// 返回执行班次名称
		return executeDutyName;
	}

	/**
	 * 获取执行人类型名称
	 * 
	 * @param _executerType
	 *            执行人类型编号
	 * @return 执行人类型名称
	 */
	public static String getExecuterTypeName(int _executerType) {
		// 初始化数据
		String executeTypeName = "";
		// 如果执行人类型编号在执行人类型范围以内
		if (_executerType < TawwpMonthExecuteVO.EXECUTERTYPE.length
				&& _executerType >= 0) {
			// 取出执行人类型显示名称
			executeTypeName = TawwpMonthExecuteVO.EXECUTERTYPE[_executerType];
		}
		// 返回执行人类型名称
		return executeTypeName;
	}

	// 以下为各属性set、get方法
	public String getCommand() {
		if (command == null) {
			command = "";
		}

		return command;
	}

	public String getCrtime() {
		if (crtime == null) {
			crtime = "";
		}

		return crtime;
	}

	public String getCycle() {
		if (cycle == null) {
			cycle = "";
		}

		return cycle;
	}

	public String getCruser() {
		if (cruser == null) {
			cruser = "";
		}

		return cruser;
	}

	public String getCycleName() {
		if (cycleName == null) {
			cycleName = "";
		}

		return cycleName;
	}

	public String getDeleted() {
		if (deleted == null) {
			deleted = "";
		}

		return deleted;
	}

	public String getExecuteDate() {
		if (executeDate == null) {
			executeDate = "";
		}

		return executeDate;
	}

	public String getExecuteDutyName() {
		if (executeDutyName == null) {
			executeDutyName = "";
		}

		return executeDutyName;
	}

	public String getExecuteDuty() {
		if (executeDuty == null) {
			executeDuty = "";
		}

		return executeDuty;
	}

	public String getExecuter() {
		if (executer == null) {
			executer = "";
		}

		return executer;
	}

	public String getExecuterName() {
		if (executerName == null) {
			executerName = "";
		}

		return executerName;
	}

	public String getExecuteRoom() {
		if (executeRoom == null) {
			executeRoom = "";
		}

		return executeRoom;
	}

	public String getExecuteRoomName() {
		if (executeRoomName == null) {
			executeRoomName = "";
		}

		return executeRoomName;
	}

	public String getExecuterType() {
		if (executerType == null) {
			executerType = "";
		}

		return executerType;
	}

	public String getExecuterTypeName() {
		if (executerTypeName == null) {
			executerTypeName = "";
		}

		return executerTypeName;
	}

	public String getFormat() {
		if (format == null) {
			format = "";
		}

		return format;
	}

	public String getFormId() {
		if (formId == null) {
			formId = "";
		}

		return formId;
	}

	public String getFormName() {
		if (formName == null) {
			formName = "";
		}

		return formName;
	}

	public String getId() {
		if (id == null) {
			id = "";
		}

		return id;
	}

	public String getName() {
		if (name == null) {
			name = "";
		}

		return name;
	}

	public String getNetTypeId() {
		if (netTypeId == null) {
			netTypeId = "";
		}

		return netTypeId;
	}

	public String getNetTypeName() {
		if (netTypeName == null) {
			netTypeName = "";
		}

		return netTypeName;
	}

	public String getRemark() {
		if (remark == null) {
			remark = "";
		}

		return remark;
	}

	public String getSerialNo() {
		if (serialNo == null) {
			serialNo = "";
		}

		return serialNo;
	}

	public String getUserName() {
		if (userName == null) {
			userName = "";
		}

		return userName;
	}

	public String getValidate() {
		if (validate == null) {
			validate = "";
		}

		return validate;
	}

	public String getXlsX() {
		if (xlsX == null) {
			xlsX = "";
		}

		return xlsX;
	}

	public String getXlsY() {
		if (xlsY == null) {
			xlsY = "";
		}

		return xlsY;
	}

	public void setXlsY(String xlsY) {
		this.xlsY = xlsY;
	}

	public void setXlsX(String xlsX) {
		this.xlsX = xlsX;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setNetTypeName(String netTypeName) {
		this.netTypeName = netTypeName;
	}

	public void setNetTypeId(String netTypeId) {
		this.netTypeId = netTypeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setExecuterTypeName(String executerTypeName) {
		this.executerTypeName = executerTypeName;
	}

	public void setExecuterType(String executerType) {
		this.executerType = executerType;
	}

	public void setExecuteRoomName(String executeRoomName) {
		this.executeRoomName = executeRoomName;
	}

	public void setExecuteRoom(String executeRoom) {
		this.executeRoom = executeRoom;
	}

	public void setExecuterName(String executerName) {
		this.executerName = executerName;
	}

	public void setExecuter(String executer) {
		this.executer = executer;
	}

	public void setExecuteDutyName(String executeDutyName) {
		this.executeDutyName = executeDutyName;
	}

	public void setExecuteDuty(String executeDuty) {
		this.executeDuty = executeDuty;
	}

	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDayCount() {
		return dayCount;
	}

	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}

	// 进行排序处理，按周期进行排序
	public int compareTo(Object obj) {
		TawwpMonthExecuteVO tawwpMonthExecuteVO = (TawwpMonthExecuteVO) obj;
		String cycyle1 = null;
		String cycyle2 = null;

		int iRet = 0;

		try {
			cycyle1 = this.getCycle();
			cycyle2 = tawwpMonthExecuteVO.getCycle();

			iRet = cycyle1.compareTo(cycyle2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return iRet;
	}

	public String getCommandName() {
		if (commandName == null) {
			commandName = "";
		}

		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getBotype() {
		if (botype == null) {
			botype = "";
		}
		return botype;
	}

	public void setBotype(String botype) {
		this.botype = botype;
	}

	public String getExecutedeptlevel() {
		if (executedeptlevel == null) {
			executedeptlevel = "";
		}
		return executedeptlevel;
	}

	public void setExecutedeptlevel(String executedeptlevel) {
		this.executedeptlevel = executedeptlevel;
	}

	public String getAppdesc() {
		if (appdesc == null) {
			appdesc = "";
		}
		return appdesc;
	}

	public void setAppdesc(String appdesc) {
		this.appdesc = appdesc;
	}

	public String getStartHH() {
		if (startHH == null) {
			startHH = "";
		}
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		if (endHH == null) {
			endHH = "";
		}
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public String getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}

	public String getFileFlagName() {
		fileFlagName = "";
		if (fileFlag != null) {
			if (fileFlag.equals("1")) {
				fileFlagName = "是";
			} else if (fileFlag.equals("0")) {
				fileFlagName = "否";
			}
		}

		return fileFlagName;
	}

	public String getQualityCheckUser() {
		return qualityCheckUser;
	}

	public void setQualityCheckUser(String qualityCheckUser) {
		this.qualityCheckUser = qualityCheckUser;
	}

	public String getQualityCheckUserName() {
		return qualityCheckUserName;
	}

	public void setQualityCheckUserName(String qualityCheckUserName) {
		this.qualityCheckUserName = qualityCheckUserName;
	}

	public String getQualityCheckDevice() {
		return qualityCheckDevice;
	}

	public void setQualityCheckDevice(String qualityCheckDevice) {
		this.qualityCheckDevice = qualityCheckDevice;
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