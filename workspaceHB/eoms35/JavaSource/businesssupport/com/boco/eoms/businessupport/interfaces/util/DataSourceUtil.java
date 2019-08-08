package com.boco.eoms.businessupport.interfaces.util;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.client.ResManagerLoader;

public class DataSourceUtil {

    /**
     * 将一个table数据转换成接口的xml数据
     *
     * @param tableId 数据集标识
     * @param records 生成内容示例：
     *                <table id="tableId">
     *                		<row username="tom" birthday="2000-12-24"></row>
     *                		<row username="jack" birthday="1998-06-12"></row>
     *                </table>
     */
    public static String getWellFormatXml(String tableId, List records) {
        Map map = new HashMap();
        map.put(tableId, records);
        return getWellFormatXml(map);
    }

    /**
     * 将多个table转换成接口xml数据
     *
     * @param source<list>
     * @return
     */
    public static String getWellFormatXml(Map source) {
        if (source == null || source.size() < 1) {
            return null;
        }
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("UTF-8");
        Element root = document.addElement("root");
        Iterator it = source.entrySet().iterator();
        while (it.hasNext()) {
            Element row = null;
            Map.Entry entry = (Map.Entry) it.next();
            Object valueObject = entry.getValue();
            /**单条记录*/
            if (valueObject instanceof Map) {
                row = root.addElement("table").addAttribute("id", entry.getKey().toString()).addElement("row");
                appendXmlAttributes(row, ((Map) valueObject));
            } else if (valueObject instanceof List)/**多条记录*/ {
                List records = (List) entry.getValue();
                Element table = root.addElement("table").addAttribute("id", entry.getKey().toString());
                for (int i = 0; i < records.size(); i++) {
                    Map map = (Map) records.get(i);
                    row = table.addElement("row");
                    appendXmlAttributes(row, map);
                }
            } else if (valueObject == null) {
            } else if (valueObject instanceof String) {
            } else if (valueObject instanceof Object) {
                row = root.addElement("table").addAttribute("id", entry.getKey().toString()).addElement("row");
                appendXmlAttributes(row, getMapFromObject(valueObject));
            } else {
                //throw new RuntimeException("根据对象生成xml时，map内元素必须为Map或List");
            }
        }
        return document.asXML();
    }

    private static Map getMapFromObject(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map map = new HashMap();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = (Field) fields[i];
                Object vObj = PropertyUtils.getProperty(obj, field.getName());
                String v = vObj == null ? "" : vObj.toString();
                map.put(field.getName(), v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 添加xml节点属性
     *
     * @param row
     * @param rec
     */
    private static void appendXmlAttributes(Element row, Map map) {
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = StaticMethod.nullObject2String(it.next());
            Object obj = map.get(key);
            row.addAttribute(key, StaticMethod.nullObject2String(obj));
        }
    }

    /**
     * 一次性从xml文件中读取多个DataRecord数据集合
     *
     * @param xmlSource xml数据源，可以为xml字符串或者xml文件
     * @return map 返回map，map的key为指定的标识，值为List
     */
    public static Map buildMultiRecordsByXml(Object xmlSource) {
        Element root = getRootElement(xmlSource);
        if (root == null) {
            return null;
        }
        Map map = new HashMap();
        List tables = root.elements();
        for (int i = 0; i < tables.size(); i++) {
            Element table = (Element) tables.get(i);
            List list = getList(root, table);
            map.put(table.attributeValue("id"), list);
        }
        return map;
    }

    private static List getList(Element root, Element table) {
        List list = null;
        try {
            List rows = table.selectNodes("row");//得到所有记录
            if (rows == null || rows.size() < 1) {
                return null;
            }
            list = new ArrayList();
            Map row = null;
            for (int i = 0; i < rows.size(); i++) {
                row = new HashMap();
                List fields = ((Element) rows.get(i)).attributes();//得到所有字段
                for (int j = 0; j < fields.size(); j++) {
                    Attribute att = (Attribute) fields.get(j);
                    /**dom4j会将tab自动换成空格*/
                    row.put(att.getName(), att.getText() == null ? "" : att.getText().replaceAll("#convertTab#", "\\\t"));
                }
                list.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 获取根节点
     */
    private static Element getRootElement(Object xmlSource) {
        if (xmlSource == null) {
            return null;
        }
        SAXReader reader = new SAXReader();
        reader.setEncoding("UTF-8");
        Document doc = null;
        try {
            if (xmlSource instanceof String) {
                /**dom4j会将tab自动换成空格*/
                xmlSource = xmlSource.toString().replaceAll("\\\t", "#convertTab#");

                if (((String) xmlSource).indexOf("?xml") <= 0)
                    xmlSource = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlSource;

                doc = reader.read(new StringReader((String) xmlSource));
            } else if (xmlSource instanceof File) {
                doc = reader.read((File) xmlSource);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return doc.getRootElement();
    }

    public static void main(String[] avg) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><table id=\"commInfo\"><row rtMessage=\"id to load is required for loading\" rtCode=\"0\"/></table></root>";

        Map resultMap = ResManagerLoader.transactionXml(xml);
        List list = (List) resultMap.get("commInfo");
        if (list != null && list.size() > 0) {
            Map map = (Map) list.get(0);
            System.out.println(map.get("rtCode"));
        }


    }
}
