/*
 * Created on 2008-7-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.util.xml;

import java.io.FileNotFoundException;
import java.net.URL;


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

    /**
     * classpath��ʶ
     */
    public final static String CLASSPATH_FLAG = "classpath:";

    public static XMLProperties getBoco() {
        //if(xManage==null)
        xManage = new XmlManage();
        String path = xManage.getClass().getResource(CONFIG_FILENAME).getPath();
        System.out.println("path:" + path);

        XMLProperties properties = new XMLProperties();
        properties.loadFile(path);
        return properties;
    }

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
            path = getFilePathForUrl("classpath:" + temp);
            System.out.println("path=" + path);
        } catch (Exception err) {
            path = xManage.getClass().getResource(filePath).getPath();
            System.out.println("path=" + path);
        }
        XMLProperties properties = new XMLProperties();
        properties.loadFile(path);
        return properties;
    }

    public static XMLProperties getSchema(String xmlSchema) {
//	  	if(xManage==null)
        xManage = new XmlManage();
        XMLProperties properties = new XMLProperties();
        properties.loadSchema(xmlSchema);
        return properties;
    }

    private XmlManage() {

    }

    /**
     * ��java��ʱ���ص�·��
     *
     * @param filePath �ļ�·��
     * @return
     * @throws FileNotFoundException
     */
    public static String getFilePathForUrl(String filePath)
            throws FileNotFoundException {
        URL url = getFileUrl(filePath);
        return url.getFile();
    }

    /**
     * ��ȡfilePath��url
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException ����urlʧ�ܽ��׳�MalformedURLException
     */
    public static URL getFileUrl(String filePath) throws FileNotFoundException {
        URL url = null;
        if (filePath != null) {
            if (filePath.length() > CLASSPATH_FLAG.length()) {
                if (CLASSPATH_FLAG.equals(filePath.substring(0, CLASSPATH_FLAG
                        .length()))) {
                    // url =
                    // loader.getResource(filePath.substring(CLASSPATH_FLAG
                    // .length()));
                    try {
                        // ����urlʧ�ܽ��׳�MalformedURLException
                        // url = ClassLoaderUtil
                        // .getExtendResource(getPathButClasspath(filePath));
                        url = XmlManage.class.getClassLoader().getResource(
                                getPathButClasspath(filePath));

                        // url = new URL(URI.toString()
                        // + filePath.substring(CLASSPATH_FLAG.length()));
                    } catch (Exception e) {
                        throw new FileNotFoundException(filePath
                                + "is not found.");
                    }

                } else {
                    // TODO �����⣬���޸�
                }
            }
        }
        return url;
    }

    /**
     * ȥ��classpath
     *
     * @param path
     * @return
     */
    private static String getPathButClasspath(String path) {
        return path.substring(CLASSPATH_FLAG.length());
    }

}
