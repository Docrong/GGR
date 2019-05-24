package com.boco.eoms.commons.rule.test.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample;
import com.boco.eoms.commons.rule.service.RuleServiceFacade;

import junit.framework.TestCase;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 22, 2007 3:08:26 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class ExpressionRuleServiceTest extends TestCase {
	private Logger logger = Logger.getLogger(ExpressionRuleServiceTest.class);

	private RuleServiceFacade facade;

	private Map inputMap;

	private Rule1InputParameter1Sample inputParam1;

	private Rule1InputParameter2Sample inputParam2;

	private String xmlPath = "";

	private String ruleId;

	protected void setUp() throws Exception {
		super.setUp();
		facade = RuleServiceFacade.create();
		// 测试输入参数类弄与xml是否匹配
		inputMap = new HashMap();
		inputParam1 = new Rule1InputParameter1Sample();
		inputParam2 = new Rule1InputParameter2Sample();
		inputParam1.setAge(new Integer(10));
		inputParam1.setName("qjb");
		inputParam2.setSex("male");

		inputMap.put("rule1InputParameter1Sample", inputParam1);
		inputMap.put("rule1InputParameter2Sample", inputParam2);
		xmlPath = "classpath:com/boco/eoms/commons/rule/sample/ExpressionRuleSample.xml";
		ruleId = "ExpressionRule";
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExecute() {
		try {
			Map map = facade.invokeRuleService(xmlPath, ruleId, inputMap);
			String r1 = (String) map.get("rule1OutputParameter1Sample");
			Rule1InputParameter1Sample r2 = (Rule1InputParameter1Sample) map
					.get("rule1OutputParameter2Sample");
			assertEquals("qjb:10", r1);
			assertEquals(new Integer(5), r2.getAge());
		} catch (RuleException e) {
			logger.error(e);
			fail();
		}
	}

}
