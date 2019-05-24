package com.boco.eoms.sheet.base.util;

import java.io.InputStream;
import java.util.Properties;

import com.boco.eoms.common.properties.XMLProperties;
//import com.ibm.bpe.util.Base64;


/**
 * 
 * @author IBM_USER
 *
 */
public class PropertyFile {
	private static PropertyFile prop = null;
	private static Properties properties=null;
	
	private PropertyFile(String fileName){
		loadProperties(fileName);
	}
	private void loadProperties(String fileName){
	  if(properties==null){
	  	properties=new Properties();
	   }
		 InputStream in = null;
		 try {
		      in = getClass().getResourceAsStream(fileName);
		      properties.load(in);
		    }
		    catch (Exception e) {
		       e.printStackTrace();
		    }
		    finally {
		      try {
		        if (in != null) {
		          in.close();
		        }
		      }
		      catch (Exception e) {}
		    }	
	}
	public static PropertyFile getInstance(String fileName){	  
      if (properties == null){   	   
		 prop = new PropertyFile(fileName);
		 System.out.println("properties is null");
      }
      else {
    	  String propertiesFile=SheetStaticMethod.nullObject2String(
    			  properties.getProperty("propertiesFile")); 
    	  if(!fileName.equals(propertiesFile)){
    		  prop = new PropertyFile(fileName);
    		  properties.setProperty("propertiesFile", fileName); 
    		  System.out.println("properties is not equal");
    	  }  	  
      }		 
      return prop;
	}
	
	public static String getPropertyByName(String key){
		String value=properties.getProperty(key);
		return value;
	}
	
//	public static void main(String[] args) {
//		try {
//			String taskConfigPath="/config/urgentfaultTaskConfig.properties";
//			PropertyFile pop=getInstance(taskConfigPath);
//			pop=getInstance(taskConfigPath);
//			taskConfigPath="/config/commonfaultTaskConfig.properties";
//			pop=getInstance(taskConfigPath);	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
