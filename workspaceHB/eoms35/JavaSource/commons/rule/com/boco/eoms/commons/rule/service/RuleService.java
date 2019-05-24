package com.boco.eoms.commons.rule.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.listener.IRuleListener;
import com.boco.eoms.commons.rule.util.RuleConstants;

/**
 * <p>
 * Title:规则接口
 * </p>
 * <p>
 * Description:二则开发人员实现规则接务时需实现该接口,具体使用方法请参看com.boco.eoms.commons.rule.sample.Rule1Sample.java
 * </p>
 * <p>
 * Apr 11, 2007 10:07:44 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public abstract class RuleService {

	/**
	 * 输入参数(map)
	 */
	private Map inputMap;

	/**
	 * 规则优先级,默认优先级为最低
	 */
	private Integer pri = new Integer(-1);

	/**
	 * 规则所配的listener
	 */
	private List ruleListeners;

	/**
	 * 规则mapping对象
	 */
	private RuleEngine ruleEngine;

	/**
	 * xml mapping rule对象
	 */
	private Rule rule;

	/**
	 * xml配置文件
	 */
	private String xmlPath;

	/**
	 * @param ruleListener
	 * @param ruleEngine
	 * @param rule
	 * @param xmlPath
	 */
	public RuleService(List ruleListeners, RuleEngine ruleEngine, Rule rule,
			String xmlPath) {
		super();
		this.ruleListeners = ruleListeners;
		this.ruleEngine = ruleEngine;
		this.rule = rule;
		this.xmlPath = xmlPath;
	}

	/**
	 * 调用lisener前置方法
	 * 
	 * @param inputMap
	 *            输入参数
	 * @throws RuleException
	 */
	private void invokeBeforeListeners(Map inputMap) throws RuleException {
		// 如果开发人员配置xml的listener不为空，则调用listener的前置方法
		for (Iterator it = ruleListeners.iterator(); it.hasNext();) {
			IRuleListener ruleListener = (IRuleListener) it.next();
			ruleListener.before(inputMap, this.rule);
		}
	}

	/**
	 * 调用listener后置方法
	 * 
	 * @param outMap
	 *            输出参数
	 * @throws RuleException
	 */
	private void invokeAfterListeners(Map outMap) throws RuleException {
		for (Iterator it = ruleListeners.iterator(); it.hasNext();) {
			IRuleListener ruleListener = (IRuleListener) it.next();
			ruleListener.after(outMap, this.rule);
		}
	}

	/**
	 * 流程制定人员调用
	 * 
	 * @param xmlPath
	 *            xml配置文件
	 * @param map
	 *            输入参数
	 * 
	 * 
	 * @return map输出参数
	 * @throws RuleException
	 */
	public Map invoke(Map map) throws RuleException {
		// 如果开发人员配置xml的listener不为空，则调用listener的前置方法
		invokeBeforeListeners(map);
		Map outMap = this.execute(map, this.rule);
		// 如果开发人员配置xml的listener不为空，则调用listener
		invokeAfterListeners(outMap);

		// 在执行业务规则后，RuleService将对应Rule存入输出参数outMap,
		// 按map.put(RuleConstants.RULE_OUT_PARAMETER_MAP,Rule)存储，
		// 二次开发人员可以RULE_OUT_PARAMETER_MAP为KEY在outMap中取Rule
		outMap.put(RuleConstants.RULE_OUT_PARAMETER_MAP, this.rule);
		return outMap;
	}

	/**
	 * 流程制定人员调用
	 * 
	 * @param xmlPath
	 *            xml配置文件
	 * 
	 * @return map输出参数
	 * @throws RuleException
	 */
	public Map invoke() throws RuleException {
		return this.invoke(this.inputMap);
	}

	/**
	 * 二次开发人员需实现的业务接口
	 * 
	 * @param map
	 *            根据配置文件中input的配置，二次开发人员取出配置的参数，
	 *            如sample配置key="rule1InputParameter1Sample",value="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample"
	 *            key="rule1Parameter2InputSample",value="com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample"
	 *            这时二次发人员在execute中按这种方式取参数
	 *            com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample
	 *            param1=(com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample)
	 *            map.get("rule1InputParameter1Sample");
	 *            com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample
	 *            param2=(com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample)
	 *            map.get("rule1InputParameter2Sample");
	 * 
	 * @return 根据配置文件output的配置，二次开发人员需返回配置的参数 如sample配置
	 *         key="rule1OutputParameter1Sample",value="com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample"
	 *         key="rule1OutputParameter2Sample",value="com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample"
	 *         配置人员需按业务要求，返回
	 *         map.put("rule1OutputParameter1Sample",com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample);
	 *         map.put("rule1OutputParameter2Sample",com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample);
	 *         return map;
	 * @throws RuleException
	 */
	protected abstract Map execute(Map inputMap, Rule rule)
			throws RuleException;

	public Integer getPri() {
		return pri;
	}

	public void setPri(Integer pri) {
		this.pri = pri;
	}

	public Map getInputMap() {
		return inputMap;
	}

	public void setInputMap(Map inputMap) {
		this.inputMap = inputMap;
	}
}
