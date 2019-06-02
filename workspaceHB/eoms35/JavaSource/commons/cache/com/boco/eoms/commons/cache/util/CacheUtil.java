package com.boco.eoms.commons.cache.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 2, 2007 9:01:27 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class CacheUtil {
	/**
	 * 
	 * 
	 * creates cache key: targetName.methodName.argument0.argument1...
	 * 
	 * @param targetName
	 *            getClass().getName()
	 * @param methodName
	 *            方法名称
	 * @param arguments
	 *            参数名称
	 * @return
	 */
	public static String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}

		return sb.toString();
	}
}
