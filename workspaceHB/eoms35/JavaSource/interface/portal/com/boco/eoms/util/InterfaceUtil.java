package com.boco.eoms.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.FileDownLoad;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
//import com.boco.eoms.commons.system.user.util.UserMgrLocator;
//import com.boco.eoms.commons.util.xml.XmlManage;
//import com.boco.eoms.crm.bo.CrmLoader;
//import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;


import org.apache.xpath.XPathAPI;

public class InterfaceUtil {
    public String getFilterRoles(java.util.List list) {
        String str = "0";

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
                tawSystemSubRole = (TawSystemSubRole) list.get(i);
                str = tawSystemSubRole.getId() + ",";
            }
            str = str.substring(0, str.length() - 1);
        }

        return str;

    }

    public HashMap xmlElements(String xmlDoc, HashMap map,
                               String opDetailFieldName) throws Exception {

        NodeList UDSObjectList = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            if (xmlDoc.indexOf("?xml") <= 0)
                xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
            DocumentBuilder dc = dbf.newDocumentBuilder();
            Document doc = dc.parse(new InputSource(new StringReader(xmlDoc)));

            String xpath = "//opDetail/recordInfo";

            UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
            if (UDSObjectList.getLength() > 1) {
                if (UDSObjectList.getLength() > 0) {
                    if (UDSObjectList.getLength() == 1) {
                        map = this.getMap(xpath, map, doc, opDetailFieldName);

                    } else {
                        xpath = "//opDetail/recordInfo/fieldInfo";
                        map = this.getMap(xpath, map, doc, opDetailFieldName);

                    }
                }
            } else {
                xpath = "//opDetail/recordInfo/fieldInfo";
                map = this.getMap(xpath, map, doc, opDetailFieldName);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return map;
    }

    public String getFieldValue(String key, HashMap map,
                                String OpDetailFieldName) {
        String fieldValue = "";
        OpDetail opDetail = new OpDetail();
        opDetail = (OpDetail) map.get(key);

        System.out.println("FieldChName:" + opDetail.getFieldChName());
        System.out.println("FieldContent:" + opDetail.getFieldContent());
        System.out.println("FieldEnName:" + opDetail.getFieldEnName());
        if (OpDetailFieldName.equals("FieldChName")) {
            fieldValue = opDetail.getFieldChName();
        } else if (OpDetailFieldName.equals("FieldContent")) {
            fieldValue = opDetail.getFieldContent();
        } else if (OpDetailFieldName.equals("FieldEnName")) {
            fieldValue = opDetail.getFieldEnName();
        }
        return fieldValue;
    }

    public HashMap getMap(String xpath, HashMap map, Document doc,
                          String opDetailFieldName) {
        NodeList UDSObjectList = null;
        Node UDSObject = null;
        String fieldValue = "";
        // xpath = "//opDetail/recordInfo/fieldInfo";
        try {
            UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
        } catch (TransformerException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (UDSObjectList.getLength() > 0) {
            for (int i = 0; i < UDSObjectList.getLength(); i++) {
                UDSObject = UDSObjectList.item(i);
                OpDetail opDetail = new OpDetail();
                xpath = "fieldChName";
                try {
                    if (XPathAPI.selectSingleNode(UDSObject, xpath)
                            .getFirstChild() != null) {
                        System.out.println("fieldChName:"
                                + XPathAPI.selectSingleNode(UDSObject, xpath)
                                .getFirstChild().getNodeValue());

                        opDetail.setFieldChName(XPathAPI.selectSingleNode(
                                UDSObject, xpath).getFirstChild()
                                .getNodeValue());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                xpath = "fieldEnName";
                try {
                    if (XPathAPI.selectSingleNode(UDSObject, xpath)
                            .getFirstChild() != null) {
                        System.out.println("fieldEnName:"
                                + XPathAPI.selectSingleNode(UDSObject, xpath)
                                .getFirstChild().getNodeValue());

                        opDetail.setFieldEnName(XPathAPI.selectSingleNode(
                                UDSObject, xpath).getFirstChild()
                                .getNodeValue());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                xpath = "fieldContent";
                try {
                    if (XPathAPI.selectSingleNode(UDSObject, xpath)
                            .getFirstChild() != null) {
                        System.out.println("fieldContent:"
                                + XPathAPI.selectSingleNode(UDSObject, xpath)
                                .getFirstChild().getNodeValue());
                        opDetail.setFieldContent(XPathAPI.selectSingleNode(
                                UDSObject, xpath).getFirstChild()
                                .getNodeValue());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (opDetailFieldName.equals("FieldChName")) {
                    fieldValue = opDetail.getFieldChName();
                } else if (opDetailFieldName.equals("FieldContent")) {
                    fieldValue = opDetail.getFieldContent();
                } else if (opDetailFieldName.equals("FieldEnName")) {
                    fieldValue = opDetail.getFieldEnName();
                }
                map.put(opDetail.getFieldEnName(), fieldValue);
            }

        }
        return map;
    }

    /**
     * @param xmlDoc
     * @return AttachRef
     */
    public List getAttachRefFromXml(String xmlDoc) {
        if (xmlDoc == null || xmlDoc.length() == 0)
            return null;
        List resultlist = new ArrayList();
        NodeList UDSObjectList = null;
        Node UDSObject = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            if (xmlDoc.indexOf("?xml") <= 0)
                xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
            DocumentBuilder dc = dbf.newDocumentBuilder();
            Document doc = dc.parse(new InputSource(new StringReader(xmlDoc)));
            String xpath = "//attachRef/attachInfo";

            UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
            if (UDSObjectList.getLength() > 0) {
                for (int i = 0; i < UDSObjectList.getLength(); i++) {
                    UDSObject = UDSObjectList.item(i);
                    AttachRef attach = new AttachRef();
                    xpath = "attachName";
                    attach.setAttachName(XPathAPI.selectSingleNode(UDSObject,
                            xpath).getFirstChild().getNodeValue());

                    xpath = "attachURL";
                    attach.setAttachURL(XPathAPI.selectSingleNode(UDSObject,
                            xpath).getFirstChild().getNodeValue());

                    xpath = "attachLength";
                    attach.setAttachLength(XPathAPI.selectSingleNode(UDSObject,
                            xpath).getFirstChild().getNodeValue());
                    resultlist.add(attach);
                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return resultlist;
    }

    public static String changeCharater(String xml) {
        if (xml.indexOf("&amp") < 0)
            xml = xml.replaceAll("&", "&amp");
        xml = xml.replaceAll("\"", "&quot");
        xml = xml.replaceAll("\"", "&quot");
        xml = xml.replaceAll("'", "&apos");
        xml = xml.replaceAll("<", "&lt");
        xml = xml.replaceAll(">", "&gt");

        return xml;
    }


    public String mapToXml(HashMap map) {
        String xml = "<opDetail>";
        Set filterSet = map.keySet();
        Iterator filterIt = filterSet.iterator();
        while (filterIt.hasNext()) {
            String key = StaticMethod.nullObject2String(filterIt.next());
            String value = StaticMethod.nullObject2String(map.get(key));
            xml += "<recordInfo><fieldInfo><fieldChName></fieldChName><fieldEnName>"
                    + key
                    + "</fieldEnName><fieldContent>"
                    + value
                    + "</fieldContent></fieldInfo></recordInfo>";
        }
        xml += "</opDetail>";
        return xml;
    }


    public void fileDownLoad(String url) {
        Thread downThread = new Thread(new FileDownLoad(url, BulletinMgrLocator.getAttributes().getBulletinAccessories()),
                "nThread");
        downThread.start();
        System.out.println("thread have gone,we will go on");
    }


    public String getLocalString() {
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
                "yyMMdd");
        String date = dateFormat.format(currentDate);

        return date;
    }

	/*
	<attachRef>
	<attachInfo>
		<attachName>a?|</attachName>
		<attachURL>a?|</attachURL>
		<attachLength>a?|</attachLength>
	</attachInfo>
	</attachRef>*/

    public String getAttachRef(String accessories) {
        String attachRef = "<attachRef>";
        if (accessories != null && !"".equals(accessories)) {
            if (accessories.indexOf(",") > 0) {
                String[] accessorieses = accessories.split(",");
                if (accessorieses.length > 0) {
                    for (int i = 0; i < accessorieses.length; i++) {
                        attachRef += "<attachInfo>"
                                + "<attachName>" + accessorieses[i] + "</attachName>"
                                + "<attachURL>" + BulletinMgrLocator.getAttributes().getBulletinAccessories() + "/" + accessorieses[i] + "</attachURL>"
                                + "<attachLength>2</attachLength>"
                                + "</attachInfo>";
                    }
                }
            } else {
                attachRef += "<attachInfo>"
                        + "<attachName>" + accessories + "</attachName>"
                        + "<attachURL>" + BulletinMgrLocator.getAttributes().getBulletinAccessories() + "/" + accessories + "</attachURL>"
                        + "<attachLength>2</attachLength>"
                        + "</attachInfo>";
            }
        }

        attachRef += "</attachRef>";


        return attachRef;

    }

    public String getOpDetail(
            com.boco.eoms.workbench.infopub.model.Thread thread) {
        String opDetail = "<opDetail><recordInfo>";// ÏêÏ¸ÐÅÏ¢

        if (thread != null) {
            opDetail += "<fieldInfo>" // ×Ö¶ÎÄÚÈÝ
                    + "<fieldChName>Ö÷Ìâ</fieldChName>" // ×Ö¶ÎÖÐÎÄÃû
                    + "<fieldEnName>title</fieldEnName>" // ×Ö¶ÎÓ¢ÎÄÃû
                    + "<fieldContent>"
                    + thread.getTitle()
                    + "</fieldContent>" // ×Ö¶ÎÄÚÈÝ
                    + "</fieldInfo>"
                    + "<fieldInfo>" // ×Ö¶ÎÄÚÈÝ
                    + "<fieldChName>½ô¼±³Ì¶È</fieldChName>" // ×Ö¶ÎÖÐÎÄÃû
                    + "<fieldEnName>urgentDegree</fieldEnName>" // ×Ö¶ÎÓ¢ÎÄÃû
                    + "<fieldContent>"
                    + thread.getThreadTypeName()
                    + "</fieldContent>" // ×Ö¶ÎÄÚÈÝ
                    + "</fieldInfo>"
                    + "<fieldInfo>" // ×Ö¶ÎÄÚÈÝ
                    + "<fieldChName>ÓÐÐ§ÆÚ</fieldChName>" // ×Ö¶ÎÖÐÎÄÃû
                    + "<fieldEnName>availTime</fieldEnName>" // ×Ö¶ÎÓ¢ÎÄÃû
                    + "<fieldContent>"
                    + thread.getValidity()
                    + "</fieldContent>" // ×Ö¶ÎÄÚÈÝ
                    + "</fieldInfo>"
                    + "<fieldInfo>"
                    + "<fieldChName>¹«¸æÄÚÈÝ</fieldChName>" // ×Ö¶ÎÖÐÎÄÃû
                    + "<fieldEnName>noticeDesc</fieldEnName>" // ×Ö¶ÎÓ¢ÎÄÃû
                    + "<fieldContent>"
                    + thread.getContent()
                    + "</fieldContent>" // ×Ö¶ÎÄÚÈÝ
                    + "</fieldInfo>"
                    + "<fieldInfo>"
                    + "<fieldChName>ÊÇ·ñÐèÒª»Ø¸´</fieldChName>" // ×Ö¶ÎÖÐÎÄÃû
                    + "<fieldEnName>isNeedReploy</fieldEnName>" // ×Ö¶ÎÓ¢ÎÄÃû
                    + "<fieldContent>" + thread.getReplyName()
                    + "</fieldContent>" // ×Ö¶ÎÄÚÈÝ
                    + "</fieldInfo></recordInfo>";

        }
        opDetail += "</opDetail>";

        System.out.println("opDetail:" + opDetail);
        return opDetail;
    }

    public String getOpDetail(ThreadHistory thread) {
        String opDetail = "<opDetail><recordInfo>";// ÏêÏ¸ÐÅÏ¢

        if (thread != null) {
            opDetail += "<fieldInfo>" // ×Ö¶ÎÄÚÈÝ
                    + "<fieldChName>»Ø¸´½á¹û</fieldChName>" // ×Ö¶ÎÖÐÎÄÃû
                    + "<fieldEnName>reployResult</fieldEnName>" // ×Ö¶ÎÓ¢ÎÄÃû
                    + "<fieldContent>"
                    + thread.getReplyresultName()
                    + "</fieldContent>" // ×Ö¶ÎÄÚÈÝ
                    + "</fieldInfo>"
                    + "<fieldInfo>" // ×Ö¶ÎÄÚÈÝ
                    + "<fieldChName>»Ø¸´Òâ¼û</fieldChName>" // ×Ö¶ÎÖÐÎÄÃû
                    + "<fieldEnName>reployDesc</fieldEnName>" // ×Ö¶ÎÓ¢ÎÄÃû
                    + "<fieldContent>"
                    + thread.getComments()
                    + "</fieldContent>" // ×Ö¶ÎÄÚÈÝ
                    + "</fieldInfo>" + "</recordInfo>";

        } else {
            opDetail += "</opDetail>";
        }
        System.out.println("opDetail:" + opDetail);
        return opDetail;
    }

    // SC-501-081111-10002
    public String getSerialNo() {
        String serialNo = BulletinMgrLocator.getAttributes()
                .getBulletinSerSupplier()
                + "-"
                + BulletinMgrLocator.getAttributes().getBulletinSerialNoType()
                + "-" + this.getLocalString() + "-";//
        IThreadManager msg = (IThreadManager) ApplicationContextHolder
                .getInstance().getBean("IthreadManager");
        String max = msg.getMaxSerialNo(serialNo);
        if (max != null && !"".equals(max)) {
            max.subSequence(max.length() - 5, max.length());
            int count = java.lang.Integer.parseInt(String.valueOf(max
                    .subSequence(max.length() - 5, max.length())));
            serialNo += String.valueOf(count + 1);
        }
        return serialNo;
    }

    public static void main(String[] args) {
        //
        // String xml = "<opDetail>" +
        // "<recordInfo>" +
        // "<fieldInfo>"
        // + "<fieldChName>zcx1</fieldChName>"
        // + "<fieldEnName>wangwei</fieldEnName>"
        // + "<fieldContent>1001???¡è</fieldContent>" +
        // "</fieldInfo>"
        // + "<fieldInfo>" +
        // "<fieldChName>zcx</fieldChName>"
        // + "<fieldEnName>80</fieldEnName>"
        // + "<fieldContent></fieldContent>" +
        // "</fieldInfo>"
        // + "</recordInfo>" +
        // "</opDetail>";
        //
        //
        //
        // xml =
        // "<opDetail><recordInfo><fieldInfo><fieldChName>???????</fieldChName><fieldEnName>alarmId</fieldEnName>"+
        // "<fieldContent>BOCO_WNMS_892717772_640202506_3081174526_2329122724</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????ID</fieldChName><fieldEnName>alarmStaId</fieldEnName><fieldContent>001-011-00-800064</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>?????</fieldChName><fieldEnName>oriAlarmId</fieldEnName><fieldContent>1097868</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmTitle</fieldEnName><fieldContent>DIGITAL
        // PATH QUALITY SUPERVISION</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmCreateTime</fieldEnName><fieldContent>2008-09-22
        // 18:23:00</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>NeType</fieldEnName><fieldContent>10101</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmVendor</fieldEnName><fieldContent>???</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>neName</fieldEnName><fieldContent>??BSCE1</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmLevel</fieldEnName><fieldContent>8</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmType</fieldEnName><fieldContent>?????</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmSubType</fieldEnName><fieldContent>??????</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmProvince</fieldEnName><fieldContent>???</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmRegion</fieldEnName><fieldContent>???</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmLocation</fieldEnName><fieldContent>90RBL2</fieldContent></fieldInfo>"+
        // "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmDetail</fieldEnName><fieldContent>????????:6"+
        // "????????:7"+
        // "????:"+
        // "%a"+
        // "-RecordId=1097868"+
        // "-SystemType=AXE"+
        // "-EventTime=20080922182300"+
        // "-ETtext=Quality of service"+
        // "-EventType=2"+
        // "-ObjectOfReference=SubNetwork=ONRM_RootMo,SubNetwork=AXE,ManagedElement=WHBSCE1"+
        // "-ProposedRepairAction=-2"+
        // "-ProblemText=*** ALARM 642 A3/APT \"WHBSCE1R12/GAP/\"U 080922 1823"+
        // ":DIGITAL PATH QUALITY SUPERVISION"+
        // ":SES"+
        // ":DIP DIPPART SESL2 QSV SECTION DATE TIME"+
        // ":90RBL2 1 11 080922 143303"+
        // ":END"+
        // "-ProblemData=PRCA=42"+
        // "-LoggingTime=20080922182419"+
        // "-PreviousSeverity=-1"+
        // "-FMXFlag=1"+
        // "-FMXGenerated=1"+
        // "-FmAlarmId=1097868"+
        // "%A</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>createType</fieldEnName><fieldContent>0</fieldContent></fieldInfo><fieldInfo><fieldChName>???</fieldChName><fieldEnName>sender</fieldEnName><fieldContent></fieldContent></fieldInfo></recordInfo></opDetail>";
        //
        //
        //
        // System.out.println("xml:" +"<?xml version=\"1.0\"
        // encoding=\"gb2312\"?>"+xml);
        // HashMap sheetMap = new HashMap();
        // sheetMap = doc.xmlElements(xml, sheetMap, "FieldContent");
        // System.out.println("zcx:" + sheetMap.get("80"));
        // System.out.println("zcx1:" + sheetMap.get("wangwei"));
        //
        // xml = "<opDetail>"+
        // "<recordInfo>"+
        // "<fieldInfo>" +
        // "<fieldChName>EOMS???¡ì???¡è?????¨¬??</fieldChName>" +
        // "<fieldEnName>userName</fieldEnName>" +
        // "<fieldContent>test11</fieldContent>" +
        // "</fieldInfo>" +
        //
        // "</recordInfo>" +
        // "<recordInfo>" +
        // "<fieldInfo>" +
        // "<fieldChName>EOMS???¡ì???¡è??£¤????</fieldChName>" +
        // "<fieldEnName>userPassword</fieldEnName>" +
        // "<fieldContent>111</fieldContent>" +
        // "</fieldInfo>" +
        // "</recordInfo>" +
        // "</opDetail>";
        //
        // sheetMap = new HashMap();
        // sheetMap=doc.xmlElements(xml,sheetMap,"FieldContent");
        // System.out.println("userName:"+sheetMap.get("userName"));
        //
        // System.out.println("userPassword:"+sheetMap.get("userPassword"));
        //
        // xml ="<attachRef>" + "<attachInfo>"
        // +"<attachName>¡§|?????</attachName>"
        // + "<attachURL>http://</attachURL>"
        // + "<attachLength>1001</attachLength>" +
        // "</attachInfo>"+
        // "</attachRef>";
        // List list = doc.getAttachRefFromXml(xml);
        // System.out.println(list.get(0).getClass().getName());
        //
        // try{
        // CrmLoader cd = new CrmLoader();
        // cd.load();
        //
        // EomsLoader loader = new EomsLoader();
        // loader.getCrmService("031", "001");
        //
        // boolean result=UserMgrLocator.getTawSystemUserMgr().getUser("test11",
        // "111");
        // System.out.println(""+result);
        // }catch(Exception err){
        // err.printStackTrace();
        // }
    }

}
