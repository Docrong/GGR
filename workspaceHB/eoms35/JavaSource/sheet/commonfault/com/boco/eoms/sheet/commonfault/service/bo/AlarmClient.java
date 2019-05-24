// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmClient.java

package com.boco.eoms.sheet.commonfault.service.bo;

import com.boco.alarm.service.sheetStateSync.SheetStateSync3SoapServiceSoapBindingStub;
import com.boco.alarm.service.sheetStateSync.SheetStateSync3SoapServiceLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.chinamobile.eoms.service.SheetStateSync3Locator;
import com.chinamobile.eoms.service.SheetStateSync3SoapStub;
import java.io.PrintStream;
import java.net.URL;
import java.util.Date;
import org.dom4j.*;

public class AlarmClient
{

	private static SheetStateSync3SoapServiceSoapBindingStub tyBinding = null;
	private static SheetStateSync3SoapStub binding = null;

	public AlarmClient()
	{
	}

	public static String syncSheetState(String serSupplier, String opDetail)
		throws Exception
	{
		System.out.println("接口 opDetail = "+opDetail);
		String result = "";
		loadSheetStateService(serSupplier);
		if (binding != null){
			result = binding.syncSheetState(serSupplier, "", "", "", opDetail);
		    System.out.println("接口11result = "+"result");
		    
		}else if(tyBinding != null){
			result = tyBinding.syncSheetState(serSupplier, "", "", "", opDetail);
		 System.out.println("接口 2result = "+"result");
		}
		return result;
	}

	private static void loadSheetStateService(String serSupplier)
		throws Exception
	{
		System.out.println("start loadSheetStateService");
		String ServiceRooter = XmlManage.getFile("/config/commonfault-util.xml").getProperty("ServiceRooter");
		System.out.println("告警服务提供方 ServiceRooter:" + ServiceRooter);
		String filePath = StaticMethod.getFilePathForUrl("classpath:config/commonfault-util.xml");
		String getIrmsStr = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode(filePath, "serviceUrl", serSupplier);
		System.out.println("告警服务地址 getIrmsStr:" + getIrmsStr);
		URL alarmCreateUrl = new URL(getIrmsStr);
		int ms = 0;
		String timeout = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("connecttimeout"));
		if (timeout.length() > 0)
			try
			{
				ms = Integer.parseInt(timeout);
			}
			catch (Exception err)
			{
				System.out.println("没有找到接口超时设置，使用默认值");
			}
		if (ServiceRooter != null && ServiceRooter.equals("tongyong"))
		{
			tyBinding = (SheetStateSync3SoapServiceSoapBindingStub)(new SheetStateSync3SoapServiceLocator()).getSheetStateSync3SoapPort(alarmCreateUrl);
			if (ms > 0)
				tyBinding.setTimeout(ms);
			System.out.println("获得接口连接---通用");
		} else
		{
			binding = (SheetStateSync3SoapStub)(new SheetStateSync3Locator()).getSheetStateSync3Soap(alarmCreateUrl);
			if (ms > 0)
				binding.setTimeout(ms);
		}
	}

	private static void buildEmosXML(Element recordInfo, String cnname, String name, Object object)
	{
		Element filedInfo = recordInfo.addElement("fieldInfo");
		Element fieldCnName = filedInfo.addElement("fieldCnName");
		fieldCnName.setText(cnname);
		Element fieldEnName = filedInfo.addElement("fieldEnName");
		fieldEnName.setText(name);
		Element fieldContent = filedInfo.addElement("fieldContent");
		fieldContent.setText(object.toString());
	}

	public static void main(String args[])
	{
		try
		{
			SheetStateSync3SoapServiceLocator locator = new SheetStateSync3SoapServiceLocator();
	//		locator.setSheetStateSync3SoapPortEndpointAddress("http://10.30.198.164:9000/SheetStateSync");
			locator.setSheetStateSync3SoapPortEndpointAddress("http://10.30.172.15:9000/SheetStateSync");
			SheetStateSync3SoapServiceSoapBindingStub binding = (SheetStateSync3SoapServiceSoapBindingStub)locator.getSheetStateSync3SoapPort();
			binding.setTimeout(60000);
			String alive = binding.isAlive();
			Document dom4jDoc = DocumentHelper.createDocument();
			Element opDetailElement = dom4jDoc.addElement("opDetail");
			Element recordInfo = opDetailElement.addElement("recordInfo");
			buildEmosXML(recordInfo, "网管告警ID", "alarmId", "BOCO_WNMS_762591260_2256541824_1497814915_3274358119");
			buildEmosXML(recordInfo, "EOMS工单ID", "sheetNo", "HB-051-140528-11072");
			buildEmosXML(recordInfo, "工单状态", "sheetStatus", "T1");
			buildEmosXML(recordInfo, "状态时间", "statusTime", (new Date()).toString());
			String ret = binding.syncSheetState("boco", "boco", "boco", "boco", dom4jDoc.asXML());
			System.out.println(ret);
			System.out.println(alive);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
