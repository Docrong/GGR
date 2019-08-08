package com.boco.eoms.km.core.crimson.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.StringTokenizer;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.boco.eoms.km.core.crimson.jaxp.DocumentFactory;
import com.boco.eoms.km.core.crimson.jaxp.XmlException;

/**
 * @author zhangxb
 */
public class XmlUtil {

    /**
     * 获取 DOM Document 对象的一个新实例来生成一个 DOM 树。
     *
     * @return Document
     */
    public static final Document newDocument() {
        return DocumentFactory.newInstance().newDocument();
    }

    /**
     * 将给定路径的文件内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象。
     *
     * @param filePath 要解析的文件路径
     * @return Document
     * @throws Exception
     */
    public static final Document parseFile(String filePath) throws Exception {
        return DocumentFactory.newInstance().parseFile(filePath);
    }

    /**
     * 将给定 XML 格式的字符串解析为一个 XML 文档，并且返回一个新的 DOM Document 对象。
     *
     * @param xmlString 要解析的 XML 格式的字符
     * @return Document
     * @throws Exception
     */
    public static final Document parseString(String xmlString) throws Exception {
        return DocumentFactory.newInstance().parseString(xmlString);
    }

    /**
     * 获取一个包含指定类型元素的 DOM 树。
     *
     * @param rootName 要创建的节点名称
     * @return Document
     */
    public static final Document newDocument(String rootName) {
        Document doc = newDocument();
        Element root = doc.createElement(rootName);
        doc.appendChild(root);
        return doc;
    }

    /**
     * 获取一个包含指定节点的 DOM 树。
     *
     * @param root 要移入此文档的节点
     * @return Document
     */
    public static final Document newDocument(Node root) {
        Document doc = newDocument();
        doc.appendChild(root);
        return doc;
    }

    /**
     * 设置 DOM 树的根节点为指定的节点。
     *
     * @param doc      被设置 DOM 树
     * @param rootName 要创建的节点名称
     * @return Document
     * @deprecated
     */
    public static final Document setRoot(Document doc, String rootName) {
        Node root = doc.getDocumentElement();
        if (root != null) {
            doc.removeChild(root);
        }
        doc.appendChild(doc.createElement(rootName));
        return doc;
    }

    /**
     * 设置 DOM 树的根节点为指定节点。
     *
     * @param doc  被设置 DOM 树
     * @param root 要移入此文档的节点
     * @return Document
     * @deprecated
     */
    public static final Document setRoot(Document doc, Node root) {
        Node oldRoot = doc.getDocumentElement();
        if (oldRoot != null) {
            doc.removeChild(oldRoot);
        }
        doc.appendChild(root);
        return doc;
    }

