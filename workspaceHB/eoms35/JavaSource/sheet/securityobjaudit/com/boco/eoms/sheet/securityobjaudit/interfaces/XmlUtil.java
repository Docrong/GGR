package com.boco.eoms.sheet.securityobjaudit.interfaces;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;





/**
 * XMl工具类
 * 
 * @author tangyangbo
 */
public class XmlUtil {
	//public static String head = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	public static String head = "<?xml version=\"1.0\" encoding=\"GB2312\"?>";
	static String lt = "<";
	static String ltEnd = "</";
	static String rt = ">";
	static String rhtEnd = "/>";
	static String quotes = "\"";
	static String equal = "=";
	static String blank = " ";

	public static String elementToXml(Element el,String head) {
		StringBuffer result = new StringBuffer(head);
		// 元素开始
		result.append(lt).append(el.getName());
		// 判断是否有属性
		if (el.getProperty() != null && el.getProperty().size() > 0) {
			Iterator iter = el.getProperty().keySet().iterator();
			while (iter.hasNext()) {
				String key = String.valueOf(iter.next());
				String value = (String) el.getProperty().get(key);
				result.append(blank).append(key).append(equal).append(quotes)
						.append(value).append(quotes).append(blank);
			}
		}
		result.append(rt);// 结束标记
		/*
		 * 判断是否是叶子节点 是叶子节点，添加节点内容 不是叶子节点，循环添加子节点
		 */
		if (el.isIsleaf()) {
			result.append(el.getNodeText());
		} else {
//			for (Element element : el.getChild()) {
//				result.append(elementToXml(element, ""));
//			}
			for(int i=0;i<el.getChild().size();i++){
				Element element = (Element)el.getChild().get(i);
				result.append(elementToXml(element, ""));
			}
		}
		// 元素结束
		result.append(ltEnd).append(el.getName()).append(rt);
		return result.toString();
	}

	/**
	 * 获取源xml中唯一节点值
	 * 
	 * @param src
	 *            以xml格式存储的信息
	 * @param nodeName
	 *            节点名称
	 * @return 对应节点值
	 */
	public static String getSingleNodeValue(String src, String nodeName) {
		StringBuilder nodeValue = new StringBuilder();
		StringBuilder beginTag = new StringBuilder(lt + nodeName + rt);
		StringBuilder endTag = new StringBuilder(ltEnd + nodeName + rt);
		if (null != src && !"".equals(src.trim()) && null != nodeName
				&& !"".equals(nodeName.trim())) {
			int startIndex = src.indexOf(beginTag.toString())
					+ beginTag.length();
			int endIndex = src.indexOf(endTag.toString(), startIndex);
			if (endIndex > 0) {
				nodeValue.append(src.substring(startIndex, endIndex));
			}
		}
		return nodeValue.toString();
	}

