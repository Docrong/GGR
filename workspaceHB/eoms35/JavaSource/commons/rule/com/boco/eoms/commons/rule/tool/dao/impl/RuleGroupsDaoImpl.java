package com.boco.eoms.commons.rule.tool.dao.impl;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.boco.eoms.commons.rule.tool.dao.IRuleGroupsDao;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 6, 2007 11:35:38 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleGroupsDaoImpl implements IRuleGroupsDao, ContentHandler {

	public List getRuleGroups() {
		return null;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		System.out.print("元素开始: " + localName);

		if ("rule".equals(localName)) {
			atts.getValue("className");
		}

		// 打印属性
		for (int i = 0; i < atts.getLength(); i++)
			System.out.println("\t属性名称: " + atts.getLocalName(i) + " = "
					+ atts.getValue(i) + ".");

	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

}