    /**
     * 此节点的值，取决于其类型。
     *
     * @param node 要处理的节点
     * @return String
     */
    public static String getNodeValue(Node node) {
        if (node == null) {
            return null;
        }
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE: // 1 该节点为 Element
                StringBuffer contents = new StringBuffer();
                NodeList childNodes = node.getChildNodes();
                int length = childNodes.getLength();
                if (length == 0) {
                    return null;
                }
                for (int i = 0; i < length; ++i) {
                    if (childNodes.item(i).getNodeType() == Node.TEXT_NODE) {
                        contents.append(childNodes.item(i).getNodeValue());
                    }
                }
                return contents.toString();
            case Node.ATTRIBUTE_NODE: // 2 该节点为 Attr
                return node.getNodeValue();
            case Node.TEXT_NODE: // 3 该节点为 Text 节点
                return node.getNodeValue();
        }
        return null;
    }

    /**
     * 根据提供的节点，取得其下xpath为xql的节点的值。
     *
     * @param node 要处理的节点
     * @return String
     */
    public static String getNodeValue(Node node, String xql) throws Exception {
        Node childNode = findNode(node, xql);
        if (childNode == null)
            return null;
        return getNodeValue(childNode);
    }

    /**
     * 根据提供的节点，取得其下xpath为xql的节点 。
     *
     * @param node
     * @param XPath
     * @return
     * @throws XmlException
     */
    public static Node findNode(Node node, String xql) throws XmlException {
        if ((xql == null) || (xql.length() == 0)) {
            throw new XmlException("the XPath: {0} is error");
        } else if (node == null) {
            throw new XmlException("the value is null");
        } else if (node.getNodeType() != Node.DOCUMENT_NODE) { // 9
            xql = processXql(xql);
        }
        return XPathAPI.selectSingleNode(node, xql);
    }

    public static final Node setNodeValue(Node node, String xql, String value)
            throws Exception {
        Node targetNode = findNode(node, xql);
        if (targetNode == null)
            targetNode = appendNode(node, xql);
        setNodeValue(targetNode, value);
        return targetNode;
    }

    public static void setNodeValue(Node node, String value) {
        if (node == null)
            return;
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE: // 1
                Node childNode = node.getFirstChild();
                if (childNode == null) {
                    childNode = node.getOwnerDocument().createTextNode(value);
                    node.appendChild(childNode);
                } else if (childNode.getNodeType() == 3) {
                    childNode.setNodeValue(value);
                } else {
                    node.appendChild(node.getOwnerDocument().createTextNode(value));
                }
                return;
            case Node.ATTRIBUTE_NODE: // 2
                node.setNodeValue(value);
                return;
            case Node.TEXT_NODE: // 3
                node.setNodeValue(value);
                return;
        }
    }

    public static final Node appendNode(Node node, String xql) throws Exception {
        if ((node == null) || (xql == null) || (xql.length() < 1))
            throw new Exception("the value is null");

        String[] saXql = StringUtil.split(xql, "/");
        if (saXql.length < 1) {
            String[] xpath = {xql};
            throw new Exception("the XPath: {0} is error, xpath = " + xpath);
        }

        Document doc = getOwnerDocument(node);

        Node root = getRoot(node, xql);
        if (root == null)
            throw new Exception("the root node is null");

        Node item = root;
        for (int i = 0; i < saXql.length; ++i) {
            String itemXql = subXql(saXql, i);
            item = findNode(root, itemXql);

            if ((item == null) || (i == saXql.length - 1)) {
                Node parentNode = findNode(root, subXql(saXql, i - 1));
                if (saXql[i].startsWith("@")) {
                    String attrName = saXql[i].substring(1, saXql[i].length());
                    if (parentNode == null) {
                        ((Element) root).setAttribute(attrName, "");
                        item = ((Element) root).getAttributeNode(attrName);
                    } else {
                        ((Element) parentNode).setAttribute(attrName, "");
                        item = ((Element) parentNode)
                                .getAttributeNode(attrName);
                    }
                } else {
                    item = createNode(doc, saXql[i]);
                    if (parentNode == null)
                        root.appendChild(item);
                    else
                        parentNode.appendChild(item);
                }
            }
        }
        return item;
    }

    public static Document getOwnerDocument(Node node) {
        Document doc = null;
        if (node.getNodeType() == Node.DOCUMENT_NODE)
            doc = (Document) node;
        else
            doc = node.getOwnerDocument();

        return doc;
    }

    private static Node getRoot(Node node, String xql) throws Exception {
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            String subXPath = xql;
            if (subXPath.startsWith(".".concat("/")))
                subXPath = subXPath.substring(2);
            else if (subXPath.startsWith("/")) {
                subXPath = subXPath.substring(1);
            }

            String[] xpath = StringUtil.split(subXPath, "/");
            if (xpath.length <= 1)
                throw new Exception("Can't append a Root Node in Document"
                        .concat(" : ").concat(xql));
            subXPath = xpath[0];

            Element docElement = getOwnerDocument(node).getDocumentElement();
            if (docElement.getNodeName().equals(subXPath))
                return node;

            throw new Exception("the root node is null".concat(" : ").concat(
                    subXPath));
        }
        return node;
    }

    protected static Element createNode(Document doc, String path) {
        StringTokenizer stk = new StringTokenizer(path, "[".concat("]").concat(
                "@").concat("\"").concat("'"));

        String eleName = null;
        String eleAttrName = null;
        String eleAttrVal = null;

        if (stk.hasMoreTokens())
            eleName = stk.nextToken();

        if (eleName == null)
            return null;
        Element retElement = doc.createElement(eleName);

        while (stk.hasMoreTokens()) {
            eleAttrName = stk.nextToken();
            int index = eleAttrName.lastIndexOf("=");
            if (index >= 0)
                eleAttrName = eleAttrName.substring(0, index);

            if (stk.hasMoreTokens())
                eleAttrVal = stk.nextToken();
            if (eleAttrName != null)
                retElement.setAttribute(eleAttrName, eleAttrVal);
        }

        return retElement;
    }

    private static final String processXql(String xql) {
        if ((xql.length() > 1) && (xql.charAt(0) == '/'))
            xql = ".".concat(xql);
        return xql;
    }

    private static final String subXql(String[] saXql, int index) {
        String subXql = "";
        for (int i = 0; i <= index; ++i) {
            if (i >= saXql.length)
                break;
            if (subXql != "")
                subXql = subXql.concat("/").concat(saXql[i]);
            else
                subXql = subXql.concat(saXql[i]);
        }
        return subXql;
    }

    public static final String node2String(Node node, boolean isPreserveSpace) {
        if (node == null)
            return null;
        if (node.getNodeType() == 9)
            node = ((Document) node).getDocumentElement();

        OutputFormat format = new OutputFormat(node.getOwnerDocument());

        String strEncoding = System.getProperty("xml.encoding");
        if (strEncoding != null)
            format.setEncoding(strEncoding);
        else
            format.setEncoding("GB2312");

        format.setIndenting(false);

        format.setPreserveSpace(isPreserveSpace);
        StringWriter stringOut = new StringWriter();
        XMLSerializer serial = new XMLSerializer(stringOut, format);
        try {
            serial.asDOMSerializer();
            serial.serialize((Element) node);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stringOut.toString();
    }

    public static final String node2String(Node node) {
        return node2String(node, true);
    }

}
