package com.boco.eoms.commons.rule.test.service;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample;
import com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample;
import com.boco.eoms.commons.rule.service.RuleServiceFacade;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 19, 2007 10:17:00 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleServiceFacadeTest extends TestCase {
	private RuleServiceFacade facade;

	private Map inputMap;

	private Rule1InputParameter1Sample inputParam1;

	private Rule1InputParameter2Sample inputParam2;

	private String xmlPath = "";

	private String groupId = "";

	/**
	 * 输入参数初始化
	 */
	protected void setUp() throws Exception {
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
		xmlPath = "classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml";
		groupId = "group1";
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * 以classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml为配置文件，调用rule1Sample为ruleId，以inputMap为输入参数，
	 *  
	 */
	public void testInvokeRuleService() {
		try {
			facade.invokeRuleService(xmlPath, "Rule1Sample", inputMap);
		} catch (RuleException e) {
			fail();
		}
	}

	/**
	 * 以规则分组（路由）方式调用，调用groupId即group1，按照xml配置是调用了两个rule，按照优先级（数字由大到小）先后调用
	 * 以不同输入参数调用
	 */
public void testInvokeRuleGroupForDiffInputMap() {

		Map map = new HashMap();
		Map outMap = null;
		// 以不同输入参数调用
		map.put("Rule1Sample", inputMap);
		map.put("Rule2Sample", inputMap);
		try {
			outMap = facade.invokeRuleGroupForDiffInputMap(xmlPath, groupId,
					map);

		} catch (RuleException e) {
			fail();
		}
		checkOutMap(outMap);
	}
	/**
	 * 以规则分组（路由）方式调用，调用groupId即group1，按照xml配置是调用了两个rule，按照优先级（数字由大到小）先后调用
	 * 以相同输入参数调用
	 *  
	 */
	public void testInvokeRuleGroupForSampeInputMap() {
		Map outMap = null;
		try {
			outMap = facade.invokeRuleGroupForSampeInputMap(xmlPath, groupId,
					inputMap);
		} catch (RuleException e) {
			e.printStackTrace();
			fail();
		}
		checkOutMap(outMap);
	}

	/**
	 * 验证输出参数，用于测试
	 * 
	 * @param outMap
	 */
	private void checkOutMap(Map outMap) {
		Rule1OutputParameter2Sample outPram2 = (Rule1OutputParameter2Sample) outMap
				.get("rule1OutputParameter2Sample");
		assertEquals(outPram2.getSex(), "male");
		assertEquals(outPram2.isOk(), true);
	}
}
