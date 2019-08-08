package com.boco.eoms.km.interfaces.webservice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;

import sun.misc.BASE64Encoder;

import com.boco.eoms.km.interfaces.util.Dom4jMethod;
import com.boco.eoms.km.interfaces.util.KmInterMethod;
import com.boco.eoms.km.interfaces.util.KmInterProps;

public class KnowledgeSendService {
    private String url = "http://127.0.0.1:9080/eoms35";

    public KnowledgeSendService() {
        Properties prop = KmInterProps.getConfigure("defaultconfig.properties");
        url = prop.getProperty("url");
    }

    public String saveXmlValue(String xmlString) {
        System.out.println("==========接口调用开始==========");
        System.out.println("=====接口输入=====");
        System.out.println(xmlString);

        Document doc = Dom4jMethod.buildDocument(xmlString);
        String fileName = System.currentTimeMillis() + ".kminter";
        File fileSever = new File("");
        File file = new File(fileSever.getAbsolutePath() + "/kmInterFile");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("鍒涘缓" + file.getAbsolutePath() + "鐩綍ok锛�");
            } else {
                System.out.println("鍒涘缓" + file.getAbsolutePath() + "鐩綍fail锛�");
            }
        }
        String filePath = file.getAbsolutePath() + "/" + fileName;
        // 读取:得到用户id
        Element root = doc.getRootElement();
        Element message = root.element("message");
        Element userIdEle = message.element("userId");
        String userId = userIdEle.getText();

        try {
            KmInterMethod.saveFile(xmlString, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }


        String result = url + "/kmmanager/kmContentsInterface.do?method=addKnowledge&type=interface&interface=interface&userName=" + userId + "&xmlFile=" + fileName;
        System.out.println("=====接口输出=====");
        System.out.println(result);

        return result;
    }

    public String searchXmlValue(String xmlString) {
        System.out.println("==========接口调用开始==========");
        System.out.println("=====接口输入=====");
        System.out.println(xmlString);
        String result = "";
        Properties prop = KmInterProps.getConfigure("defaultconfig.properties");
        Document doc = Dom4jMethod.buildDocument(xmlString);
        String fileName = System.currentTimeMillis() + ".kminter";
        File fileSever = new File("");
        File file = new File(fileSever.getAbsolutePath() + "/kmInterFile");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("鍒涘缓" + file.getAbsolutePath() + "鐩綍ok锛�");
            } else {
                System.out.println("鍒涘缓" + file.getAbsolutePath() + "鐩綍fail锛�");
            }
        }
        String filePath = file.getAbsolutePath() + "/" + fileName;
        try {
            KmInterMethod.saveFile(xmlString, filePath);
            System.out.println("ssssssssssssss=====" + xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(filePath);
        Document document = Dom4jMethod.readDocument(filePath);
        Element root = document.getRootElement();

        Element message = root.element("message");
        // 读取:工单类型
        Element sheetNameEle = message.element("sheetName");
        // 读取:用户id
        Element userIdEle = message.element("userId");
        String userId = userIdEle.getText();
        String sheetName = sheetNameEle.getText();
        // 读取:基本信息
        Element detail = root.element("detail");
        Element main = detail.element("main");
        Iterator it = main.elementIterator();

        // 转换“新增故障工单知识”详细信息�
        if (sheetName.equals("CommonFault")) {
            String condition = "&sheetName=CommonFault";
            while (it.hasNext()) {
                Element el = (Element) it.next();
                String name = el.attributeValue("name");
                String value = el.attributeValue("value");
                if (name == null || name.equals("")) {
                    continue;
                } else if (name.equals("mainNetSortOne") && !value.equals("")) {
                    condition = condition + "&one=" + value;
                    continue;
                } else if (name.equals("mainNetSortTwo") && !value.equals("")) {
                    condition = condition + "&two=" + value;
                    continue;
                } else if (name.equals("mainNetSortThree") && !value.equals("")) {
                    condition = condition + "&three=" + value;
                    continue;
                }
            }
            String tableId = prop.getProperty(sheetName);
            result = url + "/kmmanager/kmContentsInterface.do?method=searchFaultKnowledge&type=interface&interface=interface&userName=" + userId + "&tableId="
                    + tableId
                    + condition;
        }
        // 转换“入故障工单遗留问题库”详细信息�
        else if (sheetName.equals("CommonFaultLeave")) {
            String condition = "&sheetName=CommonFaultLeave";
            while (it.hasNext()) {
                Element el = (Element) it.next();
                String name = el.attributeValue("name");
                String value = el.attributeValue("value");
                if (name == null || name.equals("")) {
                    continue;
                } else if (name.equals("mainNetSortOne") && !value.equals("")) {
                    condition = condition + "&one=" + value;
                    continue;
                } else if (name.equals("mainNetSortTwo") && !value.equals("")) {
                    condition = condition + "&two=" + value;
                    continue;
                } else if (name.equals("mainNetSortThree") && !value.equals("")) {
                    condition = condition + "&three=" + value;
                    continue;
                }
            }
            String tableId = prop.getProperty(sheetName);
            result = url + "/kmmanager/kmContentsInterface.do?method=searchFaultKnowledge&type=interface&interface=interface&userName=" + userId + "&tableId="
                    + tableId
                    + condition;
        }
        // 转换“新增投诉工单知识”详细信息�
        else if (sheetName.equals("complaint")) {
            String condition = "&sheetName=complaint";
            while (it.hasNext()) {
                Element el = (Element) it.next();
                String name = el.attributeValue("name");
                String value = el.attributeValue("value");
                if (name == null || name.equals("")) {
                    continue;
                } else if (name.equals("complaintType1") && !value.equals("")) {
                    condition = condition + "&one=" + value;
                    continue;
                } else if (name.equals("complaintType2") && !value.equals("")) {
                    condition = condition + "&two=" + value;
                    continue;
                } else if (name.equals("complaintType") && !value.equals("")) {
                    condition = condition + "&three=" + value;
                    continue;
                } else if (name.equals("complaintType4") && !value.equals("")) {
                    condition = condition + "&four=" + value;
                    continue;
                } else if (name.equals("complaintType5") && !value.equals("")) {
                    condition = condition + "&five=" + value;
                    continue;
                } else if (name.equals("complaintType6") && !value.equals("")) {
                    condition = condition + "&six=" + value;
                    continue;
                } else if (name.equals("complaintType7") && !value.equals("")) {
                    condition = condition + "&seven=" + value;
                    continue;
                }
            }
            String tableId = prop.getProperty(sheetName);
            result = url + "/kmmanager/kmContentsInterface.do?method=searchComplaintKnowledge&type=interface&interface=interface&userName=" + userId + "&tableId="
                    + tableId
                    + condition;
        }

        System.out.println("=====接口输出=====");
        System.out.println(result);
        return result;
    }

    public static void main(String args[]) {
        String xmlContents = "<a>dfdsfsdf</a>";
        KnowledgeSendService service = new KnowledgeSendService();
        service.saveXmlValue(xmlContents);
    }

}
