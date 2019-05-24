package com.boco.eoms.km.core.bizlets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.boco.eoms.km.core.crimson.util.XmlUtil;

public class BNXmlUtil {

	/**
	 * 根据提供的节点，取得其值。
	 * 
	 * @param paramNode
	 * @return String
	 */
	public static String getNodeValue(Node paramNode) {
		if (paramNode == null)
			return null;
		NodeList localNodeList = paramNode.getChildNodes();
		int i = localNodeList.getLength();
		for (int j = 0; j < i; ++j) {
			Node localNode = localNodeList.item(j);
			if (localNode.getNodeType() == 3)
				return localNode.getNodeValue();
		}
		return null;
	}

	public static String getNodeValue(Node paramNode, String paramString) {
		if ((paramNode == null) || (paramString == null))
			return null;
		NodeList localNodeList = paramNode.getChildNodes();
		int i = localNodeList.getLength();
		Node localNode = null;
		for (int j = 0; j < i; ++j) {
			localNode = localNodeList.item(j);
			if (paramString.equals(localNode.getNodeName()))
				return getNodeValue(localNode);
		}
		return null;
	}

	public static void setNodeValue(Node paramNode, String nodeValue) {
		if (paramNode == null)
			return;
		if (nodeValue == null)
			nodeValue = "";
		NodeList localNodeList = paramNode.getChildNodes();
		int i = localNodeList.getLength();
		for (int j = 0; j < i; ++j) {
			Node localNode = localNodeList.item(j);
			if (localNode.getNodeType() == 3) {
				localNode.setNodeValue(nodeValue);
				return;
			}
		}
		paramNode.appendChild(paramNode.getOwnerDocument().createTextNode(
				nodeValue));
	}

	public static void setNodeValue(Node paramNode, String newNodeName,
			String newNodeValue) {
		if ((paramNode == null) || (newNodeName == null))
			return;
		NodeList localNodeList = paramNode.getChildNodes();
		int i = localNodeList.getLength();
		for (int j = 0; j < i; ++j) {
			Node localNode = localNodeList.item(j);
			if ((localNode.getNodeType() == 1)
					&& (localNode.getNodeName().equals(newNodeName))) {
				setNodeValue(localNode, newNodeValue);
				return;
			}
		}
		Element localElement = paramNode.getOwnerDocument().createElement(
				newNodeName);
		setNodeValue(localElement, newNodeValue);
		paramNode.appendChild(localElement);
	}

	public static void setAttribute(Node paramNode, String paramString1,
			String paramString2) {
		if ((paramNode == null) || (paramString1 == null))
			return;
		if (paramNode.getNodeType() != 1)
			return;
		String str = paramString2;
		if (paramString2 == null)
			str = "";
		((Element) paramNode).setAttribute(paramString1, str);
	}

	public static String getAttribute(Node paramNode, String paramString) {
		if ((paramNode == null) || (paramString == null))
			return null;
		if (paramNode.getNodeType() != 1)
			return null;
		return ((Element) paramNode).getAttribute(paramString);
	}

	public static void removeAllAttr(Node paramNode) {
		NamedNodeMap localNamedNodeMap;
		int i;
		if (paramNode != null) {
			localNamedNodeMap = paramNode.getAttributes();
			for (i = localNamedNodeMap.getLength(); i > 0; --i)
				localNamedNodeMap.removeNamedItem(localNamedNodeMap.item(i - 1)
						.getNodeName());
		}
	}

	public static void removeAllChild(Node paramNode) {
		NodeList localNodeList;
		int i;
		if (paramNode != null) {
			localNodeList = paramNode.getChildNodes();
			for (i = localNodeList.getLength(); i > 0; --i)
				paramNode.removeChild(localNodeList.item(i - 1));
		}
	}

	public static void copyNode(Node paramNode1, Node paramNode2) {
		if ((paramNode1 == null) || (paramNode2 == null))
			return;
		removeAllChild(paramNode2);
		NodeList localNodeList = paramNode1.getChildNodes();
		int i = localNodeList.getLength();
		for (int j = 0; j < i; ++j) {
			Node localNode = localNodeList.item(j).cloneNode(true);
			paramNode2.appendChild(localNode);
		}
	}

