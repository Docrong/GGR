package com.boco.eoms.commons.statistic.base.util;

import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;



/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Aug 3, 2008 1:01:58 PM
 * </p>
 * 
 * @author wangbeiying
 * @version 1.0
 * 
 */
public class StatAttributeUrlLocator {
	public static StatAttributeUrl getStatAttributeUrl(String beanid) {
		return (StatAttributeUrl) ApplicationContextHolder.getInstance().getBean(
		beanid);
	}
	
	public static String getStatAttributeUrlString(String beanid,String key) {
		return ((StatAttributeUrl) (ApplicationContextHolder.getInstance().getBean(
				beanid))).getAttributeUrl().getProperty(key);
	}
}
