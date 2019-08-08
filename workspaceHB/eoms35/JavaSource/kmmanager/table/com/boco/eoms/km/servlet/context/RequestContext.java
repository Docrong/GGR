package com.boco.eoms.km.servlet.context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.boco.eoms.km.core.crimson.jaxp.XmlException;
import com.boco.eoms.km.core.crimson.util.XmlUtil;
import com.boco.eoms.km.servlet.Constants;

public class RequestContext extends Context {

    private static final long serialVersionUID = 1L;

    public RequestContext() {
        super(Constants.XPATH_CONTEXT_ROOT);
    }

    public RequestContext(Document doc) {
        super(Constants.XPATH_CONTEXT_ROOT);
        this.doc = doc;
    }

    public RequestContext(String filePath) {
        super(Constants.XPATH_CONTEXT_ROOT, filePath);
    }

    public RequestContext(Node node) throws Exception {
        super(Constants.XPATH_CONTEXT_ROOT);
        Node root = XmlUtil.findNode(this.doc, Constants.XPATH_CONTEXT_ROOT);
        root.appendChild(this.doc.createElement("data"));
        Node childNode = this.doc.importNode(node, true);
        Node findNode = XmlUtil.findNode(this.doc,
                Constants.XPATH_CONTEXT_ABS_DATA);
        findNode.appendChild(childNode);
    }

    public void setEntityValue2(String xpath, String value) {
        try {
            String aXpath = this.getEntityPath(xpath);
            super.setValue2(aXpath, value, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEntityValues(String xpath, String[] values) {
        try {
            this.setValues(xpath, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setValues(String xpath, String[] values) throws Exception {
        for (int i = 0; i < values.length; ++i) {
            super.setValue(xpath, values[i], false);
        }
    }

    private String getEntityPath(String xpath) {
        if (xpath == null)
            return null;
        else if (xpath.startsWith("/"))
            xpath = Constants.XPATH_CONTEXT_DATA + xpath;
        else
            xpath = Constants.XPATH_CONTEXT_DATA + "/" + xpath;
        return xpath;
    }

    public Element getTableInfo() {
        try {
            return (Element) XmlUtil.findNode(this.doc, Constants.XPATH_CONTEXT_ABS_TABLE_INFO);
        } catch (XmlException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Element getPageCond() {
        try {
            return (Element) XmlUtil.findNode(this.doc, Constants.XPATH_CONTEXT_ABS_PAGE_COND);
        } catch (XmlException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Element getQueryCond() {
        try {
            return (Element) XmlUtil.findNode(this.doc, Constants.XPATH_CONTEXT_ABS_QUERY_COND);
        } catch (XmlException e) {
            e.printStackTrace();
            return null;
        }
    }
}