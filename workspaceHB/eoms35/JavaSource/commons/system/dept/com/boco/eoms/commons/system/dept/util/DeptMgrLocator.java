package com.boco.eoms.commons.system.dept.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;

/**
 * <p>
 * Title:部门mgr locator
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 29, 2008 10:38:11 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class DeptMgrLocator {
	public static ITawSystemDeptManager getTawSystemDeptManager() {
		return (ITawSystemDeptManager) ApplicationContextHolder.getInstance()
				.getBean("ItawSystemDeptManager");
	}
}
