package com.boco.eoms.commons.statistic.customstat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

public class CustomVisitor extends VisitorSupport
{
	private Map excelConfigMap = null;
	private Map queryCongigMap = null;
	
	public void visit(Element element)
	{
		super.visit(element);
		String name = element.getName();
		if("property".equalsIgnoreCase(name))
		{
			Attribute attributechart = element.attribute("name");
			String attributeValue = attributechart.getValue();
			if("excelConfigMap".equalsIgnoreCase(attributeValue))
			{
				excelConfigMap= initConfigMap(element);
			}
			else if("queryCongigMap".equalsIgnoreCase(attributeValue))
			{
				queryCongigMap = initConfigMap(element);
			}
		}
	}
	
	private Map initConfigMap(Element element)
	{
		Map configMap = new HashMap();
		
		Element map = element.element("map");
		List list = map.elements();
		for (int i = 0; i < list.size(); i++) {
			Element entry = (Element)list.get(i);
			String key = entry.attributeValue("key");
			Element valueE = entry.element("value");
			String value = valueE.getTextTrim();
			configMap.put(key, value);
		}
		
		return configMap;
	}

	public Map getExcelConfigMap() {
		return excelConfigMap;
	}

	public Map getQueryCongigMap() {
		return queryCongigMap;
	}
}