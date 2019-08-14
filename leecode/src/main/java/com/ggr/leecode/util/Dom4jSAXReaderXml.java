package com.ggr.leecode.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dom4jSAXReaderXml {
    public static void main(String[] args) {

        try {

            // 创建SAXReader对象
            SAXReader reader = new SAXReader();
            // 读取XML文件结构
            File file = new File("config/server.xml");
            System.out.println(file.getAbsolutePath());
            Document doc = reader.read(file);
            // 获取XML文件根节点
            Element root = doc.getRootElement();
            // 获取根节点一下的子元素
            List<Element> list = root.elements();
            String mainNetWork1 = "101060107";
            String mainNetWork2 = "101060105";
            String mainNetWork3 = "10106010502";
            String mainNetWork4 = "10106010502";
            String mainNetWork5 = "10106010502";
            String mainNetWork6 = "10106010502";
            String mainNetWork7 = "10106010502";
            String defaultallowtime = "";
            String allowtime = "";
            List xml2list = new ArrayList();
            for (Element item : list) {

                if ("MySql".equals(item.getName())) {
                    System.out.println("MySQL数据库");
                    // 获取MySql下的子元素
                    List<Element> list1 = item.elements();
                    // 迭代子节点的元素值
                    for (Element element : list1) {

                        System.out.println("当前元素是:" + element.getName() + "值是:"
                                + element.getStringValue());
                    }
                } else if ("SQLServer".equals(item.getName())) {
                    System.out.println("SQLServer数据库");
                    // 获取SQLServer下的子元素
                    List<Element> list1 = item.elements();
                    // 迭代子节点的元素值
                    for (Element element : list1) {
                        System.out.println("当前元素是:" + element.getName() + "值是:" + element.getStringValue());
                    }

                }


                if ("timeLimit".equals(item.getName())) {
                    System.out.println("设置使用时间");
                    List<Element> list1 = item.elements();
                    for (Element element : list1) {
                        System.out.println("当前元素是:" + element.getName());
                        List<Element> list2 = element.elements();
                        Map<String, String> map = new HashMap();
                        for (Element ele : list2) {
//                            System.out.println("当前元素是:" + ele.getName() + "值是:" + ele.getStringValue());
                            map.put(ele.getName(), ele.getStringValue());
                        }
                        System.out.println(map);
                        xml2list.add(map);
                       /* if (map.get("network1").equals(mainNetWork1) && map.get("network2").equals(mainNetWork2) &&
                                map.get("network3").equals(mainNetWork3)) {
                            allowtime = map.get("allowtime");
                        }
                        if (map.get("network1").equals("") && map.get("network2").equals("") &&
                                map.get("network3").equals("")) {
                            defaultallowtime = map.get("allowtime");
                        }*/
                    }
                /*    if (allowtime.equals("")) {
                        allowtime = defaultallowtime;
                    }
                    System.out.println("延迟时间是:" + allowtime);
                    System.out.println("默认时限是:" + defaultallowtime);*/
                }

            }
            /*String sheetCompleteLimit="2019-08-04 09:37:20";
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar=Calendar.getInstance();
            Date completeLimitDate=sdf.parse(sheetCompleteLimit);

            calendar.setTime(completeLimitDate);
            System.out.println(calendar.getTime());
            calendar.add(Calendar.HOUR,Integer.parseInt(allowtime));
            System.out.println(calendar.getTime());
            System.out.println(completeLimitDate);*/
            System.out.println(xml2list);
            for (int i = 0; i < xml2list.size(); i++) {
                Map map = (Map) xml2list.get(i);
                String network1 = String.valueOf(map.get("network1"));
                String network2 = String.valueOf(map.get("network2"));
                String network3 = String.valueOf(map.get("network3"));
                String network4 = String.valueOf(map.get("network4"));
                String network5 = String.valueOf(map.get("network5"));
                String network6 = String.valueOf(map.get("network6"));
                String network7 = String.valueOf(map.get("network7"));
                if (network1.equals("") && network2.equals("") && network3.equals("") && network4.equals("") && network5.equals("")
                        && network6.equals("") && network7.equals("")) {
                    defaultallowtime = String.valueOf(map.get("allowtime"));
                } else {
                    if ((network1.equals(mainNetWork1)) &&
                            (network2.equals("") || network2.equals(mainNetWork2)) &&
                            (network3.equals("") || network3.equals(mainNetWork3)) &&
                            (network4.equals("") || network4.equals(mainNetWork4)) &&
                            (network5.equals("") || network5.equals(mainNetWork5)) &&
                            (network6.equals("") || network6.equals(mainNetWork6)) &&
                            (network7.equals("") || network7.equals(mainNetWork7))) {
                        allowtime = String.valueOf(map.get("allowtime"));
                    }

                }
            }
            if (allowtime.equals("")) {
                allowtime = defaultallowtime;
            }
            System.out.println("延迟时间是:" + allowtime);
            System.out.println("默认时限是:" + defaultallowtime);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
