package com.boco.eoms.commons.statistic.base.excelutil.dbexcel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Title:        
 * Description:  
 * Copyright:    Copyright (c) 2002
 * Company:      UNC
 * @author      zhouj@unc.com.cn
 * @version     1.0
 */
public class DomUtter {
	
	public Document parseDocument(String uri) {
		Document doc = null;
		try {
			DocumentBuilder docBuilder =
				DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = docBuilder.parse(uri);
		} catch (Exception e) {
			System.err.println("Sorry, an error occurred: " + e);
		}
        return doc;
	}
	
	public Document newDocument(){
		Document doc = null;
		try {
			DocumentBuilder docBuilder =
				DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = docBuilder.newDocument();
		} catch (Exception e) {
			System.err.println("Sorry, an error occurred: " + e);
		}
        return doc;
	}

	/** Prints the specified node, recursively. */
	public void printDOMTree(Node node) {
		int type = node.getNodeType();
		switch (type) {
			// print the document element
			case Node.DOCUMENT_NODE :
				{
					System.out.println("<?xml version=\"1.0\" ?>");
					printDOMTree(((Document) node).getDocumentElement());
					break;
				}

				// print element with attributes
			case Node.ELEMENT_NODE :
				{
					System.out.print("<");
					System.out.print(node.getNodeName());
					NamedNodeMap attrs = node.getAttributes();
					for (int i = 0; i < attrs.getLength(); i++) {
						Node attr = attrs.item(i);
						System.out.print(
							" "
								+ attr.getNodeName()
								+ "=\""
								+ attr.getNodeValue()
								+ "\"");
					}
					System.out.print(">");

					NodeList children = node.getChildNodes();
					if (children != null) {
						int len = children.getLength();
						for (int i = 0; i < len; i++)
							printDOMTree(children.item(i));
					}

					break;
				}

				// handle entity reference nodes
			case Node.ENTITY_REFERENCE_NODE :
				{
					System.out.print("&");
					System.out.print(node.getNodeName());
					System.out.print(";");
					break;
				}

				// print cdata sections
			case Node.CDATA_SECTION_NODE :
				{
					System.out.print("<![CDATA[");
					System.out.print(node.getNodeValue());
					System.out.print("]]>");
					break;
				}

				// print text
			case Node.TEXT_NODE :
				{
					System.out.print(node.getNodeValue());
					break;
				}

				// print processing instruction
			case Node.PROCESSING_INSTRUCTION_NODE :
				{
					System.out.print("<?");
					System.out.print(node.getNodeName());
					String data = node.getNodeValue();
					{
						System.out.print(" ");
						System.out.print(data);
					}
					System.out.print("?>");
					break;
				}
		}

		if (type == Node.ELEMENT_NODE) {
			System.out.print("</");
			System.out.print(node.getNodeName());
			System.out.print('>');
		}
	} // printDOMTree(Node)

	/** Main program entry point. */
	public static void main(String argv[]) {
		if (argv.length == 0) {
			System.out.println("Usage:  java DomWriter uri");
			System.out.println(
				"   where uri is the URI of the XML document you want to print.");
			System.out.println("   Sample:  java DomWriter sonnet.xml");
			System.exit(1);
		}
 
		DomUtter dw = new DomUtter();
		dw.printDOMTree(dw.parseDocument(argv[0]));
	}

}
