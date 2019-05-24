package com.boco.eoms.km.core.crimson.util;

import java.util.ListIterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

import com.boco.eoms.km.core.crimson.tree.NodeListEx;

public class XPathAPI {

	public static Node selectSingleNode(Node contextNode, String xPath) {
		if (contextNode == null) {
			return null;
		}
		int nodeType = contextNode.getNodeType();
		if (nodeType != Node.DOCUMENT_NODE && nodeType != Node.ELEMENT_NODE) {
			return null;
		}
		Node currNode = realNode(contextNode);
		XPathParser parser = XPathParser.create(xPath);
		ListIterator itXPath = parser.listIterator();
		NodeListEx nlResult = new NodeListEx();
		findNodeList((Element) currNode, itXPath, nlResult, true);
		if (nlResult.getLength() > 0) {
			return nlResult.item(0);
		}
		return null;
	}

	private static final Node realNode(Node node) {
		if (node.getNodeType() == Node.DOCUMENT_NODE)
			return ((Document) node).getDocumentElement();
		return node;
	}

	private static final void findNodeList(Element currNode,
			ListIterator itXPath, NodeListEx result, boolean single) {
		if (single && result.getLength() > 0)
			return;
		if (!itXPath.hasNext())
			return;
		NodeDescription curDesc = (NodeDescription) itXPath.next();
		if (curDesc.getType() == Node.ELEMENT_NODE) {
			Node node = currNode.getParentNode();
			if (node == null || node.getNodeType() == Node.DOCUMENT_NODE) {
				Element parent = currNode;
				if (curDesc.equals(parent))
					result.append(curDesc.getAttribute(parent));
			}
			return;
		}
		if (!curDesc.equals(currNode))
			return;
		if (itXPath.hasNext()) {
			NodeDescription nextDesc = (NodeDescription) itXPath.next();
			if (itXPath.hasPrevious())
				itXPath.previous();
			if (nextDesc.getType() == Node.ELEMENT_NODE) {
				if (nextDesc.equals(currNode))
					result.append(nextDesc.getAttribute(currNode));
				return;
			}
		}
		if (itXPath.hasNext()) {
			NodeList nlChilds = currNode.getChildNodes();
			int nCount = nlChilds.getLength();
			for (int i = 0; i < nCount; i++) {
				Node currChild = nlChilds.item(i);
				if (currChild == null
						|| currChild.getNodeType() != Node.DOCUMENT_NODE
						&& currChild.getNodeType() != Node.ELEMENT_NODE)
					continue;
				findNodeList((Element) currChild, itXPath, result, single);
				if (itXPath.hasPrevious())
					itXPath.previous();
			}
			return;
		} else {
			result.append(currNode);
			return;
		}
	}
}
