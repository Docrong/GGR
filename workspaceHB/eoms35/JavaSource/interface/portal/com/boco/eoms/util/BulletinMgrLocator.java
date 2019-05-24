package com.boco.eoms.util;

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
public class BulletinMgrLocator {
	/**
	 * 获取属性类
	 * 
	 * @return 获取属性类
	 */
	public static BulletinAttributes getAttributes() {
		return (BulletinAttributes) ApplicationContextHolder.getInstance().getBean(
				"bulletinAttributes");
	}
}
