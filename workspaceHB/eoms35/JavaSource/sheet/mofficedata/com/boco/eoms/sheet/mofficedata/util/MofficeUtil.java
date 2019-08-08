package com.boco.eoms.sheet.mofficedata.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.xpath.XPathAPI;
import org.dom4j.DocumentHelper;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.util.AttachRef;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.accessories.util.AccessoriesUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.model.RecordInfo;

public class MofficeUtil {
    public static List getAttachRefFromXml(String xmlDoc) {
        if (xmlDoc == null || xmlDoc.length() == 0) {
            return null;
        }

        List resultlist = new ArrayList();
        NodeList UDSObjectList = null;
        Node UDSObject = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            if (xmlDoc.indexOf("?xml") <= 0) {
                xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
            }
            DocumentBuilder dc = dbf.newDocumentBuilder();
            Document doc = dc.parse(new InputSource(new StringReader(xmlDoc)));
            String xpath = "//attachRef/attachInfo";

            UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
            if (UDSObjectList.getLength() > 0) {
                for (int i = 0; i < UDSObjectList.getLength(); i++) {
                    UDSObject = UDSObjectList.item(i);

                    AttachRef attach = new AttachRef();
                    xpath = "attachName";

                    attach.setAttachName(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
                    BocoLog.info(MofficeUtil.class, XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild()
                            .getNodeValue());

                    xpath = "attachURL";
                    attach.setAttachURL(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
                    resultlist.add(attach);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultlist;
    }

    public static String getXmlFromMap(Map objectMap, String filePath, String nodePath) throws Exception {
        List chNameList = new ArrayList();
        List enNameList = new ArrayList();
        List contentList = new ArrayList();

        List extandList = new ArrayList();
        for (int i = 0; i < chNameList.size(); i++) {
            RecordInfo info = new RecordInfo();
            info.setFieldChName(StaticMethod.nullObject2String(chNameList.get(i)));
            info.setFieldEnName(StaticMethod.nullObject2String(enNameList.get(i)));
            info.setFieldContent(StaticMethod.nullObject2String(contentList.get(i)));
            extandList.add(info);
        }

        try {
            SAXBuilder dc = new SAXBuilder();
            org.jdom.Document doc = dc.build(new File(filePath));

            Element element = doc.getRootElement();
            element = element.getChild(nodePath);

            List recordInfoList = new ArrayList();

            List childList = new ArrayList();
            childList.add(objectMap);

            List list = element.getChildren();
            for (int k = 0; k < childList.size(); k++) {
                List fieldList = new ArrayList();
                Map childMap = (Map) childList.get(k);

                Map map = new HashMap();
                map.putAll(objectMap);
                map.putAll(childMap);

                for (int i = 0; i < list.size(); i++) {
                    Element node = (Element) list.get(i);
                    String interfaceCnName = node.getAttribute("interfaceCnName").getValue();
                    String interfaceEnName = node.getAttribute("interfaceEnName").getValue();
                    String columnName = node.getAttribute("columnName").getValue();

                    System.out.println("interfaceCnName=" + interfaceCnName);
                    System.out.println("interfaceEnName=" + interfaceEnName);
                    System.out.println("columnName=" + columnName);

                    String value = "";
                    if (columnName.length() > 0) {
                        Object obj = objectMap.get(columnName);
                        if (obj != null)
                            if (obj.getClass().isArray())
                                obj = ((Object[]) obj)[0];
                            else if (obj instanceof Date) {
                                String type = "yyyy-MM-dd HH:mm:ss";
                                if (node.getAttribute("type") != null)
                                    type = node.getAttribute("type").getValue();
                                SimpleDateFormat dateformat = new SimpleDateFormat(type);
                                value = dateformat.format(obj);
                            }
                        if ("".equals(value))
                            value = StaticMethod.nullObject2String(obj);
                    }

                    if (value.length() <= 0)
                        value = node.getAttribute("defauleValue").getValue();

                    if (value.length() > 0) {
                        if (node.getAttribute("dictNodePath") != null) {// 需要从xml文件转换数据字典
                            String dictNodePath = node.getAttribute("dictNodePath").getValue();
                            System.out.println("dictNodePath=" + dictNodePath);
                            value = getDictIdByInterfaceCode(dictNodePath, value);
                        }
                    }
                    RecordInfo info = new RecordInfo();
                    info.setFieldChName(interfaceCnName);
                    info.setFieldEnName(interfaceEnName);
                    info.setFieldContent(value);

                    fieldList.add(info);
                }
                if (extandList != null && extandList.size() > 0)
                    fieldList.addAll(extandList);
                recordInfoList.add(fieldList);
            }

            String opDetail = createOpDetailXml(recordInfoList);
            BocoLog.info(MofficeUtil.class, nodePath + " opDetail=" + opDetail);
            return opDetail;
        } catch (Exception err) {
            err.printStackTrace();
            throw new Exception("生成xml出错：" + err.getMessage());
        }

    }

    public static String getDictIdByInterfaceCode(String nodePath, String code) throws Exception {
        String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/crm/config/crm-config.xml");
        return getDictIdByInterfaceCode(filePath, nodePath, code);
    }

    public static String getDictIdByInterfaceCode(String filePath, String nodePath, String code) throws Exception {
        SAXBuilder dc = new SAXBuilder();
        org.jdom.Document doc = dc.build(new File(filePath));

        Element element = doc.getRootElement();
        element = element.getChild("dict");
        element = element.getChild(nodePath);
        if (element == null) {
            System.out.println("dict." + nodePath + " not find");
            return "";
        }

        List list = element.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Element node = (Element) list.get(i);
            String dict = node.getAttribute("dictid").getValue();
            String interfacecode = node.getAttribute("interfacecode").getValue();
            if (interfacecode.equals(code))
                return dict;
        }
        return "";
    }

    public static String createOpDetailXml(List recordInfoList) {
        org.dom4j.Document dom4jDoc = DocumentHelper.createDocument();
        org.dom4j.Element opDetailElement = dom4jDoc.addElement("opDetail");

        for (int i = 0; i < recordInfoList.size(); i++) {
            org.dom4j.Element recordInfo = opDetailElement.addElement("recordInfo");
            List fieldList = (List) recordInfoList.get(i);
            for (int j = 0; j < fieldList.size(); j++) {
                RecordInfo info = (RecordInfo) fieldList.get(j);
                addContentXML(recordInfo, info.getFieldChName(), info.getFieldEnName(), info.getFieldContent());
            }
        }

        String xml = dom4jDoc.asXML();
        String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        int index = xml.indexOf(opt);
        if (index >= 0) {
            xml = xml.substring(index + opt.length());
        }
        return xml;
    }

    private static void addContentXML(org.dom4j.Element recordInfo, String cnname, String name, Object object) {
        org.dom4j.Element filedInfo = recordInfo.addElement("fieldInfo");
        org.dom4j.Element fieldCnName = filedInfo.addElement("fieldChName");
        fieldCnName.setText(cnname);
        org.dom4j.Element fieldEnName = filedInfo.addElement("fieldEnName");
        fieldEnName.setText(name);
        org.dom4j.Element fieldContent = filedInfo.addElement("fieldContent");
        if ("AttachRef".equals(name) && !"".equals(StaticMethod.nullObject2String(object))) {
            String attachIds = StaticMethod.nullObject2String(object);
            attachIds = attachIds.replaceAll("'", "");

            org.dom4j.Element attachRefElement = fieldContent.addElement("attachRef");
            ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance()
                    .getBean("ItawCommonsAccessoriesManager");
            String uploadType = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("uploadType");
            if (uploadType == null || uploadType.endsWith("")) {
                uploadType = "http";
            }
            BocoLog.info(MofficeUtil.class, "=====uploadType===" + uploadType);
            String attacArray[] = attachIds.split(",");
            for (int i = 0; i < attacArray.length; i++) {
                String id = attacArray[i];
                TawCommonsAccessories tawCommonsAccessories = mgr.getSystemToOther(id, uploadType);
                if (tawCommonsAccessories != null && tawCommonsAccessories.getAccessoriesPath() != null) {
                    org.dom4j.Element attachInfoElement = attachRefElement.addElement("attachInfo");
                    org.dom4j.Element attachName = attachInfoElement.addElement("attachName");
                    attachName.setText(tawCommonsAccessories.getAccessoriesCnName());
                    org.dom4j.Element attachURL = attachInfoElement.addElement("attachURL");
                    String ftpurl = tawCommonsAccessories.getAccessoriesPath();
                    attachURL.setText(ftpurl);
                    org.dom4j.Element attachLength = attachInfoElement.addElement("attachLength");
                    attachLength.setText(String.valueOf(tawCommonsAccessories.getAccessoriesSize()));
                }
            }
        } else {
            if (object == null) {
                fieldContent.setText("");
            } else {
                fieldContent.setText(object.toString());
            }
        }

    }

    public static String DownFile(String ftpurl, String appId) {
        String nameurl[] = ftpurl.split("/");
        String fileCnName;
        fileCnName = nameurl[nameurl.length - 1];
        String path = ftpurl;
        String ftpserver = "";
        String userLogin = "";
        String pwdLogin = "";
        String result = null;
        for (int i = 0; i < 3; i++)
            path = path.substring(path.indexOf("/") + 1);

        path = "/" + path;
        System.out.println("ftpurl=" + ftpurl + "  fileCnName=" + fileCnName + "  path=" + path);
        FTPClient fc = new FTPClient();
        ftpserver = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("ftp.ip"));
        userLogin = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("ftp.username"));
        pwdLogin = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("ftp.password"));
        try {
            fc.connect(ftpserver);
            fc.login(userLogin, pwdLogin);
            fc.setBufferSize(1024 * 10);
            fc.setFileType(fc.BINARY_FILE_TYPE);
            InputStream inStream = fc.retrieveFileStream(new String(path.getBytes("gbk"), "iso8859-1"));
            ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance()
                    .getBean("ItawCommonsAccessoriesManager");
            String upPath = mgr.getFilePath(appId);
            AccessoriesUtil.createFile(upPath, "/");
            System.out.println("*****DownFile:upPath=" + upPath);
            File file = new File(upPath);
            if (!file.exists()) {
                file.mkdir();
            }
            String fileName = StaticMethod
                    .getCurrentDateTime("yyyyMMddHHmmss") + randomKey(4) + fileCnName.substring(fileCnName.indexOf("."));
            File file_out = new File(upPath + "/" + fileName);
            System.out.println("*****DownFile:fileName=" + fileName);
            if (!file_out.exists()) {
                boolean isBoolean = file_out.createNewFile();
                if (isBoolean) {
                    FileOutputStream os = new FileOutputStream(file_out);
                    byte[] bytes = new byte[1024];
                    int c;
                    while ((c = inStream.read(bytes)) != -1) {
                        os.write(bytes, 0, c);
                    }
                    os.close();
//					 附件信息入库
                    TawCommonsAccessories accessories = new TawCommonsAccessories();
                    accessories.setAccessoriesCnName(fileCnName);
                    // 获取附件的相对路径
                    // 获取文件保存路径
                    String rootFilePath = AccessoriesMgrLocator.getAccessoriesAttributes()
                            .getUploadPath();
                    System.out.println("*****DownFile:rootFilePath=" + rootFilePath);
                    String fileEnd = mgr.getFilePath(appId).substring(mgr.getFilePath(appId).indexOf(rootFilePath) + rootFilePath.length());
                    System.out.println("*****DownFile:fileEnd=" + fileEnd);
                    accessories.setAccessoriesPath(fileEnd);
                    File file1 = new File(upPath);
                    File systemFile = new File(file1, fileName);
                    accessories.setAccessoriesSize(systemFile.length());
                    accessories.setAccessoriesUploadTime(StaticMethod.getLocalTime());
                    accessories.setAccessoriesEnName(fileName);
                    accessories.setAccessoriesName(fileName);
                    accessories.setAppCode(appId);
                    mgr.saveTawCommonsAccessories(accessories);
                    result = fileName;
                    System.out.println("*****DownFile:result=" + result);
                }
            }
            inStream.close();
            fc.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String randomKey(int sLen) {
        String base;
        String temp;
        int i;
        int p;

        base = "1234567890";
        temp = "";
        for (i = 0; i < sLen; i++) {
            p = (int) (Math.random() * 10);
            temp += base.substring(p, p + 1);
        }
        return (temp);
    }

    public static String encodeUrl(String str, String encoding) {
        String rstr = new String();
        int start = 0;
        int i = 0;
        try {
            while (i < str.length()) {
                if (str.charAt(i) == '/' ||
                        str.charAt(i) == ':' ||
                        str.charAt(i) == '(' ||
                        str.charAt(i) == ')' ||
                        str.charAt(i) == '!' ||
                        str.charAt(i) == '@' ||
                        str.charAt(i) == '#' ||
                        str.charAt(i) == '$' ||
                        str.charAt(i) == '*') {
                    rstr += java.net.URLEncoder.encode(str.substring(start, i), encoding);
                    rstr += str.charAt(i);
                    start = i + 1;
                }
                i++;
            }
            if (start < str.length())
                rstr += java.net.URLEncoder.encode(str.substring(start), encoding);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rstr;
    }
}
