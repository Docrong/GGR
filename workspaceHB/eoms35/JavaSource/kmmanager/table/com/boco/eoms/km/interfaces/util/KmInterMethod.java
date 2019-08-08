package com.boco.eoms.km.interfaces.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class KmInterMethod {

    public static Map getContentsMap(String filePath) {
        Map map = new HashMap();
        try {
            Properties prop = KmInterProps.getConfigure("defaultconfig.properties");

            Document doc = Dom4jMethod.readDocument(filePath);
            Element root = doc.getRootElement();

            // 读取:基本信息
            Element message = root.element("message");

            // 读取:工单类型
            Element sheetNameEle = message.element("sheetName");
            String sheetName = sheetNameEle.getText();
            map.put("sheetName", sheetName);

            // 读取:模型ID
            String TABLE_ID = prop.getProperty(sheetName);
            if (TABLE_ID != null && !TABLE_ID.equals("")) {
                map.put("TABLE_ID", TABLE_ID);

                // 读取:用户ID
                String userIdKey = prop.getProperty(sheetNameEle + ".userId");
                if (userIdKey != null && !userIdKey.equals("")) {
                    Element userId = message.element("userId");
                    map.put(userIdKey, userId.getText());
                }

                // 读取:工单ID
                String sheetIdKey = prop.getProperty("sheetId");
                if (sheetIdKey != null && !sheetIdKey.equals("")) {
                    Element sheetId = message.element(sheetNameEle + ".sheetId");
                    map.put(sheetIdKey, sheetId.getText());
                }

                // 读取工单主表详细信息
                Element main = root.element("detail").element("main");

                // 转换“新增故障工单知识”详细信息
                if (sheetName.equals("CommonFault")) {
                    map.putAll(getCommonFaultMain(sheetName, prop, main));
                }
                // 转换“入故障工单遗留问题库”详细信息
                else if (sheetName.equals("CommonFaultLeave")) {
                    map.putAll(getCommonFaultLeaveMain(sheetName, prop, main));
                }
                // 转换“新增投诉工单知识”详细信息
                else if (sheetName.equals("complaint")) {
                    map.putAll(getComplaintMain(sheetName, prop, main));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 转换“新增故障工单知识”详细信息
     *
     * @return Map
     */
    public static Map getCommonFaultMain(String sheetName, Properties prop,
                                         Element main) {
        Map map = new HashMap();
        Iterator it = main.elementIterator();
        String theme_id = "";
        while (it.hasNext()) {
            Element el = (Element) it.next();
            String name = el.attributeValue("name");
            String value = el.attributeValue("value");
            if (name == null || name.equals("") || value.equals("")) {
                continue;
            }
            String key = prop.getProperty(sheetName + "." + name);
            if (key == null || key.equals("")) {
                continue;
            }
            map.put(key, value); // 存入属性
        }
        if (map.get(prop.getProperty(sheetName + "." + "mainNetSortThree")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "mainNetSortThree")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "mainNetSortTwo")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "mainNetSortTwo")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "mainNetSortOne")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "mainNetSortOne")).toString();
        }
        String transStr = prop.getProperty(sheetName + ".mainNetSortTrans");
        theme_id = getTransNode(theme_id, transStr, 0);
        map.put("THEME_ID", theme_id);
        return map;
    }

    /**
     * 转换“入故障工单遗留问题库”详细信息
     *
     * @return Map
     */
    public static Map getCommonFaultLeaveMain(String sheetName, Properties prop, Element main) {
        Map map = new HashMap();
        Iterator it = main.elementIterator();
        String theme_id = "";
        while (it.hasNext()) {
            Element el = (Element) it.next();
            String name = el.attributeValue("name");
            String value = el.attributeValue("value");
            if (name == null || name.equals("") || value.equals("")) {
                continue;
            }
            String key = prop.getProperty(sheetName + "." + name);
            map.put(key, value); // 存入属性
        }
        if (map.get(prop.getProperty(sheetName + "." + "mainNetSortThree")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "mainNetSortThree")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "mainNetSortTwo")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "mainNetSortTwo")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "mainNetSortOne")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "mainNetSortOne")).toString();
        }
        String transStr = prop.getProperty(sheetName + ".mainNetSortTrans");
        theme_id = getTransNode(theme_id, transStr, 0);
        map.put("THEME_ID", theme_id);
        return map;
    }

    /**
     * 转换“新增投诉工单知识”详细信息
     *
     * @return Map
     */
    public static Map getComplaintMain(String sheetName, Properties prop, Element main) {
        Map map = new HashMap();
        Iterator it = main.elementIterator();
        String theme_id = "";
        while (it.hasNext()) {
            Element el = (Element) it.next();
            String name = el.attributeValue("name");
            String value = el.attributeValue("value");
            if (name == null || name.equals("") || value.equals("")) {
                continue;
            }

            String key = prop.getProperty(sheetName + "." + name);
            map.put(key, value); // 存入属性
        }
        if (map.get(prop.getProperty(sheetName + "." + "complaintType7")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "complaintType7")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "complaintType6")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "complaintType6")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "complaintType5")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "complaintType5")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "complaintType4")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "complaintType4")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "complaintType")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "complaintType")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "complaintType2")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "complaintType2")).toString();
        } else if (map.get(prop.getProperty(sheetName + "." + "complaintType1")) != null) {
            theme_id = map.get(prop.getProperty(sheetName + "." + "complaintType1")).toString();
        }
        String transStr = prop.getProperty(sheetName + ".complaintTypeTrans");
        theme_id = getTransNode(theme_id, transStr, 0);
        map.put("THEME_ID", theme_id);
        return map;
    }


    /**
     * 父节点映射转换
     *
     * @return String
     */
    public static String getTransNode(String node, String transStr, int flag) {
        String[] transStrs = transStr.split(":");
        String firstStr = transStrs[0];
        String lastStr = transStrs[1];
        if (flag == 1) {
            firstStr = transStrs[1];
            lastStr = transStrs[0];
        }
        node = node.substring(firstStr.length());
        node = lastStr + node;

        return node;
    }


    public static File saveFile(String fileContent, String filePath)
            throws IOException {
        if (fileContent == null) {
            return null;
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String picBinary = encoder.encode(fileContent.getBytes("gb2312"));
        File file = new File(filePath);
        FileOutputStream out = new FileOutputStream(file);
        out.write(picBinary.getBytes());
        out.close();
        return file;
    }

    public static String readFile(String filePath) {
        try {
            sun.misc.BASE64Decoder decoder = new BASE64Decoder();
            File file = new File(filePath);
            java.io.FileReader reader = new FileReader(file);
            char[] c = new char[1024];
            StringBuffer buf = new StringBuffer();
            int index = -1;
            while ((index = reader.read(c)) > 0) {
                buf.append(c, 0, index);
            }
            String result = new String(decoder.decodeBuffer(buf.toString()), "gb2312");

            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
