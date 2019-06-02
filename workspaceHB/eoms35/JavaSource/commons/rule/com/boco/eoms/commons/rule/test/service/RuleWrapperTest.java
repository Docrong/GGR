package com.boco.eoms.commons.rule.test.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.config.model.Parameter;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.exception.RuleListenerException;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample;
import com.boco.eoms.commons.rule.service.RuleConfigWrapper;

/**
 * <p>
 * Title:Rule封装对外接口测试
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 12, 2007 10:07:24 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleWrapperTest extends TestCase {
	private final Logger logger = Logger.getLogger(RuleWrapperTest.class);

	String mappingXml = "classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml";

	String ruleId = "Rule1Sample";

	Map inputMap;

	RuleEngine rules;

	Rule rule;

	protected void setUp() throws Exception {

		// 测试输入参数类弄与xml是否匹配
		inputMap = new HashMap();
		Rule1InputParameter1Sample inputParam1 = new Rule1InputParameter1Sample();
		Rule1InputParameter2Sample inputParam2 = new Rule1InputParameter2Sample();
		inputParam1.setAge(new Integer(23));
		inputParam1.setName("qjb");
		inputParam2.setSex("male");
		inputMap.put("rule1InputParameter1Sample", inputParam1);
		inputMap.put("rule1InputParameter2Sample", inputParam2);

		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckRulesId() {

		this.testFindRulesByRuleId();
		assertNotNull(rule);
		for (Iterator paraIt = rule.getInput().getParameters().iterator(); paraIt
				.hasNext();) {
			Parameter para = (Parameter) paraIt.next();
			try {
				RuleConfigWrapper.checkMapType(inputMap, para);
			} catch (RuleException e) {
				logger.error(e);
				fail();
			}
		}

	}

	public void testFindRulesByRuleId() {
		testGetRules();
		assertNotNull(rules);
		try {
			this.rule = RuleConfigWrapper.findRulesByRuleId(rules, ruleId);
			assertNotNull(rule);
		} catch (RuleException e) {
			logger.error(e);
			fail();
		}
	}

	public void testCheckMapType() {
		this.testFindRulesByRuleId();
		assertNotNull(rule);
		for (Iterator paraIt = rule.getInput().getParameters().iterator(); paraIt
				.hasNext();) {
			Parameter para = (Parameter) paraIt.next();
			try {
				RuleConfigWrapper.checkMapType(inputMap, para);
			} catch (RuleException e) {
				logger.error(e);
				fail();
			}
		}
	}

	public void testGetRules() {

		try {
			this.rules = RuleConfigWrapper
					.getRuleEngine("classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml");
			assertNotNull(rules);

		} catch (RuleException e) {
			logger.error(e);
			fail();
		}
	}

	public void testGetListener() {
		this.testFindRulesByRuleId();
		assertNotNull(rule);
		try {
			List list = RuleConfigWrapper.getListener(rule);
			assertNotNull(list);
			assertTrue(!list.isEmpty());
		} catch (RuleListenerException e) {
			logger.error(e);
			fail();
		}
	}

}
