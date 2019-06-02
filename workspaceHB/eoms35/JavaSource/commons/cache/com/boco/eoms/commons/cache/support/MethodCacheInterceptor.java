package com.boco.eoms.commons.cache.support;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.boco.eoms.commons.cache.util.CacheUtil;

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
public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {
	private static final Log logger = LogFactory
			.getLog(MethodCacheInterceptor.class);

	private Cache cache;



	/**
	 * sets cache name to be used
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * Checks if required attributes are provided.
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache,
				"A cache is required. Use setCache(Cache) to provide one.");
	}

	/**
	 * main method caches method result if method is configured for caching
	 * method results must be serializable
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		Object result;
		logger.debug("looking for method result in cache");
		String cacheKey = CacheUtil.getCacheKey(targetName, methodName,
				arguments);
		Element element = cache.get(cacheKey);
		if (element == null) {
			// call target/sub-interceptor
			logger.debug("calling intercepted method");
			result = invocation.proceed();
			// cache method result
			logger.debug("caching result");
			element = new Element(cacheKey, (Serializable) result);
			cache.put(element);
		}
		return element.getValue();
	}

}