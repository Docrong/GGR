/*
 * Created on 2008-7-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.util.xml;

import com.boco.eoms.sheet.base.util.flowdefine.xml.StaticMethod;

/**
 * @author xqz
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XmlManage {
    private static final String CONFIG_FILENAME = "/config/boco.xml";

    /**
     * XML properties to actually get and set the BOCO properties.
     */
    private static XmlManage xManage = null;

    /*
     * 加载boco.xml
     */
    public static XMLProperties getBoco() {
        //if(xManage==null)
        xManage = new XmlManage();
        String path = xManage.getClass().getResource(CONFIG_FILENAME).getPath();
        System.out.println("path:" + path);

        XMLProperties properties = new XMLProperties();
        properties.loadFile(path);
        return properties;
    }

    /*
     * 加载一个xml文件
     * @param /filePath xml文件的相对路径，"/"代表web-inf/classess
     */
    public static XMLProperties getFile(String filePath) {
//	  	if(xManage==null)
	  	/*	xManage = new XmlManage();
	  	String path = xManage.getClass().getResource(filePath).getPath();
	  	XMLProperties properties = new XMLProperties();
	  	properties.loadFile(path);
	    return properties;*/
        xManage = new XmlManage();
        String path = "";
        String temp = filePath;
        try {
            if (filePath.indexOf("/") == 0)
                temp = filePath.substring(1);
            path = StaticMethod.getFilePathForUrl("classpath:" + temp);
            System.out.println("path=" + path);
        } catch (Exception err) {
            path = xManage.getClass().getResource(filePath).getPath();
            System.out.println("path=" + path);
        }
        XMLProperties properties = new XMLProperties();
        properties.loadFile(path);
        return properties;
    }

    /*
     * 解析schema
     */
    public static XMLProperties getSchema(String xmlSchema) {
//	  	if(xManage==null)
        xManage = new XmlManage();
        XMLProperties properties = new XMLProperties();
        properties.loadSchema(xmlSchema);
        return properties;
    }

    private XmlManage() {

    }

//	  /*
//	   * 获取一个node的text值
//	   * @param node 节点名称
//	   * @return
//	   */
//	  public String getProperty(String node) {
//	    return properties.getProperty(node);
//	  }
//
//	  public String getProperty(String name, String value) {
//	    return properties.getProperty(name) != null ? properties.getProperty(name) :
//	        value;
//	  }
//
//	  public String getProperty(String name, int Index) {
//	    return properties.getProperty(name, Index);
//	  }
//
//	  public String[] getChildrenProperties(String parent) {
//	    return properties.getChildrenProperties(parent);
//	  }
//
//	  public String getAttribute(String name, int Index, String Attribute) {
//	    return properties.getAttribute(name, Index, Attribute);
//	  }
//	  /*
//	   * 获取一个node的Attribute
//	   * @param node 节点名称
//	   * @param Attribute 属性名
//	   * @return 属性值
//	   */
//	  public String getAttribute(String node, String Attribute) {
//	    return properties.getAttribute(node, Attribute);
//	  }
//
//	  public XMLProperties getXMLProperties() {
//	    return properties;
//	  }

}
