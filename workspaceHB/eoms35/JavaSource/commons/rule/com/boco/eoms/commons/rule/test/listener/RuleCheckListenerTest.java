package com.boco.eoms.commons.rule.test.listener;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.listener.IRuleListener;
import com.boco.eoms.commons.rule.listener.RuleCheckListener;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample;
import com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample;
import com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample;
import com.boco.eoms.commons.rule.service.RuleConfigWrapper;

/**
 * <p>
 * Title:规则xml配置参数与输入输出参数验证
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 8:09:43 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleCheckListenerTest extends TestCase {
	private Logger logger = Logger.getLogger(this.getClass());

	IRuleListener ruleCheckListener = new RuleCheckListener();

	RuleConfigWrapper ruleWrapper;

	RuleEngine rules;

	Rule rule;

	protected void setUp() throws Exception {
		super.setUp();
		// TODO 测试用例证明耦合
		this.ruleWrapper = new RuleConfigWrapper();
		this.rules = ruleWrapper
				.getRuleEngine("classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml");
		this.rule = ruleWrapper.findRulesByRuleId(rules, "Rule1Sample");
		ruleCheckListener = new RuleCheckListener();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAfter() {

		// 测试输入参数类弄与xml是否匹配
		Map outMap = new HashMap();
		Rule1OutputParameter1Sample outputParam1 = new Rule1OutputParameter1Sample();
		Rule1OutputParameter2Sample outputParam2 = new Rule1OutputParameter2Sample();
		outputParam1.setOk(true);
		outputParam2.setOk(false);
		outputParam1.setName("qjb");
		outputParam2.setSex("male");
		outMap.put("rule1OutputParameter1Sample", outputParam1);
		outMap.put("rule1OutputParameter2Sample", outputParam2);

		try {
			this.ruleCheckListener.after(outMap, rule);
		} catch (RuleException e) {
			logger.error(e);
			fail();
		}

	}

	public void testBefore() {

		// 测试输入参数类弄与xml是否匹配
		Map inputMap = new HashMap();
		Rule1InputParameter1Sample inputParam1 = new Rule1InputParameter1Sample();
		Rule1InputParameter2Sample inputParam2 = new Rule1InputParameter2Sample();
		inputParam1.setAge(new Integer(15));
		inputParam1.setName("qjb");
		inputParam2.setSex("male");
		inputMap.put("rule1InputParameter1Sample", inputParam1);
		inputMap.put("rule1InputParameter2Sample", inputParam2);

		try {
			this.ruleCheckListener.before(inputMap, rule);
		} catch (RuleException e) {
			logger.error(e);
			fail();
		}
	}
}
