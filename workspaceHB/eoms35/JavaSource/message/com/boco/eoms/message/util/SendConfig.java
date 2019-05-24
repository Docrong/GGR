package com.boco.eoms.message.util;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SendConfig
{
	public String transactionID;
	public String VASID;
	public String VASPID;
	public String serviceCode;
	public String SenderAddress;
	
	public static SendConfig sc;
	
	public SendConfig(){}
	
	public SendConfig(String transactionID,String VASID,
			String VASPID,String serviceCode,String SenderAddress)
	{
		this.transactionID = transactionID;
		this.VASID = VASID;
		this.VASPID = VASPID;
		this.serviceCode = serviceCode;
		this.SenderAddress = SenderAddress;
	}
	
	public static SendConfig getSendConfig(String path) throws DocumentException
	{
		if(sc!=null)
		{
			return sc;
		}
		
		Document document = new SAXReader().read(new File(path));
		Element root = document.getRootElement();
		List MMConfigmessage = root.selectNodes("MMConfig-message");
		
		Element config = ((Element)(MMConfigmessage.get(0)));
		String transactionID = config.element("transactionID").getTextTrim();
		String VASID = config.element("VASID").getTextTrim();
		String VASPID = config.element("VASPID").getTextTrim();
		String serviceCode = config.element("serviceCode").getTextTrim();
		String SenderAddress = config.element("SenderAddress").getTextTrim();
		
		return new SendConfig(transactionID,VASID,VASPID,serviceCode,SenderAddress);
	}
}
