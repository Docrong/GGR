package com.boco.eoms.commons.log.webapp.action;

/**
 * 日志信息的处理和分发
 * 
 * @author panlong
 * @Mar 23, 2007 2:29:20 AM
 */
public class TawCommonLogAction {
	public TawCommonLogAction() {

	}

	/**
	 * 保存信息到xml文件
	 * @param obj
	 * @param userid
	 * @param remoteip
	 * @param loglevel
	 * @param message
	 * @param operlevel
	 * @param filepath
	 * @return
	 */
	public String tofileinfo(Object obj, String userid, String remoteip,
			String loglevel, String message, String operlevel, String filepath) {

		return obj.getClass().getName() + ";" + userid + ";" + remoteip + ";"
				+ loglevel + ";" + message + ";" + operlevel + ";" + filepath;

	}

	/**
	 * 保存信息到DB
	 * @param obj
	 * @param userid
	 * @param remoteip
	 * @param loglevel
	 * @param message
	 * @param operlevel
	 * @return
	 */
	public String todbinfo(Object obj, String userid, String remoteip,
			String loglevel, String message, String operlevel) {

		return obj.getClass().getName() + ";" + userid + ";" + remoteip + ";"
				+ loglevel + ";" + message + ";" + operlevel;
	}
}
