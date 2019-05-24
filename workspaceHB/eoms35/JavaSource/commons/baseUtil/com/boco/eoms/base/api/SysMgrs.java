package com.boco.eoms.base.api;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.area.service.IAreaMgr;
import com.boco.eoms.commons.system.dict.service.DictMgrs;
import com.boco.eoms.commons.system.priv.service.IPrivMgr;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;

/**
 * <p>
 * Title:系统API
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 28, 2008 9:00:02 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */

public class SysMgrs {
	/**
	 * 字典api
	 * 
	 * @since 0.1
	 */
	private static final DictMgrs dictMgrs = new DictMgrs();

	/**
	 * 区域mgr
	 * 
	 * @return 区域mgr
	 * @since 0.1
	 */
	public static IAreaMgr getAreaMgr() {
		return (IAreaMgr) ApplicationContextHolder.getInstance().getBean(
				"AreaMgrImpl");
	}

	/**
	 * 权限api
	 * 
	 * @return 权限api
	 * @since 0.1
	 */
	public static IPrivMgr getPrivMgr() {
		return PrivMgrLocator.getPrivMgr();
	}

	/**
	 * xml字典api
	 * 
	 * @return XML字典api
	 * @since 0.1
	 */
	public static DictMgrs getDictMgrs() {
		return dictMgrs;
	}

}
