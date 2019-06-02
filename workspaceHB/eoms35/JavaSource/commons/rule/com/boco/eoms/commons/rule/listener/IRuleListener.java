package com.boco.eoms.commons.rule.listener;

import java.util.Map;

import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.exception.RuleException;

/**
 * <p>
 * Title:规则监听
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 1:48:59 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public interface IRuleListener {

	/**
	 * 调用Rule前
	 * 
	 * @param inputMap
	 *            rule的输入参数
	 * @param rule
	 *            xml规则对象
	 * @throws RuleException
	 */
	public void before(Map inputMap, Rule rule) throws RuleException;

	/**
	 * 调用方法后
	 * 
	 * @param outputMap
	 *            rule的输出参数
	 * @param rule
	 *            xml规则对象
	 * @throws RuleException
	 */
	public void after(Map outputMap, Rule rule) throws RuleException;

}
