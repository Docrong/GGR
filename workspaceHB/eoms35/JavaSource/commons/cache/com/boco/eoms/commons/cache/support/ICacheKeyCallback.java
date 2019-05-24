package com.boco.eoms.commons.cache.support;

import com.boco.eoms.commons.cache.exception.CacheKeyCallbackException;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 1, 2007 4:31:24 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface ICacheKeyCallback {
	/**
	 * 获取key
	 * 
	 * @param clsName
	 *            要执行的类名
	 * @param methodName
	 *            要执行的方法名
	 * @param arguments
	 *            要执行的参数名称
	 * @return 返回key串
	 * @throws CacheKeyCallbackException
	 *             出错抛出异常
	 */
	public String[] execute(String clsName, String methodName,
			Object[] arguments) throws CacheKeyCallbackException;
}
