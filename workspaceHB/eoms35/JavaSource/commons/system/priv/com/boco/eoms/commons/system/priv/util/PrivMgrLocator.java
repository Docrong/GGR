package com.boco.eoms.commons.system.priv.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.priv.service.IPrivMgr;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;

/**
 * <p>
 * Title:权限管理locator
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 8:56:57 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class PrivMgrLocator {

	/**
	 * 获取权限mgr
	 * 
	 * @return 权限相关操作mgr
	 */
	public static IPrivMgr getPrivMgr() {
		return (IPrivMgr) ApplicationContextHolder.getInstance().getBean(
				"PrivMgrImpl");
	}

	/**
	 * 用户授权mgr
	 * 
	 * @return 用户授权mgr
	 */
	public static ITawSystemPrivUserAssignManager getTawSystemPrivUserAssignManager() {
		return (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
	}

	/**
	 * 组织授权mgr
	 * 
	 * @return 组织授权mgr
	 */
	public static ITawSystemPrivAssignManager geTawSystemPrivAssignManager() {
		return (ITawSystemPrivAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivAssignManager");
	}
}
