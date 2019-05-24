package com.boco.eoms.sheet.interfaceBase.util;

import java.io.File;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.sheet.base.util.flowdefine.xml.StaticMethod;

public class XmlUtil {
	public static String getInterfaceBeanIdByMainBeanId(String beanId){
		try{
			SAXBuilder dc=new SAXBuilder();
			Document doc = dc.build(new File(StaticMethod.getFilePathForUrl("classpath:config/interface-util.xml")));
			
			Element element = doc.getRootElement();
			element = element.getChild("sheettype");
			
			List list = element.getChildren();
			for(int i=0;i<list.size();i++){
				Element node = (Element)list.get(i);
				String mainBeanId = node.getAttribute("mainBeanId").getValue();
				String interfaceBeanId = node.getAttribute("interfaceBeanId").getValue();
				if(mainBeanId.equals(beanId))
					return interfaceBeanId;
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		return "";
	}
}