	public static Node getChildNode(Node paramNode, String paramString) {
		Object localObject;
		if ((paramNode == null) || (paramString == null))
			return null;
		NodeList localNodeList = paramNode.getChildNodes();
		int i = localNodeList.getLength();
		if (i < 1) {
			localObject = paramNode.getOwnerDocument().createElement(
					paramString);
			paramNode.appendChild((Node) localObject);
		} else {
			for (int j = 0; j < i; ++j) {
				localObject = localNodeList.item(j);
				if (((Node) localObject).getNodeName().equals(paramString))
					return ((Node) (localObject));
			}
			localObject = paramNode.getOwnerDocument().createElement(
					paramString);
			paramNode.appendChild((Node) localObject);
		}
		return ((Node) localObject);
	}

	public static void appendEntityList(Node paramNode1, Node paramNode2) {
		appendNode(paramNode1, paramNode2, true);
	}

	public static void appendNode(Node paramNode1, Node paramNode2,
			boolean paramBoolean) {
		Node localNode2;
		if ((paramNode1 == null) || (paramNode2 == null))
			return;
		if (!(paramBoolean)) {
			NodeList localNodeList1 = paramNode1.getChildNodes();
			int j = localNodeList1.getLength();
			for (int k = 0; k < j; ++k) {
				Node localNode1 = localNodeList1.item(k).cloneNode(true);
				paramNode2.appendChild(localNode1);
			}
			return;
		}
		int i = 0;
		NodeList localNodeList2 = paramNode2.getChildNodes();
		int k = localNodeList2.getLength();
		for (int l = 0; l < k; ++l) {
			localNode2 = localNodeList2.item(l);
			if (localNode2.getNodeType() == 1) {
				setAttribute(localNode2, "rowNum", String.valueOf(i));
				++i;
			}
		}
		localNodeList2 = paramNode1.getChildNodes();
		k = localNodeList2.getLength();
		for (int l = 0; l < k; ++l) {
			localNode2 = localNodeList2.item(l).cloneNode(true);
			if (localNode2.getNodeType() == 1) {
				setAttribute(localNode2, "rowNum", String.valueOf(i));
				++i;
				paramNode2.appendChild(localNode2);
			}
		}
		setAttribute(paramNode2, "rowNum", String.valueOf(i));
	}

	public static int list2Entity(Node paramNode1, Node paramNode2,
			String paramString1, String paramString2) {
		if ((paramNode1 == null) || (paramNode2 == null)
				|| (paramString1 == null) || (paramString2 == null))
			return -1;
		NodeList localNodeList = paramNode1.getChildNodes();
		int i = localNodeList.getLength();
		Document localDocument = paramNode2.getOwnerDocument();
		for (int j = 0; j < i; ++j) {
			Node localNode = localNodeList.item(j);
			if (localNode.getNodeType() == 1) {
				String str = getNodeValue(localNode, paramString1);
				if (str != null) {
					Element localElement = localDocument.createElement(str);
					setNodeValue(localElement, getNodeValue(localNode,
							paramString2));
					paramNode2.appendChild(localElement);
				}
			}
		}
		return 1;
	}

	public static int getNextEntitySite(NodeList paramNodeList, int paramInt) {
		for (int i = paramInt; i < paramNodeList.getLength(); ++i)
			if (paramNodeList.item(i).getNodeType() == 1)
				return i;
		return -1;
	}

	public static final Document parseString(String paramString)
			throws Exception {
		return XmlUtil.parseString(paramString);
	}

	// public static final String node2String(Node paramNode) {
	// return node2String(paramNode, "GB2312", false, false);
	// }

	public static final String node2String(Node paramNode, String paramString,
			boolean paramBoolean1, boolean paramBoolean2) {
		return XmlUtil.node2String(paramNode, paramBoolean2);
	}
}