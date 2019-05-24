package com.boco.eoms.workbench.networkcalendar.webapp.util;

import com.boco.eoms.base.util.ApplicationContextHolder;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 15, 2008 1:44:40 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class networkcalendarMgrLocator {
	/**
	 * 获取属性类
	 * 
	 * @return 获取属性类
	 */
	public static networkcalendarAttributes getAttributes() {
		return (networkcalendarAttributes) ApplicationContextHolder.getInstance().getBean(
				"dutyAttributes");
	}
}
