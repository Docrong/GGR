package com.boco.eoms.wap.platform.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.workbench.infopub.model.Thread;

/**
 * @author 龚玉峰
 * @date 20090218 19:47　
 * @see  信息发布xml生成
 */
public class InfopubXmlSave {

	/**
	 * @param className
	 * @return String
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @see 读入数据并写到一个xml文件。返回一个存在本地的xml文件路径
	 */
	public static String XmlWriteReturnUrl(Thread thread) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// 文件路径
  
		 String xmlPath = AccessoriesMgrLocator.getAccessoriesAttributes()
				.getUploadPath();
		String xmlName = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss"); 
		
		String xmlFile = "";
		
		try{
		Document document = DocumentFactory.getInstance().createDocument();
		document.setXMLEncoding("GB2312");  
		// 添加根元素
		Element root = document.addElement("process");
		// 元素信息
		// 添加第一个的信息
		Element attributes = root.addElement("attributes");
		// 添加attribute
		Element attribute = attributes.addElement("attribute");

		 
		Element title = attribute.addElement("title");
		Element name = attribute.addElement("name");

		title.setText("标题");
		name.setText(StaticMethod.null2String(thread.getTitle()));
		
		Element attribute1 = attributes.addElement("attribute");
		Element title1 = attribute1.addElement("title");
		Element name1 = attribute1.addElement("name");

		title1.setText("紧急程度");
		name1.setText(StaticMethod.null2String(thread.getStatus()));
		
		Element attribute2 = attributes.addElement("attribute");
		Element title2 = attribute2.addElement("title");
		Element name2 = attribute2.addElement("name");

		title2.setText("内容");
		name2.setText(StaticMethod.null2String(thread.getContent()));
		
		
		Element attribute3 = attributes.addElement("attribute");
		Element title3 = attribute3.addElement("title");
		Element name3 = attribute3.addElement("name");

		title3.setText("附件");
		name3.setText(StaticMethod.null2String(thread.getAccessories()));
		
		
		 
		// 添加第二个信息
		Element parameters = root.addElement("parameters");
		// 添加parameter
		Element parameter = parameters.addElement("parameter");
		
		Element id = parameter.addElement("id");
		Element values = parameter.addElement("value");
		
		id.setText("name");
		values.setText("123123123");
		//添加hidden
		Element hidden = parameters.addElement("hidden");
		Element hiddenid = hidden.addElement("id");
		Element hiddenvalues = hidden.addElement("value");
	    
		hiddenid.setText("");
		hiddenvalues.setText(""); 
		
		
		File file = new File(xmlPath);
		if (!file.exists()) {
			file.mkdir();
		}
		xmlFile= xmlPath + xmlName + ".xml";
		// 文件输出流
		FileWriter out = new FileWriter(xmlFile);
		// 声明格式化变量
		OutputFormat of = OutputFormat.createPrettyPrint();
		// 写入文件
		XMLWriter xout = new XMLWriter(out, of);
		xout.write(document);
		// 关闭流
		/*
		OutputStream os=new FileOutputStream("c:/test/Interest.xml");
		XMLWriter output=new XMLWriter(new OutputStreamWriter(os,"UTF-8"));
		output.write(document);
		*/
		xout.close();
		out.close();
	  } catch (IOException e) {

		e.printStackTrace();
	 }
		/*
		String xml = document.asXML();
		String[] xmlArray = xml.split("\n");
		
		System.out.println(xmlArray[0]+xmlArray[1]);*/
		//返回一个xml格式的流
		return xmlFile;
	}

}
