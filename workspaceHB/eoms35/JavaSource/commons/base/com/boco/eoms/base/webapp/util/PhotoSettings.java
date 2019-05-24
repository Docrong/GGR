package com.boco.eoms.base.webapp.util;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.workbench.networkcalendar.webapp.util.networkcalendarMgrLocator;
public class PhotoSettings {

	/**
	 * @param args
	 */
	 public static String readValue(String key) {
		  Properties props = new Properties();
		        try {
		        	String propertiesName = UtilMgrLocator.getEOMSAttributes().getPropertiesFilePath();
		        	String  path=StaticMethod.getFilePath("classpath:config/ApplicationResources_"+propertiesName+".properties");
//		        	String propertiesFilePath = UtilMgrLocator.getEOMSAttributes().getPropertiesFilePath();
					
		         InputStream in = new BufferedInputStream (new FileInputStream(path));
		         props.load(in);
		         String value = props.getProperty (key);
		            System.out.println(key+value);
		            return value;
		        } catch (Exception e) {
		         e.printStackTrace();
		         return null;
		        }
		 }
	public static void main(String[] args) {
		PhotoSettings photoSettings =new PhotoSettings();
		photoSettings.readValue("title");

	}

}
