package com.boco.eoms.commons.log.webapp.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 初始化spring xml
 * 
 * @author panlong
 * @Mar 23, 2007 3:02:15 AM
 */
public class TawCommonLogUtil {

	private static ApplicationContext ctx;

	static {
		ctx = new FileSystemXmlApplicationContext(
				"./src/commons/log/config/applicationContext-log.xml");
	}

	public static Object getApplication(Object obj, String strBean) {

		obj = (Object) ctx.getBean(strBean);
		return obj;
	}
}