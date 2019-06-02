package com.boco.eoms.km.servlet.context;

import java.io.Serializable;
import java.util.StringTokenizer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.boco.eoms.km.core.crimson.util.XmlUtil;

public class Context implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String CONTEXT_ROOT;
	protected Document doc;

	public Context(String rootName) {
		this.CONTEXT_ROOT = rootName;
		this.doc = XmlUtil.newDocument(this.CONTEXT_ROOT);
	}

	public Context(String root, String filePath) {
		try {
			this.doc = XmlUtil.parseFile(filePath);
			this.CONTEXT_ROOT = this.doc.getDocumentElement().getNodeName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Document getDocument() {
		return this.doc;
	}
	
	public void setValue(String xPath, String value, boolean overrideExist)
			throws Exception {
		String subXPath = "";
		String tmpStr = "";
		Node currNode = null;
		Node childNode = null;

		xPath = getAbstractPath(xPath);
		StringTokenizer stk = new StringTokenizer(xPath, "/");
		Node parentNode = doc.getDocumentElement();

		do {
			if (!stk.hasMoreTokens()) {
				break;
			}
			tmpStr = stk.nextToken();
			int index = tmpStr.indexOf("@");
			if (index == 0) {
				tmpStr = tmpStr.substring(index + 1);
				((Element) parentNode).setAttribute(tmpStr, value);
				continue;
			}

			subXPath = subXPath.concat(subXPath.equals("") ? "" : "/").concat(tmpStr);

			currNode = XmlUtil.findNode(doc, subXPath);

			if (currNode != null) {
				if (stk.hasMoreTokens()) {
					parentNode = currNode;
					continue;
				}
				if ("hciCreateEmptyNode".equals(value)) {
					break;
				}
				parentNode = currNode.getParentNode();
				if (overrideExist) {
					parentNode.removeChild(currNode);
				}
			}

			Element ele = createNode(tmpStr);
			if (!stk.hasMoreElements() && !"hciCreateEmptyNode".equals(value)) {
				childNode = doc.createTextNode(value);
				ele.appendChild(childNode);
			}
			parentNode.appendChild(ele);
			parentNode = ele;
		} while (true);
	}

	public String getAbstractPath(String xPath) {
		return processXPath(xPath);
	}

	public void setValue2(String xPath, String value, boolean overrideExist)
			throws Exception {
		String subXPath = "";
		String tmpStr = "";
		Node currNode = null;
		Node childNode = null;

		xPath = processXPath(xPath);
		StringTokenizer stk = new StringTokenizer(xPath, "/");
		Node parentNode = doc.getDocumentElement();

		do {
			if (!stk.hasMoreTokens()) {
				break;
			}
			tmpStr = stk.nextToken();
			int index = tmpStr.indexOf("@");
			if (index == 0) {
				tmpStr = tmpStr.substring(index + 1);
				((Element) parentNode).setAttribute(tmpStr, value);
				continue;
			}

			subXPath = subXPath.concat(subXPath.equals("") ? "" : "/").concat(
					tmpStr);

			currNode = XmlUtil.findNode(doc, subXPath);
			if (currNode != null) {
				if (stk.hasMoreTokens()) {
					parentNode = currNode;
					continue;
				}
				if ("hciCreateEmptyNode".equals(value)) {
					break;
				}
				parentNode = currNode.getParentNode();
			}

			Node ele;
			if (overrideExist) {
				if (currNode == null) {
					currNode = createNode(tmpStr);
					parentNode.appendChild(currNode);
				} else {
					NodeList children = currNode.getChildNodes();
					if (children != null) {
						int count = children.getLength();
						for (int i = 0; i < count; i++) {
							Node child = children.item(i);
							if (child != null && child.getNodeType() == 3)
								currNode.removeChild(child);
						}

					}
				}
				ele = currNode;
			} else {
				ele = createNode(tmpStr);
				parentNode.appendChild(ele);
			}

			if (!stk.hasMoreElements() && !"hciCreateEmptyNode".equals(value)) {
				childNode = doc.createTextNode(value);
				ele.appendChild(childNode);
			}
			parentNode = ele;

		} while (true);
	}

	protected String processXPath(String xPath) {
		String tmpStr = xPath;
		if (tmpStr == null || tmpStr.equals("")) {
			tmpStr = this.CONTEXT_ROOT;
		} else {
			tmpStr = this.CONTEXT_ROOT.concat("/").concat(xPath);
		}

		int index = 0;
		int count = 0;
		String retStr = "";
		String delim = "&quot;";
		do {
			index = tmpStr.indexOf(delim);
			if (index > 0) {
				if (!(retStr.equals(""))) {
					retStr = retStr.concat("\"");
				}
				retStr = retStr.concat(tmpStr).substring(0, index);
				tmpStr = tmpStr.substring(index + delim.length(), tmpStr
						.length());
				++count;
			}
		} while (index > 0);

		if (count > 0) {
			retStr = retStr.concat("\"").concat(tmpStr);
		} else {
			retStr = retStr.concat(tmpStr);
		}
		return retStr;
	}

	protected Element createNode(String xPath) throws Exception {
		StringTokenizer stk = new StringTokenizer(xPath, "[".concat("]")
				.concat("@").concat("\"").concat("'"));

		String eleName = null;
		if (stk.hasMoreTokens()) {
			eleName = stk.nextToken();
		}
		if (eleName == null) {
			return null;
		}

		Element retElement = this.doc.createElement(eleName);

		while (stk.hasMoreTokens()) {
			String eleAttrName = stk.nextToken();
			int index = eleAttrName.lastIndexOf("=");
			if (index >= 0)
				eleAttrName = eleAttrName.substring(0, index);

			String eleAttrVal = null;
			if (stk.hasMoreTokens()) {
				eleAttrVal = stk.nextToken();
			}
			if (eleAttrName != null) {
				retElement.setAttribute(eleAttrName, eleAttrVal);
			}
		}

		return retElement;
	}

}