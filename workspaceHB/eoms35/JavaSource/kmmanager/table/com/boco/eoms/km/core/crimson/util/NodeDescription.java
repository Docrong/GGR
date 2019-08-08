package com.boco.eoms.km.core.crimson.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class NodeDescription {

    public static final short NODE_ELEMENT = 0;
    public static final short NODE_ATTR = 1;
    public static final short NODE_ANY = 2;
    private String m_strNodeName;
    private ArrayList m_alAttrs;
    private short m_type;

    class Pair {
        public String first;
        public String second;

        public Pair(String strFirst, String strSecond) {
            first = strFirst;
            second = strSecond;
        }
    }

    public NodeDescription(String xPathItem) {
        m_strNodeName = "";
        m_alAttrs = new ArrayList();
        m_type = 0;
        if (xPathItem.indexOf("*") == 0)
            m_type = 2;
        else if (xPathItem.indexOf("@") == 0) {
            m_strNodeName = "";
            m_type = 1;
            StringTokenizer stk = new StringTokenizer(xPathItem, "@");
            if (stk.hasMoreTokens())
                m_alAttrs.add(new Pair(stk.nextToken(), ""));
        } else {
            m_type = 0;
            StringTokenizer stk = new StringTokenizer(xPathItem, "[@=\"']");
            if (stk.hasMoreTokens())
                m_strNodeName = stk.nextToken();
            String strAttrName;
            String strAttrVal;
            for (; stk.hasMoreTokens(); m_alAttrs.add(new Pair(strAttrName,
                    strAttrVal))) {
                strAttrName = stk.hasMoreTokens() ? stk.nextToken() : "";
                strAttrVal = stk.hasMoreTokens() ? stk.nextToken() : "";
            }

        }
    }

    public boolean equals(Element ele) {
        if (ele == null)
            return false;
        if (".".equals(m_strNodeName))
            return true;
        switch (m_type) {
            case 0:
                if (ele.getNodeName() == null)
                    return false;
                if (!ele.getNodeName().equals(m_strNodeName))
                    return false;
                for (int i = 0; i < m_alAttrs.size(); i++) {
                    String strAttrName = ((Pair) m_alAttrs.get(i)).first;
                    String strAttrVal = ((Pair) m_alAttrs.get(i)).second;
                    if (strAttrVal.equals(""))
                        return ele.hasAttribute(strAttrName);
                    if (!strAttrVal.equals(ele.getAttribute(strAttrName)))
                        return false;
                }
                break;
            case 1:
                String strAttrName = ((Pair) m_alAttrs.get(0)).first;
                if (!ele.hasAttribute(strAttrName))
                    return false;
                break;
            case 2:
            default:
                break;
        }
        return true;
    }

    public short getType() {
        return m_type;
    }

    public Attr getAttribute(Element ele) {
        String strAttrName = ((Pair) m_alAttrs.get(0)).first;
        return ele.getAttributeNode(strAttrName);
    }
}