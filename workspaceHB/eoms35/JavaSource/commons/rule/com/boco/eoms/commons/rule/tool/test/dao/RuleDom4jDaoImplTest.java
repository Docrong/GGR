package com.boco.eoms.commons.rule.tool.test.dao;

import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.rule.tool.dao.IRuleDom4jDao;
import com.boco.eoms.commons.rule.tool.exception.RuleToolDom4jXMLException;
import com.boco.eoms.commons.rule.tool.service.IRuleXMLDom4jDocumentFactoryService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 4:17:40 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleDom4jDaoImplTest extends ConsoleTestCase {

	IRuleDom4jDao ruleDom4jDao;

	IRuleXMLDom4jDocumentFactoryService dfs;

	protected void onSetUpInTransaction() throws Exception {
		super.setUp();
		ruleDom4jDao = (IRuleDom4jDao) getBean("ruleDom4jDao");
		dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
	}


	public void testFindRuleById() {
		try {

			List list = (List) ruleDom4jDao
					.findRulesById(
							"/ruleEngine/groups/group[@id='group1']",
							dfs
									.getDocument("classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml"));
			assertNotNull(list);
		} catch (RuleToolDom4jXMLException e) {
			fail();
		}
	}

}
