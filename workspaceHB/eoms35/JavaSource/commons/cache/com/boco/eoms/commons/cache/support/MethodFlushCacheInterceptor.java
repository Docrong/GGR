package com.boco.eoms.commons.cache.support;

import java.util.Properties;

import net.sf.ehcache.Cache;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.boco.eoms.base.util.ApplicationContextHolder;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 9, 2007 10:33:48 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class MethodFlushCacheInterceptor implements MethodInterceptor,
		InitializingBean {
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * addUser=listUser ,methodName=cacheKey
	 */
	private Properties method;

	private Cache cache;

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Properties getMethod() {
		return method;
	}

	public void setMethod(Properties method) {
		this.method = method;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		String className = targetName + "." + methodName;
		if (method.containsKey(className)) {
			//取自定义的返回缓存key的callback
			ICacheKeyCallback callback = (ICacheKeyCallback) ApplicationContextHolder
					.getInstance().getBean((String) method.get(className));
			//cache.remove(method.get(className));
			//返回要刷新（删除）的cache key值
			String[] keys = callback.execute(targetName, methodName, invocation
					.getArguments());
			if (null != keys) {
				for (int i = 0; i < keys.length; i++) {
					//删除key值
					cache.remove(keys[i]);
				}
			}
		}
		Object obj = invocation.proceed();
		return obj;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache,
				"A cache is required. Use setCache(Cache) to provide one.");
	}

}
