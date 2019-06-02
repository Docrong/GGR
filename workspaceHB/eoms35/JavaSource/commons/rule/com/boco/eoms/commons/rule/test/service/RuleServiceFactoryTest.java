package com.boco.eoms.commons.rule.test.service;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample;
import com.boco.eoms.commons.rule.service.RuleService;
import com.boco.eoms.commons.rule.service.RuleServiceFactory;

/**
 * <p>
 * Title:RuleService工厂测试
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 12, 2007 7:53:44 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleServiceFactoryTest extends TestCase {

	private final Logger logger = Logger
			.getLogger(RuleServiceFactoryTest.class);

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreate() {
		// 测试输入参数类弄与xml是否匹配
		Map inputMap = new HashMap();
		Rule1InputParameter1Sample inputParam1 = new Rule1InputParameter1Sample();
		Rule1InputParameter2Sample inputParam2 = new Rule1InputParameter2Sample();
		inputParam1.setAge(new Integer(10));
		inputParam1.setName("qjb");
		inputParam2.setSex("male");

		inputMap.put("param1", inputParam1);
		inputMap.put("param2", inputParam2);

		try {
			RuleService ruleService = RuleServiceFactory
					.create(
							"classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml",
							"Rule1Sample", inputMap);
			assertNotNull(ruleService);
		} catch (RuleException e) {
			logger.error(e);
			fail();
		}
	}
}
