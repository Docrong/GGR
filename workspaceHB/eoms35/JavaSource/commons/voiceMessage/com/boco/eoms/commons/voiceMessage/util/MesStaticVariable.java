package com.boco.eoms.commons.voiceMessage.util;

public interface MesStaticVariable {

	/**
	 * @see 字典类型 紧急程度 hieUrgeGrades （比如：无/次要/一般/重要/紧急/重大故障/来自领导投诉）
	 */
	public static final int URGE_GRADE_NULL = 0;

	public static final int URGE_GRADE_CY = 2;

	public static final int URGE_GRADE_YB = 1;

	public static final int URGE_GRADE_ZY = 3;

	public static final int URGE_GRADE_JJ = 4;
	
	public static final int URGE_GRADE_JDGZ = 5;
	
	public static final int URGE_GRADE_LZLDTS = 6;

	/**
	 * @see 字典类型 处理状态 dealStatus （比如：派发/接单/回复/归档 等）
	 */

	public static final int DEAL_STATUS_NULL = 0;// 无

	public static final int DEAL_STATUS_ACCEPT = 1;// 接单确认超时

	public static final int DEAL_STATUS_DEAL = 2;// 工单处理超时

	public static final int DEAL_STATUS_OVER = 3;// 工单结束超时

	public static final int DEAL_STATUS_TRANSMIT = 4;// 工单派发通知

	public static final int DEAL_STATUS_AUDIT = 5;// 工单审核通知

	public static final int DEAL_STATUS_MANUAL = 6;// 工单手工催办通知

	public static final int DEAL_STATUS_ADVICE = 7;// 工单阶段建议通知

	public static final int DEAL_STATUS_SMS_REPLAY = 8;// 工单短信回单通知

	public static final int DEAL_STATUS_AUTO_HIE = 9;// 工单自动催办通知

	/**
	 * @see 字典类型 服务类型 serviceType（比如：故障工单/局数据工单）
	 */

	public static final String FaultSheet = "100000"; // 故障工单

	public static final String BoardSheet = "100001"; // 坏板工单

	public static final String AllegeSheet = "100002"; // 申请工单

	public static final String ApplySheet = "100003"; // 申告工单

	public static final String DataSheet = "100004"; // 局数据工单

	public static final String LineSheet = "100005"; // 线路割接工单

	public static final String MoveSheet = "100006"; // 迁移工单

	public static final String OptimizSheet = "100007"; // 优化申请审批工单

	public static final String ReportSheet = "100008"; // 重大故障上报工单

	public static final String SoftwareSheet = "100009";// 软件版本升级工单

	public static final String TaskSheet = "100010"; // 任务工单

	public static final String WorkPlan = "150000"; // 作业计划

	public static final String DUTY = "110000"; // 值班信息

	public static final String REPORT = "140000"; // 值班信息

	public static final String ACCESSAPPLY = "140001"; // 设备访问信息

	public static final String FILEMANAGER = "160000"; // 数据上报

	/**
	 * @see 字典类型 接受者类型 receiverType
	 */

	public static final int REC_TPYE_DEPT = 1;// 部门

	public static final int REC_TPYE_ROOM = 4;// 机房

	public static final int REC_TPYE_ROLE = 0;// 角色

	public static final int REC_TPYE_USER = 3;// 个人

	public static final int REC_TPYE_ORG = 2;// 岗位

	/**
	  * @see 系统默认的空值
	  */
	  public static final String defaultnull = "-10";
	  public static final int defnull= -10;
}
