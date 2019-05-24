package com.boco.eoms.km.core;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLTranslator {

	public String getNodeName(Node aNode) {
		return aNode.getNodeName();
	}

	public Element getElementInstance(Document aDoc, String aName) {
		return aDoc.createElement(aName);
	}

	public Element getElementInstance(Document aDoc, String aName, String aValue) {
		Element element = aDoc.createElement(aName);
		Text text = aDoc.createTextNode(aValue);
		element.appendChild(text);
		return element;
	}

	public Element getLowElementByName(Element anElement, String aName) {
		NodeList nodeList = anElement.getChildNodes();
		int len = nodeList.getLength();
		for (int i = 0; i < len; ++i) {
			Node node = nodeList.item(i);
			if ((node.getNodeType() == 1) && (node.getNodeName().equals(aName))) {
				return ((Element) node);
			}
		}
		return null;
	}

	public String getFieldValueFromEntity(Element anElement, String aName) {
		Element field = getLowElementByName(anElement, aName);
		if (field == null) {
			return null;
		}
		return getElementValue(field);
	}

	public List getChildNodesList(Node aNode) {
		List list = new ArrayList();
		if (aNode == null) {
			return list;
		}
		NodeList nodeList = aNode.getChildNodes();
		int length = nodeList.getLength();
		for (int i = 0; i < length; i++) {
			Node node = nodeList.item(i);
			if (!node.getNodeName().trim().equals("#text")
					&& !node.getNodeName().trim().equals("#comment")) {
				list.add(node);
			}
		}
		return list;
	}

	public Node getChildNode(Node aNode, String aName) {
		if (aNode == null)
			return null;
		NodeList nodeList = aNode.getChildNodes();
		int length = nodeList.getLength();
		for (int i = 0; i < length; ++i) {
			Node node = nodeList.item(i);
			if (node.getNodeName().trim().equals(aName))
				return node;
		}
		return null;
	}

	public Element findByAttr(Element entity, String attrName, String attrValue) {
		NodeList list = entity.getChildNodes();
		int length = list.getLength();
		Node node = null;
		for (int i = 0; i < length; ++i) {
			node = list.item(i);
			if (node.getNodeType() != 1)
				continue;
			String value = ((Element) node).getAttribute(attrName);
			if ((value != null) && (attrValue.equals(value)))
				return ((Element) node);
		}
		return null;
	}

	public String getNodeAttr(Node aNode, String aName) {
		if (aNode == null)
			return null;
		NamedNodeMap map = aNode.getAttributes();
		Node node = map.getNamedItem(aName);
		if (node == null)
			return null;
		String value = node.getNodeValue();
		if (value == null)
			return null;
		return value.trim();
	}

	public void removeNode(Node aNode, String nodeName) {
		Node myNode = getChildNode(aNode, nodeName);
		if (myNode != null)
			aNode.removeChild(myNode);
	}

	public String getElementValue(Element anElement) {
		if (anElement == null)
			return null;
		Node node = anElement.getFirstChild();
		if (node == null)
			return null;
		String value = node.getNodeValue();
		if (value == null)
			return null;
		return value.trim();
	}

	public void setElementValue(Element anElement, String aValue) {
		Node node = anElement.getFirstChild();
		if (node == null) {
			if (aValue == null)
				return;
			node = anElement.getOwnerDocument().createTextNode(aValue);
			anElement.appendChild(node);
		} else if (aValue == null) {
			anElement.removeChild(node);
		} else {
			node.setNodeValue(aValue);
		}
	}

	public void setFieldValue(Element anElement, String aFieldName,
			String aValue) {
		Element field = getLowElementByName(anElement, aFieldName);
		if (field == null) {
			field = anElement.getOwnerDocument().createElement(aFieldName);
			anElement.appendChild(field);
		}
		setElementValue(field, aValue);
	}

	public Element addChildNode(Element anElement, String aNodeName,
			String aNodeValue) {
		Document doc = anElement.getOwnerDocument();
		Element e = doc.createElement(aNodeName);
		Text text = doc.createTextNode(aNodeValue);
		e.appendChild(text);
		anElement.appendChild(e);
		return e;
	}

	public Element addChildNode(Element anElement, String aNodeName) {
		Element e = anElement.getOwnerDocument().createElement(aNodeName);
		anElement.appendChild(e);
		return e;
	}

	public void clearChilds(Element anElement) {
		NodeList list = anElement.getChildNodes();
		int length = list.getLength();
		for (int i = 0; i < length; ++i) {
			Node node = list.item(i);
			if (node.getNodeType() == 1)
				anElement.removeChild(node);
		}
	}

	public static String convertChar(String aOpe) {
		if (aOpe.equals("&gt;"))
			return ">";
		if (aOpe.equals("&lt;"))
			return "<";
		return null;
	}
}