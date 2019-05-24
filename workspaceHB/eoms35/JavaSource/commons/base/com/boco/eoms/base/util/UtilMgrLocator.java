package com.boco.eoms.base.util;

import com.boco.eoms.base.util.ApplicationContextHolder;

/**
 * <p>
 * Title:util的locator
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 8:41:18 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UtilMgrLocator {

	/**
	 * 获取全局配置参数
	 * 
	 * @return 全局配置参数mgr
	 */
	public  static EOMSAttributes getEOMSAttributes() {
		return (EOMSAttributes) ApplicationContextHolder.getInstance().getBean(
				"eomsAttributes");
	}
}
