package com.boco.eoms.sheet.base.util;

import com.boco.eoms.common.properties.XMLProperties;

public class XMLPropertyFile {
	
	  private static XMLPropertyFile prop = null;
	  private static XMLProperties properties = null;

	  public static XMLPropertyFile getInstance(String filePath) {
	    if (properties == null)
	      prop = new XMLPropertyFile(filePath);
	    return prop;
	  }

	  private XMLPropertyFile(String filePath) {
	    loadProperties(filePath);
	  }

	  public String getProperty(String name) {
	    return properties.getProperty(name);
	  }

	  public String getProperty(String name, String value) {
	    return properties.getProperty(name) != null ? properties.getProperty(name) :
	        value;
	  }

	  public String getProperty(String name, int Index) {
	    return properties.getProperty(name, Index);
	  }

	  public String[] getChildrenProperties(String parent) {
	    return properties.getChildrenProperties(parent);
	  }

	  public String getAttribute(String name, int Index, String Attribute) {
	    return properties.getAttribute(name, Index, Attribute);
	  }
	
	  private synchronized static void loadProperties(String filePath) {
	    if (properties == null) {
	      properties = new XMLProperties(filePath);	    
	  }
	}
}
