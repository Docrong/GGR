package com.boco.eoms.commons.system.org.mgr.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.org.mgr.IOrgMgr;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Oct 22, 2008 9:00:58 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class OrgMgrLocator {
	/**
	 * 用户授权mgr
	 * 
	 * @return 用户授权mgr
	 */
	public static IOrgMgr getOrgMgr() {
		return (IOrgMgr) ApplicationContextHolder.getInstance().getBean(
				"orgMgr");
	}
}
