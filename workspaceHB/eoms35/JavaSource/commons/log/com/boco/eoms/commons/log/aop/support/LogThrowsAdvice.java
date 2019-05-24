package com.boco.eoms.commons.log.aop.support;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 11, 2008 1:10:06 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class LogThrowsAdvice implements ThrowsAdvice {
	/**
	 * log4j
	 */
	private static final Logger logger = Logger
			.getLogger(LogBeforeMethodAdvice.class);

	/**
	 * 抛出异常后调用
	 * 
	 * @param m
	 * @param args
	 * @param target
	 * @param ex
	 */
	public void afterThrowing(Method m, Object[] args, Object target,
			Exception ex) {
		logger.info(ex);
	}
}
