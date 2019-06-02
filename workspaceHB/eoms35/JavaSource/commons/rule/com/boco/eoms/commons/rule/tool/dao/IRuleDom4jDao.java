package com.boco.eoms.commons.rule.tool.dao;

import org.dom4j.Document;

import com.boco.eoms.commons.rule.tool.exception.RuleToolDom4jXMLException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 3:42:42 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public interface IRuleDom4jDao {
	/**
	 * 通过id取单一对象
	 * 
	 * @param id
	 *            规则分组id
	 * 
	 * @param document
	 *            xmlPath的document(dom4j)
	 * @return Attribute 或 Element
	 * @throws RuleToolDom4jXMLException
	 * 
	 */
	public Object findRuleById(String id, Document document)
			throws RuleToolDom4jXMLException;

	/**
	 * 通过id取多个对象
	 * 
	 * @param id
	 * @param document
	 * @return
	 * @throws RuleToolDom4jXMLException
	 */
	public Object findRulesById(String id, Document document)
			throws RuleToolDom4jXMLException;
}
