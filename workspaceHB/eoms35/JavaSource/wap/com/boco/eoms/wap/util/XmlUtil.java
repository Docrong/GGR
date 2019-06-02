package com.boco.eoms.wap.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.boco.eoms.wap.model.Attribute;
import com.boco.eoms.wap.model.Parameter;

/**
 * 用于解析 流程提供的detail页面显示字段和隐藏参数组成的xml流
 * @author xugengxian
 * @date Feb 17, 2009 9:12:02 AM
 */
public class XmlUtil {
	
	/**
	 * 从目标xml流解析出来的Document对象
	 */
	private Document doc;
	
	/**
	 * 默认构造方法
	 */
	public XmlUtil(){
		super();
	}
	
	/**
	 * 带参数的构造方法
	 * @param inputURL xml资源的流
	 */
	public XmlUtil(InputStream inputURL) {
		DocumentBuilder builder;
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		try {
			builder = domFactory.newDocumentBuilder();
			//通过URL读取资源，并将xml资源存储到doc中
			doc = builder.parse(inputURL);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回对应nodeName下的所有子节点，并以NodeList返回
	 * @param xPathString xPath表达式
	 * @return
	 */
	private NodeList parseDocument(String xPathString) {
		Object obj = new Object();
		try {
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			XPathExpression expr = xPath.compile(xPathString);
			//根据xPath表达式从doc中查询相应数据
			obj = expr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return (NodeList) obj;
	}
	
	/**
	 * 返回xml流中需要在detail页面显示的model集合
	 * @return List 中是多个 com.boco.eoms.wap.model.AttributeModel
	 */
	public List getAttributes() {
		List attributes = new ArrayList();
		Attribute attribute;
		//取得所有的<attribute>节点
		NodeList parentNodeList = parseDocument("//attributes/attribute");
		for (int i = 0; i < parentNodeList.getLength(); i++) {
			//分别取得每个<attribute>节点下的所有子节点，包括<title> 、<name>
			NodeList childNodeList = parentNodeList.item(i).getChildNodes();
			attribute = new Attribute();
			for (int j = 0; j < childNodeList.getLength(); j++) {
				//如果节点名是title，则将节点值set到title属性中
				if("title".equalsIgnoreCase(childNodeList.item(j).getNodeName())) {
						attribute.setTitle(childNodeList.item(j).getFirstChild().getNodeValue());
				}
				if("name".equalsIgnoreCase(childNodeList.item(j).getNodeName())) {
					//如果xml节点中值为空，则会报异常，将异常捕获后赋予""
					try{
						attribute.setName(childNodeList.item(j).getFirstChild().getNodeValue());
					} catch (Exception ex) {
						attribute.setName("");
					}
				}
			}
			attributes.add(attribute);
		}
		return attributes;
	}
	
	/**
	 * 返回xml流中需要在detail页面隱藏字段的model
	 * @return List 中是多个 com.boco.eoms.wap.model.Parameter对象
	 */
	public List getHiddens() {
		List hiddens = new ArrayList();
		//取得所有的<hidden>节点
		NodeList parentNodeList = parseDocument("//parameters/hidden");
		Parameter hidden;
		for (int i = 0; i < parentNodeList.getLength(); i++) {
			//取得每个<hidden>节点下的所有子节点，包括<id>、<value>
			NodeList childNodeList = parentNodeList.item(i).getChildNodes();
			hidden = new Parameter();
			for (int j = 0; j < childNodeList.getLength(); j++){
				//将各个节点的值set到对应的属性中
				if ("id".equalsIgnoreCase(childNodeList.item(j).getNodeName())) {
					hidden.setId(childNodeList.item(j).getFirstChild().getNodeValue());
				}
				if ("value".equalsIgnoreCase(childNodeList.item(j).getNodeName())) {
					//如果xml节点中值为空，则会报异常，将异常捕获后赋予""
					try{
						hidden.setValue(childNodeList.item(j).getFirstChild().getNodeValue());
					} catch (Exception ex){
						hidden.setValue("");
					}
				}
			}
			hiddens.add(hidden);
		}
		return hiddens;
	}
	
	/**
	 * 从xml中解析出wap上用来作判断的参数
	 * @return List 中存储着 com.boco.eoms.wap.model.Parameter对象
	 */
	public List getParameter() {
		List params = new ArrayList();
		//取得所有的<parameter>节点
		NodeList parentNodeList = parseDocument("//parameters/parameter");
		Parameter param;
		for (int i = 0; i < parentNodeList.getLength(); i++) {
			//取得每个<parameter>节点下的所有子节点，包括<id>、<value>
			NodeList childNodeList = parentNodeList.item(i).getChildNodes();
			param = new Parameter();
			for (int j = 0; j < childNodeList.getLength(); j++){
				//将各个节点的值set到对应的属性中
				if ("id".equalsIgnoreCase(childNodeList.item(j).getNodeName())) {
					param.setId(childNodeList.item(j).getFirstChild().getNodeValue());
				}
				if ("value".equalsIgnoreCase(childNodeList.item(j).getNodeName())) {
					//如果xml节点中值为空，则会报异常，将异常捕获后赋予""
					try{
						param.setValue(childNodeList.item(j).getFirstChild().getNodeValue());
					} catch (Exception ex){
						param.setValue("");
					}
				}
			}
			params.add(param);
		}
		return params;

	}

}
