package com.boco.eoms.commons.log.webapp.bo.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;

import com.boco.eoms.commons.log.model.TawCommonLogDeploy;
import com.boco.eoms.commons.log.service.TawCommonLogDeployManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.log.webapp.action.TawCommonLogAction;

/**
 * 实现日志的添加
 * 
 * @author panlong
 * @Mar 23, 2007 2:11:09 AM
 */
public class TawCommonLog {

	/**
	 * 根据不同的级别进行日志的相应处理
	 * 
	 */
	public static void saveLog(Object obj, String userid, String remoteip, String operlevel, String message) {
        String loglevel="info";
        try{
		TawCommonLogDeploy logbocodeploy = null;
		TawCommonLogDeployManager logdeploy = (TawCommonLogDeployManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogDeployManager");

		logbocodeploy = logdeploy.getDeployByOperID(operlevel);
		addtofileOrDB(obj, userid, remoteip, loglevel, message, operlevel,
				logbocodeploy);
        }catch(Exception ex){
        	BocoLog.error(TawCommonLog.class, "没有配置业务ID,不能为您提供日志服务!");
        }

	}

	private static void savetoDB(Object obj, String userid, String remoteid,
			String loglevel, String message, String operlevel) {

		TawCommonLogAction bocobeans = (TawCommonLogAction) ApplicationContextHolder
				.getInstance().getBean("bocobeanaction");

		bocobeans.todbinfo(obj, userid, remoteid, loglevel, message, operlevel);
	}

	private static void savetoFile(Object obj, String userid, String remoteid,
			String loglevel, String message, String operlevel,
			String keepfilepath) {
		TawCommonLogAction bocobeans = (TawCommonLogAction) ApplicationContextHolder
				.getInstance().getBean("bocobeanaction");
		bocobeans.tofileinfo(obj, userid, remoteid, loglevel, message,
				operlevel, keepfilepath);
	}

	private static void addtofileOrDB(Object obj, String userid,
			String remoteid, String loglevel, String message, String operlevel,
			TawCommonLogDeploy logbocodeploy) {

		String keepfilepath = logbocodeploy.getFilesavepath();

		try {
			if (keepfilepath == null || keepfilepath.equals("")) {
				savetoDB(obj, userid, remoteid, loglevel, message, operlevel);
			} else {
				savetoFile(obj, userid, remoteid, loglevel, message, operlevel,
						keepfilepath);
			}
		} catch (Exception ex) {
			BocoLog.error(TawCommonLog.class, "No CONFIG YOUR LOG DEPLOY!");
		}
	}

}
