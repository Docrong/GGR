package com.boco.eoms.commons.system.priv.a_fsh;

public class XmlPath {
private static String xmlpath="";
private static String type="";
public static String getType() {
	return type;
}

public static void setType(String type) {
	XmlPath.type = type;
}

public static String getXmlpath() {
	return xmlpath;
}

public static void setXmlpath(String xmlpath) {
	XmlPath.xmlpath = xmlpath +"/WEB-INF/classes/com/boco/eoms/commons/system/priv/config/Menu_Ip.xml";
}
}
