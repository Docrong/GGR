package com.boco.eoms.commons.system.dict.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.IDictService;

/**
 * <p>
 * Title:字典的locator
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 1, 2008 12:41:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class DictMgrLocator {
	/**
	 * 获取字典service
	 * 
	 * @return 字典service
	 */
	public static IDictService getDictService() {
		return (IDictService) ApplicationContextHolder.getInstance().getBean(
				"DictService");
	}

	/**
	 * 获取将id转换为名称的service
	 * 
	 * @return 将id转换为名称的service
	 */
	public static ID2NameService getId2NameService() {
		return (ID2NameService) ApplicationContextHolder.getInstance().getBean(
				"ID2NameGetServiceCatch");
	}
}
