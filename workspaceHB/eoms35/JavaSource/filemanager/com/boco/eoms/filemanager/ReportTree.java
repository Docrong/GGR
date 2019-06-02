package com.boco.eoms.filemanager;

import com.boco.eoms.common.resource.XmlUtil;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnectionPool;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-7-13
 * Time: 15:47:04
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class ReportTree {
    public static final String ALL_TEMPLET_ID="-1000";
    private Document templetDoc = null;
    private Connection conn = ConnectionPool.getInstance().getConnection();

    public ReportTree(String templetFile) {
        try {
            templetDoc = XmlUtil.getDocument(templetFile);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getSchemeTreeXml(String states,String allExp) {
//       Node rootNode=templetDoc.getFirstChild();  //root
        String xpath=".";
        xpath= "//node";
        NodeList allLeafNode = null;
        try {
            allLeafNode = XPathAPI.selectNodeList(templetDoc,xpath);
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        appendScheme(allLeafNode);
        Element root=(Element)templetDoc.getFirstChild();
        root.setAttribute("states",states);
        root.setAttribute("allExp",allExp);
        return XmlUtil.toString(templetDoc.getFirstChild());
    }

    public String getReportTopicXml(String states,String allExp) {
//       Node rootNode=templetDoc.getFirstChild();  //root
//        String xpath=".";
//        xpath= "//node";
//        NodeList allLeafNode = null;
//        try {
//            allLeafNode = XPathAPI.selectNodeList(templetDoc,xpath);
//        } catch (TransformerException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        Element root=(Element)templetDoc.getFirstChild();
        root.setAttribute("states",states);
        root.setAttribute("allExp",allExp);
        root.setAttribute("treeImageBase","../images");
        return XmlUtil.toString(templetDoc.getFirstChild());
    }
        public String getReportcXml(String states,String allExp) {
       Node rootNode=templetDoc.getFirstChild();  //root
        String xpath=".";
        xpath= "//node";
        NodeList allLeafNode = null;
        try {
            allLeafNode = XPathAPI.selectNodeList(templetDoc,xpath);
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        appendReport(allLeafNode);
        Element root=(Element)templetDoc.getFirstChild();
        root.setAttribute("states",states);
        root.setAttribute("allExp",allExp);
        root.setAttribute("treeImageBase","../images");
        return XmlUtil.toString(templetDoc.getFirstChild());
    }
    void appendScheme(NodeList allLeafNode) {
        if (allLeafNode == null || allLeafNode.getLength() <= 0) return;
        String sql = "select topic_id, file_mgr_scheme_id ,title from taw_file_mgr_scheme order by topic_id";
        Statement stat = null;
        ResultSet rs = null;
        String topicId = "";
        Node leafNode = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            int order = 1;
            while (rs.next()) {
                String tmpTopicId = rs.getString("topic_id");    //��ȡר��id
                if (!topicId.equalsIgnoreCase(tmpTopicId)) {
                    topicId = tmpTopicId;
                    order = 0;
                    leafNode = getNode(tmpTopicId, allLeafNode);
                } else {
                    order++;
                }
                if (leafNode != null) {
                    Element ele = templetDoc.createElement("node");
                    ele.setAttribute("appType", "dept");
                    ele.setAttribute("ID", "scheme_" + rs.getString("file_mgr_scheme_id"));
                    ele.setAttribute("dept_id", rs.getString("file_mgr_scheme_id"));
                    ele.setAttribute("order", order + "");
                    ele.setAttribute("nodeType", "leaf");
                    ele.setAttribute("valueType", "scheme");
                    ele.setAttribute("parent_id", tmpTopicId);
                    ele.setAttribute("name", rs.getString("title"));
                    leafNode.appendChild(ele);
                    ((Element) leafNode).setAttribute("nodeType", "node");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    void appendReport(NodeList allLeafNode) {
        if (allLeafNode == null || allLeafNode.getLength() <= 0) return;
        String sql = "select report_id ,topic_id ,report_name  from taw_file_mgr_report order by topic_id";
        Statement stat = null;
        ResultSet rs = null;
        String topicId = "";
        Node leafNode = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            int order = 1;
            while (rs.next()) {
                String tmpTopicId = rs.getString("topic_id");
                if (!topicId.equalsIgnoreCase(tmpTopicId)) {
                    topicId = tmpTopicId;
                    order = 0;
                    leafNode = getNode(tmpTopicId, allLeafNode);
                } else {
                    order++;
                }
                if (leafNode != null) {
                    Element ele = templetDoc.createElement("node");
                    ele.setAttribute("appType","dept");
                    ele.setAttribute("dept_id", rs.getString("report_id"));
                    ele.setAttribute("ID", "report"+rs.getString("report_id"));
                    ele.setAttribute("order", order + "");
                    ele.setAttribute("nodeType", "leaf");
                    ele.setAttribute("valueType", "report");
                    ele.setAttribute("name", rs.getString("report_name")==null?"":rs.getString("report_name").trim());
                    leafNode.appendChild(ele);
                    ((Element) leafNode).setAttribute("nodeType", "node");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private Node getNode(String id, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element ele = (Element) nodeList.item(i);
            if (id.equalsIgnoreCase(ele.getAttribute("ID")))
                return nodeList.item(i);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
