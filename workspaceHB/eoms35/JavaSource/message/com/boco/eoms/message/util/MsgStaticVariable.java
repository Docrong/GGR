package com.boco.eoms.message.util;

public class MsgStaticVariable {
	/**删除标志之未删除*/
	public static final  String UNDELETED = "0";
	/**删除标志之已删除*/
	public static final  String DELETED = "1";
	/**删除标志之已个性化*/
	public static final  String DIYED = "2";
	/**成功标志之成功*/
	public static final  String SUCCESS = "1";
	/**成功标志之失败*/
	public static final  String UNSUCCESS = "2";
	/**当前时间发送*/
	public static final String STATUS_CURRENT = "1";
	/**提前发送*/
	public static final String STATUS_FORWARD = "2";
	/**滞后发送*/
	public static final String STATUS_LAG = "3";
	/**ORACLE数据库*/
	public static final String DB_ORACLE = "1";
	/**INFORMIX数据库*/
	public static final String DB_INFORMIX = "2";
	/**支持长短信*/
	public static final String MSG_LONG = "T";
	/**不支持长短信*/
	public static final String MSG_SHORT = "F";
}
