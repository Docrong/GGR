package com.boco.eoms.commons.rule.test.sample;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample;
import com.boco.eoms.commons.rule.service.RuleService;
import com.boco.eoms.commons.rule.service.RuleServiceFactory;

/**
 * <p>
 * Title:Rule1Sample规则测试
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 12, 2007 7:54:26 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Rule1SampleTest extends TestCase {

	private final Logger logger = Logger.getLogger(Rule1SampleTest.class);

	private Map inputMap;

	private Rule1InputParameter1Sample inputParam1;

	private Rule1InputParameter2Sample inputParam2;

	private String xmlPath = "";

	protected void setUp() throws Exception {
		super.setUp();
		// 测试输入参数类弄与xml是否匹配
		inputMap = new HashMap();
		inputParam1 = new Rule1InputParameter1Sample();
		inputParam2 = new Rule1InputParameter2Sample();
		inputParam1.setAge(new Integer(10));
		inputParam1.setName("qjb");
		inputParam2.setSex("male");

		inputMap.put("rule1InputParameter1Sample", inputParam1);
		inputMap.put("rule1InputParameter2Sample", inputParam2);
		xmlPath = "classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml";
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreate() {

		try {
			RuleService ruleService = RuleServiceFactory.create(xmlPath,
					"Rule1Sample", inputMap);
			assertNotNull(ruleService);
			ruleService.invoke(inputMap);
		} catch (RuleException e) {
			logger.error(e);
			fail();
		}

	}

	public void testExpression() {
		try {
			Map input = new HashMap();
			input.put("age", new Integer(8));
			input.put("name", "qjb");
			RuleService ruleService = RuleServiceFactory.create(
					"d:/RuleSample.xml", "newRule", input);

			Map output = ruleService.invoke(input);

			System.out.println((String) output.get("msg"));
		} catch (RuleException e) {
			e.printStackTrace();
			logger.error(e);
			fail();
		}
	}

	public void testExpression1() {
		try {
			Map input = new HashMap();
			Rule1InputParameter1Sample input1 = new Rule1InputParameter1Sample();

			input1.setAge(new Integer(12));
			input1.setName("qjb");
			input.put("rule1InputParameter1Sample", input1);
			RuleService ruleService = RuleServiceFactory.create(
					"d:/ExpressionRuleSample.xml", "ExpressionRule", input);

			Map output = ruleService.invoke(input);

			Rule1InputParameter1Sample output1 = (Rule1InputParameter1Sample) output
					.get("rule1OutputParameter2Sample");
			// input1.getAge()>12?input1.setAge(13):input1.setAge(14);
			System.out.println(output1.getName());
		} catch (RuleException e) {
			logger.error(e);
			fail();
		}
	}
}
