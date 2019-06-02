package com.boco.eoms.commons.rule.tool.dao.impl;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.boco.eoms.commons.rule.tool.dao.IRulesSaxDao;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 6, 2007 11:25:57 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RulesSaxDaoImpl implements IRulesSaxDao, ContentHandler {

	public List getRules() {
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
		// TODO Auto-generated method stub

	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

}
