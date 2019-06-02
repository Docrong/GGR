package com.boco.eoms.knowledge.util;

import java.io.StringReader;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.util.InterfaceUtil;

public class KnowledgeMethod {
	public static String getKnowledgeXml(String userId,String tableId,String sheetKey,Map mainMap,List linkMapList) throws Exception{
		String xml = "";
		xml += "<root>";
		xml += "<message>";
		xml += "<userId>"+userId+"</userId>";
		xml += "<sheetName>"+tableId+"</sheetName>";
		xml += "<sheetId>"+sheetKey+"</sheetId>";
		xml += "</message>";
		xml += "<detail>";
		xml += "<main>";
		
		if(mainMap!=null){
			Set set = mainMap.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = StaticMethod.nullObject2String(iterator.next());
				String value = StaticMethod.nullObject2String(mainMap
						.get(key));
				value = value.replaceAll("\"", "\\\"");
				xml += "<col name=\""+key+"\" value=\""+InterfaceUtil.changeCharater(value)+"\"/>";
			}
		}		
		xml += "</main>";
		
		if(linkMapList!=null){
			for(int i=0;i<linkMapList.size();i++){
				xml += "<link>";
				
				Map linkMap = (Map)linkMapList.get(i);
				Set set = linkMap.keySet();
				Iterator iterator = set.iterator();
				while (iterator.hasNext()) {
					String key = StaticMethod.nullObject2String(iterator.next());
					String value = StaticMethod.nullObject2String(linkMap
							.get(key));
					value = value.replaceAll("\"", "\\\"");
					System.out.println(key);
					System.out.println(value);
					xml += "<col name=\""+key+"\" value=\""+InterfaceUtil.changeCharater(value)+"\"/>";
				}
				
				xml += "</link>";
			}
		}
		xml += "</detail>";
		xml += "</root>";
		
		System.out.println("root="+xml);
		return xml;
	}
	
	
	
	public static String pushSheet(String sheetKey){
		return "";
	}
	/**
	 * 解析main数据
	 * @param xmlDoc
	 * @return
	 */
	public static Map getMapFromXml(String xmlDoc){
		Map map = null;
		try {			
			map = new HashMap();
			Map mainMap = new HashMap();
			Map megMap = new HashMap();
			Node megNode = null;
			Node mainNode = null;
		
			//通过输入源构造一个Document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder dc=dbf.newDocumentBuilder();
			if(xmlDoc.indexOf("xml")<=0)
				xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>"+xmlDoc;
			Document doc = dc.parse(new InputSource(new StringReader(xmlDoc)));
			
			String xpath = "//root/message";
			megNode = XPathAPI.selectSingleNode(doc, xpath);
			if(megNode!=null){
				NodeList colNodelist = megNode.getChildNodes();
				for(int i=0;i<colNodelist.getLength();i++){
					Node node = colNodelist.item(i);
					System.out.println("meg name="+node.getNodeName());
					System.out.println("meg value="+node.getFirstChild().getNodeValue());
					megMap.put(node.getNodeName(), node.getFirstChild().getNodeValue());
				}
			}

			xpath = "//root/detail/main";
			mainNode = XPathAPI.selectSingleNode(doc, xpath);
			if(mainNode!=null){
				NodeList colNodelist = mainNode.getChildNodes();
				for(int i=0;i<colNodelist.getLength();i++){
					Element colNode = (Element)colNodelist.item(i);
					if(colNode.getAttribute("name")!=null){
						System.out.println("col name="+colNode.getAttribute("name"));
						System.out.println("col value="+colNode.getAttribute("value"));
						mainMap.put(colNode.getAttribute("name"), colNode.getAttribute("value"));
					}
				}
			}		
			map.put("mainMap", mainMap);
			map.put("megMap", megMap);
				
		}catch(Exception err){
			System.out.println("解析xml错误");
			err.printStackTrace();
		}
		return map;
		
	}
	public static void main(String[] args) {
		try{
			String xml = "sdfsdf\"sdfsdf\"sdfsdfd";
			System.out.println(xml);
			xml = xml.replace("\"", "\\\"");
			System.out.println(xml);
			
			String aaa = "*&^<>";
			aaa = InterfaceUtil.changeCharater(aaa);
			xml = "";
			xml += "<root>";
			xml += "<message>";
			xml += "<userId>a</userId>";
			xml += "<sheetName>d</sheetName>";
			xml += "<sheetId>f</sheetId>";
			xml += "</message>";
			xml += "<detail>";
			xml += "<main>";
			xml += "<col name=\"aaa\" value=\""+aaa+"\"/>";
			xml += "</main>";
			xml += "</detail>";
			xml += "</root>";
			KnowledgeMethod.getMapFromXml(xml);
		}catch(Exception err){
			System.out.println("解析xml错误");
			err.printStackTrace();
		}
	}
	
}
