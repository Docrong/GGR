package com.boco.eoms.commons.rule.tool.service.impl;


import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.boco.eoms.base.util.StaticMethod;
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
 * Date:May 23, 2007 5:23:47 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleXMLDom4jDocumentFactoryServiceImpl implements
		IRuleXMLDom4jDocumentFactoryService {

	private SAXReader saxReader;

	public void setSaxReader(SAXReader saxReader) {
		this.saxReader = saxReader;
	}

	public Document getDocument(String xmlPath)
			throws RuleToolDom4jXMLException {
		try {
			// TODO 可能使用Url方式读取

			Document doc = saxReader.read(StaticMethod.getFilePath(xmlPath));
		
			return doc;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuleToolDom4jXMLException("初使化" + xmlPath + "文件不成功\n"
					+ e.getMessage());
		}
	}
}
