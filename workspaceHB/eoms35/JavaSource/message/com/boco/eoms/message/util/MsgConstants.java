package com.boco.eoms.message.util;

public class MsgConstants {
	
	/**
	 * For the users who have more than one mobile number..
	 */
	public final static String MOBILE_SEPARATOR = ",";
	
	public final static String SEPARATOR = "#";
	
//	 SmsModule-START
	/**
	 * The request scope attribute that holds the smsModule form.
	 */
	public static final String SMSMODULE_KEY = "smsModuleForm";

	/**
	 * The request scope attribute that holds the smsModule list
	 */
	public static final String SMSMODULE_LIST = "smsModuleList";

	// SmsModule-END

	// SmsService-START
	/**
	 * The request scope attribute that holds the smsService form.
	 */
	public static final String SMSSERVICE_KEY = "smsServiceForm";

	/**
	 * The request scope attribute that holds the smsService list
	 */
	public static final String SMSSERVICE_LIST = "smsServiceList";

	// SmsService-END

	// SmsApply-START
	/**
	 * The request scope attribute that holds the smsApply form.
	 */
	public static final String SMSAPPLY_KEY = "smsApplyForm";

	/**
	 * The request scope attribute that holds the smsApply list
	 */
	public static final String SMSAPPLY_LIST = "smsApplyList";
	//消息接收类型-个人
	public static final String RECEIVER_USER = "0"; 
	//消息接收类型-部门
	public static final String RECEIVER_DEPT = "1"; 
	//当前时间发送
	public static final String STATUS_CURRENT = "1";
	//提前发送
	public static final String STATUS_FORWARD = "2";
	//滞后发送
	public static final String STATUS_LAG = "3";
	//正选
	public static final String POSITIVE = "true";
	//反选
	public static final String UNPOSITIVE = "false";
	//服务标志
	public static final String SERVICE = "2";
	//模块标志
	public static final String MODULE = "1";
	//删除标志之未删除
	public static final  String UNDELETED = "0";
	//删除标志之已删除
	public static final  String DELETED = "1";
	//删除标志之已个性化
	public static final  String DIYED = "2";
	//删除标志之正选
	public static final  String POS = "3";
//	删除标志之正选
	public static final  String ALL = "5";
	//成功标志之成功
	public static final  String SUCCESS = "1";
	//成功标志之失败
	public static final  String UNSUCCESS = "2";
	
	//JSON数据之部门
	public static final  String DEPT = "dept";
	//JSON数据之用户
	public static final  String USER = "user";
	//发送参数-人员
	public static final  String USER_PARAM = "1";
	//发送参数-部门
	public static final  String DEPT_PARAM = "2";
	//发送参数-角色
	public static final  String ROLE_PARAM = "3";
	
	//消息类型-短信
	public static final  String MSGTYPE_SMS = "1";
	//消息类型-邮件
	public static final  String MSGTYPE_EMAIL = "2";
	//消息类型-语音
	public static final  String MSGTYPE_VOICE = "3";
	//消息类型-彩信
	public static final  String MSGTYPE_MMS = "4";
	
	public static final String MMS_TYPE_TEXT = "1";
	
	public static final String MMS_TYPE_GIF = "2";
	
	public static final String MMS_TYPE_JPEG = "3";
	//发送成功不记录日志
	public static final String LOG_OFF = "0";
	//发送成功记录日志
	public static final String LOG_ON = "1";
	
	// SmsApply-END

	// SmsMonitor-START
	/**
	 * The request scope attribute that holds the smsMonitor form.
	 */
	public static final String SMSMONITOR_KEY = "smsMonitorForm";

	/**
	 * The request scope attribute that holds the smsMonitor list
	 */
	public static final String SMSMONITOR_LIST = "smsMonitorList";

	// SmsMonitor-END

	// SmsLog-START
	/**
	 * The request scope attribute that holds the smsLog form.
	 */
	public static final String SMSLOG_KEY = "smsLogForm";

	/**
	 * The request scope attribute that holds the smsLog list
	 */
	public static final String SMSLOG_LIST = "smsLogList";

	// SmsLog-END
}
