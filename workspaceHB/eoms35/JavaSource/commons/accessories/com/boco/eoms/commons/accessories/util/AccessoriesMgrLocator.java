package com.boco.eoms.commons.accessories.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Sep 11, 2008 2:27:14 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class AccessoriesMgrLocator {
	/**
	 * 返回符件配置文件管理
	 * 
	 * @return 符件配置文件管理
	 */
	public static AccessoriesAttributes getAccessoriesAttributes() {
		return (AccessoriesAttributes) ApplicationContextHolder.getInstance()
				.getBean("accessoriesAttributes");
	}

	/**
	 * 返回符件管理
	 * 
	 * @return 符件管理
	 */
	public static ITawCommonsAccessoriesManager getTawCommonsAccessoriesManagerCOS() {
		return (ITawCommonsAccessoriesManager) ApplicationContextHolder
				.getInstance().getBean("ItawCommonsAccessoriesManager");
	}
	 
}