	/**
	 * 获取源xml中唯一节点值
	 * 
	 * @param src
	 *            以xml格式存储的信息
	 * @param nodeName
	 *            节点名称
	 * @return 对应节点值
	 */
	public static String getSingleNodeValue2(String src, String nodeName) {
		try {
			
				
//			List elements = root.elements();
			src = src.replace("<secObjects><secObject>", "");
			src = src.replace("</secObject></secObjects>", "");
			
			
			src = src.replace("<propertys><propertyObject>", "");
			src = src.replace("<property>", "");
			src = src.replace("</property>", "");
			src = src.replace("</propertyObject></propertys>", "");
			  
			src = src.replace("<msg>", "");
			src = src.replace("</msg>", "");
			
			org.dom4j.Document doc = org.dom4j.DocumentHelper.parseText(src);
			org.dom4j.Element root = doc.getRootElement();
			
			System.out.println(nodeName+"=" + root.elementText(nodeName));
			return root.elementText(nodeName);
//			
			/*for (org.dom4j.Element element : elements) {
				System.out.println(nodeName+"=" + element.elementText(nodeName));
				System.out.println(element.getText());
				return element.elementText(nodeName);
			}*/
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSingleNodeValue3(String src, String nodeName) throws Exception {
		
		int intin = nodeName.indexOf(".");
			if(nodeName.indexOf(".")>=0){
				
				
				String nodeNameOne = nodeName.substring(0, nodeName.lastIndexOf("."));
				String nodeNameTwo = nodeName.substring(nodeName.lastIndexOf(".")+1);
				List list = (List)getRoots(src,nodeNameOne);
				Map map = (Map) list.get(0);
				String nodeNameTwoValue = (String) map.get(nodeNameTwo);
				System.out.println(nodeNameTwo+"=" + nodeNameTwoValue);
				return nodeNameTwoValue;
			}else{
				
				org.dom4j.Document doc = org.dom4j.DocumentHelper.parseText(src);
				org.dom4j.Element root = doc.getRootElement();
				System.out.println(nodeName+"=" + root.elementText(nodeName));
				return root.elementText(nodeName);
			}
			
		
	}
	
	public static  List getRoots(String src, String nodeName) throws Exception{  
		org.dom4j.Document doc = org.dom4j.DocumentHelper.parseText(src);
	    org.dom4j.Element root=doc.getRootElement();//获取根节点  
	    return getNodes(root,nodeName);//从根节点开始遍历所有节点  
	  
	}
	
	/**
	 * 从指定节点开始,递归遍历所有子节点
	 * 
	 * @author chenleixing
	 */
	 static List list = new ArrayList();
	public static  List getNodes(org.dom4j.Element node,
			String nodeName) {
		//

		

		// 当前节点的名称、文本内容和属性
		/*
		 * System.out.println("当前节点名称："+node.getName());//当前节点名称
		 * System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
		 */
		if (nodeName.equals(node.getName().trim().toString())
				&& nodeName == node.getName().trim()) {
			List elements = node.elements();// 当前节点的所有属性的list
			System.out.println(elements.size());
			int i=0;
			Map map = null;
//			for (org.dom4j.Element element : elements) {// 遍历当前节点的所有属性
//				i++;
//				if(i==1){
//					map = new HashMap();
//				}
//				
//				String name = element.getName();// 属性名称
//				String value = element.getText();// 属性的值
//				System.out.println("属性名称：" + name + "属性值：" + value);
//				map.put(name, value);
//				if(i==elements.size()){
//					list.add(map);
//				}
//				
//			}
			
			for(int j=0;j<elements.size();j++){
				i++;
				if(i==1){
					map = new HashMap();
				}
				
				org.dom4j.Element element =(org.dom4j.Element) elements.get(j);
				
				String name = element.getName();// 属性名称
				String value = element.getText();// 属性的值
				System.out.println("属性名称：" + name + "属性值：" + value);
				map.put(name, value);
				if(i==elements.size()){
					list.add(map);
				}
			}
		}
		// 递归遍历当前节点所有的子节点
		List listElement = node.elements();// 所有一级子节点的list
//		for (org.dom4j.Element e : listElement) {// 遍历所有一级子节点
//			getNodes(e, nodeName);// 递归
//		}
		
		for(int i=0;i<listElement.size();i++){
			org.dom4j.Element e =(org.dom4j.Element) listElement.get(i);
			getNodes(e, nodeName);// 递归
		}
		
		return list;
	}
	
	
	
	
	public static void main(String[] args) {
		Element el = new Element("node");

		Element el1 = new Element("node1");
		el1.addProperty("name", "zhangshan");
		el1.addProperty("password", "123465");
		el1.setNodeText("11111");
		el.addChild(el1);

		Element el2 = new Element("node1");
		el2.addProperty("name", "lisi");
		el2.addProperty("password", "3333");
		el2.setNodeText("11111");
		el.addChild(el2);
		System.out.println(XmlUtil.elementToXml(el,head));
	}
}
