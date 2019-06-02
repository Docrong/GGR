package com.boco.eoms.commons.rule.tool.dao.impl;

import org.dom4j.Document;

import com.boco.eoms.commons.rule.tool.dao.IRuleDom4jDao;
import com.boco.eoms.commons.rule.tool.exception.RuleToolDom4jXMLException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 3:42:54 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleDom4jDaoImpl implements IRuleDom4jDao {

	public Object findRuleById(String id, Document document)
			throws RuleToolDom4jXMLException {
		// Document document = ruleXMLDom4jDocumentFactoryService
		// .getDocument(xmlPath);	
		return document.selectSingleNode(id);
	}

	public Object findRulesById(String id, Document document)
			throws RuleToolDom4jXMLException {
		return document.selectNodes(id);
	}

}
