// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XmlDom4j.java

package com.boco.eoms.util;

import com.boco.eoms.commons.loging.BocoLog;
import java.io.PrintStream;
import java.util.*;
import org.dom4j.*;

public class XmlDom4j
{

	public XmlDom4j()
	{
	}

	public List getList(String xmlDoc)
		throws Exception
	{
		List list = new ArrayList();
		if (xmlDoc.indexOf("?xml") <= 0)
			xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
		Document doc = DocumentHelper.parseText(xmlDoc);
		Element root = doc.getRootElement();
		Map map;
		for (Iterator i = root.elementIterator("recordInfo"); i.hasNext(); list.add(map))
		{
			Element recordInfo = (Element)i.next();
			map = new HashMap();
			Element fieldInfo;
			for (Iterator j = recordInfo.elementIterator("fieldInfo"); j.hasNext(); map.put(fieldInfo.elementText("fieldEnName"), fieldInfo.elementText("fieldContent")))
			{
				fieldInfo = (Element)j.next();
				BocoLog.info(this, "fieldChName:" + fieldInfo.elementText("fieldChName"));
				BocoLog.info(this, "fieldEnName:" + fieldInfo.elementText("fieldEnName"));
				BocoLog.info(this, "fieldContent:" + fieldInfo.elementText("fieldContent"));
			}

		}

		return list;
	}

	public static void main(String args[])
	{
		String xml = "<opDetail><recordInfo><fieldInfo><fieldChName>1</fieldChName><fieldEnName>11</fieldEnName><fieldContent>111</fieldContent></fieldInfo><fieldInfo><fieldChName>2</fieldChName><fieldEnName>22</fieldEnName><fieldContent>333</fieldContent></fieldInfo></recordInfo><recordInfo><fieldInfo><fieldChName>3</fieldChName><fieldEnName>33</fieldEnName><fieldContent>333</fieldContent></fieldInfo></recordInfo></opDetail>";
		String filePath = "D:/eoms35-shanxi/EOMSV3.5SN/eoms35/WebContent/WEB-INF/classes/com/boco/eoms/crm/config/crm-config.xml";
		Document doc = DocumentFactory.getInstance().createDocument(filePath);
		System.out.println(doc.getRootElement().getText());
		Node node = doc.selectSingleNode("//portType[@interfacecode='101101502']");
		System.out.println(node.getText());
		XmlDom4j d = new XmlDom4j();
		try
		{
			d.getList(xml);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
