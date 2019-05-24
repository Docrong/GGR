package com.boco.eoms.commons.log.webapp.action;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.log.model.TawCommonLogDeploy;
import com.boco.eoms.commons.log.service.ITawCommonLogBzBO;
import com.boco.eoms.commons.log.service.ITawCommonLogFileBo;
import com.boco.eoms.commons.log.service.TawCommonLogDeployManager;

import com.boco.eoms.commons.log.service.impl.TawCommonLogFileBoImpl;

/**
 * 进行业务的流转控制
 * 
 * @author panlong
 * @Mar 23, 2007 2:53:24 AM
 */
public class TawCommonLogAdviceAction implements AfterReturningAdvice {

	/**
	 * 保存到数据库或者FILE
	 */
	public void afterReturning(Object returnvalue, Method method,
			Object[] arg2, Object arg3) throws Throwable {

		if (method.getName().startsWith("todb")) {

			String dbstr[] = null;

			dbstr = returnvalue.toString().split(";");
			String classname = dbstr[0].trim();
			String userid = dbstr[1].trim();

			String remoteip = dbstr[2].trim();
			String loglevel = dbstr[3].trim();
			String message = dbstr[4].trim();
			String operlevel = dbstr[5].trim();

			ITawCommonLogBzBO bocolog = (ITawCommonLogBzBO) ApplicationContextHolder
					.getInstance().getBean("commonlogBoimpl");
			bocolog.saveLog(classname, userid, operlevel, remoteip, loglevel,
					message);

		} else if (method.getName().startsWith("tofile")) {

			String filestr[] = null;
			filestr = returnvalue.toString().split(";");
			String classname = filestr[0].trim();
			String userid = filestr[1].trim();
			String remoteip = filestr[2].trim();
			String loglevel = filestr[3].trim();
			String message = filestr[4].trim();
			String operlevel = filestr[5].trim();

			ITawCommonLogFileBo filecontrol = null;

			TawCommonLogDeploy logbocodeploy = getCommonlog(operlevel);
			String filecutlenth = String
					.valueOf(logbocodeploy.getFilecutsize());

			String modelid = logbocodeploy.getModelid();
			String modelname = logbocodeploy.getModelname();
			String dbfilepath = logbocodeploy.getFilesavepath();
			String opername = logbocodeploy.getOpername();
			String opernameremark = logbocodeploy.getOperdesc();

			filecontrol = new TawCommonLogFileBoImpl();
			filecontrol.saveLogtoFile(classname, userid, modelid, modelname,
					opername, operlevel, message, remoteip, dbfilepath,
					loglevel, opernameremark, filecutlenth);

		}
	}

	public TawCommonLogDeploy getCommonlog(String fileoperlevel) {
		TawCommonLogDeploy logbocodeploy = new TawCommonLogDeploy();
		TawCommonLogDeployManager logdeploy = (TawCommonLogDeployManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogDeployManager");

		logbocodeploy = logdeploy.getDeployByOperID(fileoperlevel);

		return logbocodeploy;
	}

}
