package com.boco.eoms.commons.rule.tool.service;

import org.dom4j.Document;

import com.boco.eoms.commons.rule.tool.exception.RuleToolDom4jXMLException;

/**
 * <p>
 * Title: 获取xmlDocument的工厂类，这里可以添加缓存
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 5:19:58 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public interface IRuleXMLDom4jDocumentFactoryService {
	public Document getDocument(String xmlPath)
			throws RuleToolDom4jXMLException;

}
