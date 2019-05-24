// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XmlDom4jSX.java

package com.boco.eoms.util;


import com.boco.eoms.commons.loging.BocoLog;
import java.util.*;

import org.dom4j.*;

public class XmlDom4jSX
{

	public XmlDom4jSX()
	{
	}

	
	
	
	public List getList(String xmlDoc)
	throws Exception
{
	List list = new ArrayList();
	List pointInfoList = new ArrayList();
	if (xmlDoc.indexOf("?xml") <= 0)
		xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
	Document doc = DocumentHelper.parseText(xmlDoc);
	Element root = doc.getRootElement();
	for (Iterator i = root.elementIterator("customerPointInfos"); i.hasNext();)
	{
		Element customerPointInfos = (Element)i.next();
		Map map;
		for (Iterator j = customerPointInfos.elementIterator("customerPointInfo"); j.hasNext(); pointInfoList.add(map))
		{
			Element customerPointInfo = (Element)j.next();
			map = new HashMap();
			Element element;
			for (Iterator k = customerPointInfo.elementIterator(); k.hasNext(); map.put(element.getName(), element.getText()))
				element = (Element)k.next();

		}

	}

	for (Iterator i = root.elementIterator("recordInfo"); i.hasNext();)
	{
		Element recordInfo = (Element)i.next();
		Map map = new HashMap();
		Element fieldInfo;
		for (Iterator j = recordInfo.elementIterator("fieldInfo"); j.hasNext(); map.put(fieldInfo.elementText("fieldEnName"), fieldInfo.elementText("fieldContent")))
		{
			fieldInfo = (Element)j.next();
			BocoLog.info(this, "fieldChName:" + fieldInfo.elementText("fieldChName"));
			BocoLog.info(this, "fieldEnName:" + fieldInfo.elementText("fieldEnName"));
			BocoLog.info(this, "fieldContent:" + fieldInfo.elementText("fieldContent"));
		}

		if (pointInfoList.size() > 0)
		{
			for (int p = 0; p < pointInfoList.size(); p++)
			{
				Map pointInfoMap = (Map)pointInfoList.get(p);
				pointInfoMap.putAll(map);
				list.add(pointInfoMap);
			}

		} else
		{
			list.add(map);
		}
	}

	return list;
}

public static void main(String args[])
{
	String xml = "<opDetail><customerPointInfos><customerPointInfo><cableCount>\347\224\265\350\267\257\346\225\260\351\207\2171</cableCount><bandwidth>\345\270\246\345\256\275</bandwidth><cityA>\345\237\216\345\270\202A</cityA><cityZ>\345\237\216\345\270\202Z</cityZ><SevAPointName>A\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\220\215\347\247\260</SevAPointName><SevZPointName>Z\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\220\215\347\247\260</SevZPointName><ASiteName>A\347\253\257\347\253\231\347\202\271\345\220\215\347\247\260</ASiteName><ZSiteName>Z\347\253\257\347\253\231\347\202\271\345\220\215\347\247\260</ZSiteName><portA>\347\253\257\347\202\271A</portA><portAInterfaceType>\347\253\257\347\202\271A\346\216\245\345\217\243\347\261\273\345\236\213</portAInterfaceType><portADetailAdd>\347\253\257\347\202\271A\350\257\246\347\273\206\345\234\260\345\235\200</portADetailAdd><portABDeviceName>\347\253\257\347\202\271A\344\270\232\345\212\241\350\256\276\345\244\207\345\220\215\347\247\260</portABDeviceName><portABDevicePort>\347\253\257\347\202\271A\344\270\232\345\212\241\350\256\276\345\244\207\347\253\257\345\217\243</portABDevicePort><portAContactPhone>\347\253\257\347\202\271A\350\201\224\347\263\273\344\272\272\345\217\212\347\224\265\350\257\235</portAContactPhone><portZ>\347\253\257\347\202\271Z</portZ><portZInterfaceType>\347\253\257\347\202\271Z\346\216\245\345\217\243\347\261\273\345\236\213</portZInterfaceType><portZDetailAdd>\347\253\257\347\202\271Z\350\257\246\347\273\206\345\234\260\345\235\200</portZDetailAdd><portZBDeviceName>\347\253\257\347\202\271Z\344\270\232\345\212\241\350\256\276\345\244\207\345\220\215\347\247\260</portZBDeviceName><portZBDevicePort>\347\253\257\347\202\271Z\344\270\232\345\212\241\350\256\276\345\244\207\347\253\257\345\217\243</portZBDevicePort><portZContactPhone>\347\253\257\347\202\271Z\350\201\224\347\263\273\344\272\272\345\217\212\347\224\265\350\257\235</portZContactPhone><sevAPointContact>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\344\272\272</sevAPointContact><sevAPointContactPhone>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\347\224\265\350\257\235</sevAPointContactPhone><sevAPointContactEmail>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\351\202\256\347\256\261</sevAPointContactEmail><sevAPointContactAdd>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\345\234\260\345\235\200</sevAPointContactAdd><sevZPointContact>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\344\272\272</sevZPointContact><sevZPointContactPhone>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\347\224\265\350\257\235</sevZPointContactPhone><sevZPointContactEmail>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\351\202\256\347\256\261</sevZPointContactEmail><sevZPointContactAdd>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\345\234\260\345\235\200</sevZPointContactAdd></customerPointInfo><customerPointInfo><cableCount>\347\224\265\350\267\257\346\225\260\351\207\2172</cableCount><bandwidth>\345\270\246\345\256\275</bandwidth><cityA>\345\237\216\345\270\202A</cityA><cityZ>\345\237\216\345\270\202Z</cityZ><SevAPointName>A\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\220\215\347\247\260</SevAPointName><SevZPointName>Z\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\220\215\347\247\260</SevZPointName><ASiteName>A\347\253\257\347\253\231\347\202\271\345\220\215\347\247\260</ASiteName><ZSiteName>Z\347\253\257\347\253\231\347\202\271\345\220\215\347\247\260</ZSiteName><portA>\347\253\257\347\202\271A</portA><portAInterfaceType>\347\253\257\347\202\271A\346\216\245\345\217\243\347\261\273\345\236\213</portAInterfaceType><portADetailAdd>\347\253\257\347\202\271A\350\257\246\347\273\206\345\234\260\345\235\200</portADetailAdd><portABDeviceName>\347\253\257\347\202\271A\344\270\232\345\212\241\350\256\276\345\244\207\345\220\215\347\247\260</portABDeviceName><portABDevicePort>\347\253\257\347\202\271A\344\270\232\345\212\241\350\256\276\345\244\207\347\253\257\345\217\243</portABDevicePort><portAContactPhone>\347\253\257\347\202\271A\350\201\224\347\263\273\344\272\272\345\217\212\347\224\265\350\257\235</portAContactPhone><portZ>\347\253\257\347\202\271Z</portZ><portZInterfaceType>\347\253\257\347\202\271Z\346\216\245\345\217\243\347\261\273\345\236\213</portZInterfaceType><portZDetailAdd>\347\253\257\347\202\271Z\350\257\246\347\273\206\345\234\260\345\235\200</portZDetailAdd><portZBDeviceName>\347\253\257\347\202\271Z\344\270\232\345\212\241\350\256\276\345\244\207\345\220\215\347\247\260</portZBDeviceName><portZBDevicePort>\347\253\257\347\202\271Z\344\270\232\345\212\241\350\256\276\345\244\207\347\253\257\345\217\243</portZBDevicePort><portZContactPhone>\347\253\257\347\202\271Z\350\201\224\347\263\273\344\272\272\345\217\212\347\224\265\350\257\235</portZContactPhone><sevAPointContact>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\344\272\272</sevAPointContact><sevAPointContactPhone>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\347\224\265\350\257\235</sevAPointContactPhone><sevAPointContactEmail>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\351\202\256\347\256\261</sevAPointContactEmail><sevAPointContactAdd>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\345\234\260\345\235\200</sevAPointContactAdd><sevZPointContact>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\344\272\272</sevZPointContact><sevZPointContactPhone>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\347\224\265\350\257\235</sevZPointContactPhone><sevZPointContactEmail>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\351\202\256\347\256\261</sevZPointContactEmail><sevZPointContactAdd>\344\270\232\345\212\241\346\216\245\345\205\245\347\202\271\345\256\242\346\210\267\350\201\224\347\263\273\345\234\260\345\235\200</sevZPointContactAdd></customerPointInfo> </customerPointInfos><recordInfo><fieldInfo><fieldChName>1</fieldChName><fieldEnName>11</fieldEnName><fieldContent>111</fieldContent></fieldInfo><fieldInfo><fieldChName>2</fieldChName><fieldEnName>22</fieldEnName><fieldContent>333</fieldContent></fieldInfo></recordInfo></opDetail>";
	XmlDom4jSX d = new XmlDom4jSX();
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
