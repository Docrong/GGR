package com.boco.eoms.commons.rule.tool.test.service;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.rule.config.model.Group;
import com.boco.eoms.commons.rule.config.model.Parameter;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.tool.exception.RuleToolXMLException;
import com.boco.eoms.commons.rule.tool.service.IRuleXMLDom4jDocumentFactoryService;
import com.boco.eoms.commons.rule.tool.service.IRuleXMLService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 9:12:15 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleXMLServiceImplTest extends ConsoleTestCase {

	IRuleXMLService ruleXMLService;

	IRuleXMLDom4jDocumentFactoryService dfs;

	Document document;

	Element element;

	// Attribute attribute;

	protected void onSetUpInTransaction() throws Exception {
		ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		super.setUp();
	}


	public void testFindRuleGroupByGroupId() {
		try {
			document = dfs
					.getDocument("classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml");
			element = (Element) ruleXMLService.findRuleGroupByGroupId("group1",
					document);
			// logger.debug(element);
			// logger.debug(element.getStringValue());
			assertNotNull(element);
			assertEquals(element.attribute("id").getStringValue(), "group1");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testIsRuleGroupExist() {
		testFindRuleGroupByGroupId();
		boolean bool = ruleXMLService.isRuleGroupExist("group1", document);
		assertEquals(true, bool);

	}

	public void testUpdateDocument() {
		testFindRuleGroupByGroupId();
		try {
			// attribute.setText("你好");
			ruleXMLService
					.saveDocument(document,
							"classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml");
		} catch (RuleToolXMLException e) {
			fail();
		}
	}

	public void testFindRuleByRuleId() {
		Element element = null;
		try {
			Document document = dfs
					.getDocument("classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml");
			element = (Element) ruleXMLService.findRuleByRuleId("Rule1Sample",
					document);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(element);
		assertEquals(element.attribute("id").getStringValue(), "Rule1Sample");
	}

	/**
	 * 模糊查询规则分组
	 * 
	 * @param group
	 * @param document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public void testFindRuleGroupForFuzzy() throws RuleToolXMLException {

		Group group = new Group();
		group.setId("g");
		// group.setDescription("aa");
		Document document = dfs
				.getDocument("classpath:com/boco/eoms/commons/rule/sample/RuleSample.xml");
		List list = ruleXMLService.findRuleGroup(group, document);
		assertNotNull(list);
		System.out
				.println("---------------------------------fuzzy group--------------------------");
		for (Iterator it = list.iterator(); it.hasNext();) {
			Group gp = (Group) it.next();
			System.out.println(gp.getId());

		}
	}

	/**
	 * 模糊查询规则
	 * 
	 * @param rule
	 * @param document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public void testFindRuleForFuzzy() throws RuleToolXMLException {

		Rule rule = new Rule();
		// rule.setDescription("测试规则");
		rule.setId("newRule");
		Document document = dfs.getDocument("D:/RuleSample.xml");
		List list = ruleXMLService.findRule(rule, document);
		assertNotNull(list);
		System.out
				.println("---------------------------------fuzzy rule--------------------------");
		for (Iterator it = list.iterator(); it.hasNext();) {
			Rule re = (Rule) it.next();
			System.out.println("RuleId:" + re.getId());
			if (re.getInput() != null && re.getInput().getParameters() != null) {
				for (Iterator it1 = re.getInput().getParameters().iterator(); it1
						.hasNext();) {
					Parameter para = (Parameter) it1.next();
					System.out.println("parameter name:" + para.getName());
					
					
				}
			}
		}
	}

}
