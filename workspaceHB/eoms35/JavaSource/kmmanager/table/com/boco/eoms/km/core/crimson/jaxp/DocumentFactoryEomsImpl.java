package com.boco.eoms.km.core.crimson.jaxp;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;

import com.boco.eoms.km.core.crimson.tree.XmlDocument;

public class DocumentFactoryEomsImpl extends DocumentFactory {

	public static DocumentBuilderFactoryImpl factory = new DocumentBuilderFactoryImpl();

	public Document newDocument() {
		return new XmlDocument();
	}

	public Document parseFile(String filePath) throws XmlException {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new XmlException(e.getMessage());
		}
	}

	public Document parseString(String xmlString) throws XmlException {
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			return builder.parse(new ByteArrayInputStream(xmlString.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new XmlException(e.getMessage());
		}
	}

}
