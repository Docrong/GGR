package com.boco.eoms.commonfaultIrontower;

import java.util.*;
import java.util.Map.Entry;

import org.dom4j.*;

import com.boco.eoms.base.util.StaticMethod;

public class IrontowerUtil {
    public IrontowerUtil() {
    }

    /**
     * @param xmlDoc
     * @return
     * @throws Exception
     */
    public Map getMap2Xml(String xmlDoc, String nodeName)
            throws Exception {
        Map map = new HashMap();
        if (xmlDoc.indexOf("?xml") <= 0)
            xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
        Document doc = DocumentHelper.parseText(xmlDoc);
        Element root = doc.getRootElement();
        for (Iterator j = root.elementIterator(nodeName); j.hasNext(); ) {
            Element customerPointInfo = (Element) j.next();
            Element element;
            for (Iterator k = customerPointInfo.elementIterator(); k.hasNext(); map.put(element.getName(), element.getText())) {
                element = (Element) k.next();
                System.out.println("key=" + element.getName() + "==value=" + element.getText());
            }

        }
        return map;
    }

    public String getXml2Map(Map map, String nodeName) throws Exception {
        String xmlStr = "";

        if (map != null) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Entry) it.next();
                String key = StaticMethod.nullObject2String(entry.getKey());
                String value = StaticMethod.nullObject2String(entry.getValue());
                xmlStr = xmlStr + "<" + key + ">" + value + "</" + key + ">";
            }
        }
        if (!"".equals(nodeName)) {
            xmlStr = "<" + nodeName + ">" + xmlStr + "</" + nodeName + ">";
        }
        return xmlStr;
    }

}
