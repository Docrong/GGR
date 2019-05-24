package com.boco.eoms.commons.rule.sample;

import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.listener.IRuleListener;

/**
 * <p>
 * Title:模拟Rule1的listener,只输出日志信息
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 2:45:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class Rule1ListenerSample implements IRuleListener {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 执行规则后调用
	 */
	public void after(Map inputMap, Rule rule) throws RuleException {
		logger.debug(rule.getId() + " after");

	}

	/**
	 * 执行规则前调用
	 */
	public void before(Map outputMap, Rule rule) throws RuleException {
		logger.debug(rule.getId() + " before");
	}
}
