package com.ggr.leecode.temp;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test2 {
    public static void main(String[] args) throws ParseException, DocumentException {


        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 读取XML文件结构


        File file = new File("server.xml");
        System.out.println(file.getAbsolutePath());
        Document doc = reader.read(file);
        // 获取XML文件根节点
        Element root = doc.getRootElement();
        // 获取根节点一下的子元素
        List<Element> list = root.elements();
        String mainNetWork1 = "101060107";

        String mainNetWork2 = "101060101";
        String mainNetWork3 = "10106010101";
        /*
        101060107
        101060101
        10106010101
        1010601010104
        101060101010408
        null
        null
         */
        String defaultallowtime = "";
        String allowtime = "";
        for (Element item : list) {

            if ("timeLimit".equals(item.getName())) {
                System.out.println("设置使用时间");
                List<Element> list1 = item.elements();
                for (Element element : list1) {
                    System.out.println("当前元素是:" + element.getName());
                    List<Element> list2 = element.elements();
                    Map<String, String> map = new HashMap();
                    for (Element ele : list2) {
//		                            System.out.println("当前元素是:" + ele.getName() + "值是:" + ele.getStringValue());
                        map.put(ele.getName(), ele.getStringValue());
                    }
                    System.out.println(map);
                    if (map.get("network1").equals(mainNetWork1) && map.get("network2").equals(mainNetWork2) &&
                            map.get("network3").equals(mainNetWork3)) {
                        allowtime = map.get("allowtime");
                    }
                    if (map.get("network1").equals("") && map.get("network2").equals("") &&
                            map.get("network3").equals("")) {
                        defaultallowtime = map.get("allowtime");
                    }
                }
                if (allowtime.equals("")) {
                    allowtime = defaultallowtime;
                }
                System.out.println("延迟时间是:" + allowtime);
                System.out.println("默认时限是:" + defaultallowtime);
            }

        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date completeLimitDate = new Date();
        System.out.println("当前时间:" + sdf.format(completeLimitDate));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(completeLimitDate);
        System.out.println("时间:" + sdf.format(calendar.getTime()));
        calendar.add(Calendar.HOUR, Integer.parseInt(allowtime));
        completeLimitDate = calendar.getTime();


        System.out.println("处理时限:" + sdf.format(completeLimitDate));
        System.out.println("ggr==end complaint CrmServicemanagerImpl()时限设置");
    }
}
