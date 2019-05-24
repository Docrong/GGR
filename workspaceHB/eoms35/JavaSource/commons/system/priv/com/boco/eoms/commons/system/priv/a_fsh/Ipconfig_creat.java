package com.boco.eoms.commons.system.priv.a_fsh;

public class Ipconfig_creat {
public static Ipconfig getIpconfig(){
	Ipconfig ipconfig = new Ipconfig();
	try {
		ipconfig = (Ipconfig) ParseXMLFactory.getParseXML().xml2object(
				Ipconfig.class, XmlPath.getXmlpath());
	} catch (ParseXMLException e) {
		// TODO 自动生成 catch 块
		e.printStackTrace();
	}
	return ipconfig;
}
}
