package com.boco.eoms.commons.rule.tool.test.service;

import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.rule.tool.exception.RuleToolException;
import com.boco.eoms.commons.rule.tool.model.Rule;
import com.boco.eoms.commons.rule.tool.service.IRuleAttributesService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 11:29:12 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleAttributesServiceImplTest extends ConsoleTestCase {
	IRuleAttributesService ruleAttributesService;

	List list;

	protected void onSetUpInTransaction() throws Exception {
		super.setUp();
		ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
	}


	public void testFindRuleById() {
		this.testListRules();
		try {
			Rule rule = ruleAttributesService.findRuleById("1");
			assertNotNull(rule);
		} catch (RuleToolException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testListRules() {
		try {
			list = ruleAttributesService.listRules();
			assertNotNull(list);
		} catch (RuleToolException e) {
			e.printStackTrace();
			fail();
		}

	}

}
