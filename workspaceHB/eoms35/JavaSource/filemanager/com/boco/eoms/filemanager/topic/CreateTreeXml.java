package com.boco.eoms.filemanager.topic;

import com.boco.eoms.common.resource.XmlUtil;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnectionPool;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��ѯ��ݿ���� tree xml
 *
 * @author liqifei
 * @ created    2003��9��18��
 */
public class CreateTreeXml {

    private String m_strXml = "temp.xml";
    private String m_strXmlHtml = "";
    public static final int TOPIC_TYPE_KPI_ID=1;
    public static final String TOPIC_TYPE_KPI_NAME="数据上报";
    Connection m_con = ConnectionPool.getInstance().getConnection();
    XmlUtil xml;
    private Document docXml;
    /**
     * ���캯��
     *
     * @ param String strXmlPath ���xml�ļ�
     */
    public CreateTreeXml(String strXml) {
        m_strXml = strXml;
    }

    public CreateTreeXml() {
    }

    /**
     * ִ�г���
     *
     * @return int
     *         0   ��ʾʧ��
     *         1   ��ʾ�ɹ�
     */
    public int executeProcess() throws Exception {
        int nReturn = 0;
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domBuilder = domFactory.newDocumentBuilder();

            docXml = domBuilder.newDocument();
            //**************************************************
            //��ѯ��ݿ����Document
            //test �õ�xml document

            Element rootElement = docXml.createElement("infoTree");
            rootElement.setAttribute("treeImageBase", "../../images");
            rootElement.setAttribute("states", "open");
            rootElement.setAttribute("allExp", "block");
            docXml.appendChild(rootElement);
            Element rowElement = docXml.createElement("node");
            setAttr((Element) rowElement, "0", "-1", TOPIC_TYPE_KPI_NAME, TOPIC_TYPE_KPI_NAME, "1", "Topic", "-1", "Topic", "root", "0", "topic","-1000","");
            LoadNodeChild(rowElement, docXml);
            rootElement.appendChild(rowElement);
             m_strXmlHtml = XmlUtil.toString(docXml.getDocumentElement());
            saveXML("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+m_strXmlHtml,m_strXml);
            this.closeDbcon();

            nReturn = 1;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
        return nReturn;
    }

    /**
     * �ݹ���ã���ѯ��ݿ����document��
     *
     * @param parNode
     * @param objDom
     * @throws Exception
     */
    public void LoadNodeChild(Node parNode, Document objDom) throws Exception {
        ResultSet RS = null;
        Node newNode = null;
        int n;

        if (parNode != null) {
            n = 0;
            try {
                Statement stmt = m_con.createStatement();
                RS = stmt.executeQuery(getChildTopicSQL(((Element) parNode).getAttribute("ID")));
                if (RS != null) {
                    while (RS.next()) {
                        n = n + 1;
//Create a new node element
                        newNode = objDom.createElement("node");
//Add node to root element as child
                        parNode.appendChild(newNode);
//Set properties of the new just created node
                        setAttr((Element) newNode, RS.getString("topic_id"), ((Element) parNode).getAttribute("ID"), RS.getString("topic_name"), RS.getString("topic_memo"), RS.getString("topic_order"), RS.getString("topic_path"), RS.getString("class_id"), RS.getString("topic_path"), "", String.valueOf(Integer.parseInt(((Element) parNode).getAttribute("level")) + 1), "region",RS.getString("topic_type"), RS.getString("topic_type_name"));
                        LoadNodeChild(newNode, objDom);
                    }

                    if (Integer.parseInt(((Element) parNode).getAttribute("ID")) == 0) {
                        ((Element) parNode).setAttribute("nodeType", "root");
                    } else if ((n == 0) && (Integer.parseInt(((Element) parNode).getAttribute("level")) != 1)) {
                        ((Element) parNode).setAttribute("nodeType", "node");
                    } else {
                        ((Element) parNode).setAttribute("nodeType", "node");
                    }
                }
                RS.close();
                if (stmt != null) stmt.close();
                newNode = null;
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    /**
     * �ݹ���ã���ѯ��ݿ����document��
     *
     * @param parNode
     * @param objDom
     * @throws Exception
     */
    public void LoadNodeChildFile(Node parNode, Document objDom) throws Exception {
        ResultSet RS = null;
        Node newNode = null;
        int n;

        if (parNode != null) {
            n = 0;
            try {
                Statement stmt = m_con.createStatement();
                RS = stmt.executeQuery(getChildTopicSQL(((Element) parNode).getAttribute("ID")));
                if (RS != null) {
                    while (RS.next()) {
                        n = n + 1;
                        newNode = objDom.createElement("node");
                        parNode.appendChild(newNode);
                        String strTopic_path = RS.getString("topic_path");

                        setAttr((Element) newNode, RS.getString("topic_id"), ((Element) parNode).getAttribute("ID"), StaticMethod.dbNull2String(RS.getString("topic_name")), StaticMethod.dbNull2String(RS.getString("topic_memo")), RS.getString("topic_order"), strTopic_path, RS.getString("class_id"), strTopic_path, "", String.valueOf(Integer.parseInt(((Element) parNode).getAttribute("level")) + 1), "topic",RS.getString("topic_type"), StaticMethod.dbNull2String(RS.getString("topic_type_name")));
                        LoadNodeChildFile(newNode, objDom);
                    }

                    if (Integer.parseInt(((Element) parNode).getAttribute("ID")) == 0) {
                        ((Element) parNode).setAttribute("nodeType", "root");
                    } else if ((n == 0) && (Integer.parseInt(((Element) parNode).getAttribute("level")) != 1)) {
                        ((Element) parNode).setAttribute("nodeType", "leaf");
                    } else {
                        ((Element) parNode).setAttribute("nodeType", "node");
                    }
                }
                RS.close();
                if (stmt != null) stmt.close();
                newNode = null;
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    //��һ�����������
    public void setAttr(Element node, String id, String parentId, String name, String memo, String order, String folder, String classId, String topicFolder, String nodeType, String level, String appType,String topicType,String topicTypeName) {
        node.setAttribute("ID", id);
        node.setAttribute("region_id", id);
        node.setAttribute("parentId", parentId);
        node.setAttribute("name", name);
        node.setAttribute("memo", memo);
        node.setAttribute("order", order);
        node.setAttribute("folder", folder);
        node.setAttribute("classId", classId);
        node.setAttribute("topicFolder", topicFolder);
        node.setAttribute("nodeType", nodeType);
        node.setAttribute("level", level);
        node.setAttribute("appType", appType);
        node.setAttribute("topicType", topicType);
        node.setAttribute("topicTypeName", topicTypeName);
    }

    //�õ��ӽڵ��sql��
    public String getChildTopicSQL(String topicId) {
        return "SELECT * FROM taw_file_mgr_topic WHERE par_topic_id=" + topicId + " ORDER BY topic_order,topic_name";
    }

    //����
    public static void main(String[] args) {
        //���document
        try {
            CreateTreeXml objtest = new CreateTreeXml("D:/create.xml");

            objtest.executeProcess();

            //System.out.println("result is ok");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //�õ���ɵ�xml�ļ�
    public String gerResult() {

        String strReturn = m_strXmlHtml;
        /*if(m_buffer!=null)
        {
            //strReturn = m_buffer.toString();

            String strTemp = m_buffer.toString();
            //System.out.println();
            int nIndex = strTemp.indexOf("?>")+3;
            ////System.out.println("nIndex:"+nIndex);
if(nIndex>2)
{
                strReturn=strTemp.substring(nIndex);
}
        }
        ////System.out.println("strReturn:"+strReturn);
*/
        return strReturn;
    }

    //��Document ����ļ�
    public void saveXML(String  domXml, String savePath) throws Exception {
        try {
            xml.writeXml(domXml, savePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    //�ر����l��
    public void closeDbcon() {
        try {
            if (m_con != null) {
                m_con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * ���deptId�õ�Ȩ��
     *
     * @param topicXMLDoc The feature to be added to the Privilege attribute
     * @throws Exception Description of the Exception
     */
//    public void addPrivilege(Document topicXMLDoc, String deptId) throws Exception {
//
//        String TopicPrvlgSql = "SELECT topic_id,privilege FROM a_topic_typeprvlg WHERE ((user_grp_id is null) AND  (user_id is null)) OR (user_grp_id in (" + deptId + ") or user_grp_id='-1') ORDER BY privilege ,topic_id";
//        //ConnDB con = new ConnDB();
//        try {
//            //System.out.println("befroe exectute"+TopicPrvlgSql);
//            Statement stmt = m_con.createStatement();
//            ResultSet PrvlgRS = stmt.executeQuery(TopicPrvlgSql);
//            while (PrvlgRS.next()) {
//                Node topicNode = null;
//                topicNode = XPathAPI.selectSingleNode(topicXMLDoc, "//node[@ID='" + PrvlgRS.getString("topic_id") + "']");
//                if (topicNode != null) {
//                    ((Element) topicNode).setAttribute("vision", "true");
//                    ((Element) topicNode).setAttribute("privilege", PrvlgRS.getString("privilege"));
//                }
//            }
//            PrvlgRS.close();
//            if (stmt != null) stmt.close();
//        } catch (SQLException ex) {
//            ex.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            throw e;
//        }
//    }

}