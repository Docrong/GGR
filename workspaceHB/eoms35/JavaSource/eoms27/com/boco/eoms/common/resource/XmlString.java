package com.boco.eoms.common.resource;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-27
 * Time: 18:26:08
 * To change this template use File | Settings | File Templates.
 */
public class XmlString {
    private String nodeHead;
    private String nodeFoot;

    public StringBuffer getNodeString(String nodeString) {
        String startNode = startNode(nodeString);
        String endNode = endNode(nodeString);
        int start = xml.indexOf(startNode);
        nodeHead = xml.substring(0, start);
        int end = xml.indexOf(endNode) + endNode.length();
        nodeFoot = xml.substring(end, xml.length());
        return new StringBuffer(xml.substring(start, end));
    }

    public String getNodeHead() {
        return nodeHead;
    }

    public String getNodeFoot() {
        return nodeFoot;
    }

    public XmlString(StringBuffer strXml) {
        this.xml = strXml;
        this.nodeHead = "";
        this.nodeFoot = "";
    }

    public XmlString(String strXml) {
        this.xml = new StringBuffer(strXml);
        this.nodeHead = "";
        this.nodeFoot = "";
    }

    public void setXml(StringBuffer xml) {
        this.xml = xml;
        this.nodeHead = "";
        this.nodeFoot = "";
    }

    public StringBuffer getXml() {
        return xml;
    }

    private String blankNode(String nodeString) {
        return "<" + nodeString + "/>";
    }

    private String startNode(String nodeString) {
        return "<" + nodeString + ">";
    }

    private String endNode(String nodeString) {
        return "</" + nodeString + ">";
    }

    private StringBuffer xml = new StringBuffer();

    public void setValue(String nodeString, String value) {
        int start = 0;
        int end = 0;
        String blankNode = blankNode(nodeString);
        start = xml.indexOf(blankNode);
        if (start == -1) return;
        end = start + blankNode.length();
        xml.replace(start, end, startNode(nodeString) + value + endNode(nodeString));
    }

    public void modifyValue(String nodeString, String value) {
        int start = 0;
        int end = 0;
        String startNode = startNode(nodeString);
        String endNode = endNode(nodeString);
        start = xml.indexOf(startNode);
        if (start == -1) return;
        end = xml.indexOf(endNode);
        if (end == -1) return;
        end += endNode.length();
        xml.replace(start, end, startNode + value + endNode);
    }

}
