package com.boco.eoms.commons.rule.listener;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.exception.RuleListenerException;

/**
 * <p>
 * Title:listener 工厂
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 1:54:45 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleListenerFactory {

	private final static Logger logger = Logger
			.getLogger(RuleListenerFactory.class);

	/**
	 * 能冠军className取RuleListener
	 * 
	 * @param className
	 *            实现IRuleListener接口的类
	 * @return className的实例
	 * @throws RuleListenerException
	 *             若实例化IRuleListener实现类时出错、强制转换成IRuleListener、className为空抛出异常
	 * 
	 */
	public static IRuleListener create(String className)
			throws RuleListenerException {
		if (!"".equals(className)) {

			try {
				return (IRuleListener) Class.forName(className).newInstance();
			} catch (Exception e) {
				logger.error(e);
				throw new RuleListenerException(e);
			}

		} else {
			logger.error("className为空，请配置className");
			throw new RuleListenerException("className为空，请配置className");
		}
	}
}
