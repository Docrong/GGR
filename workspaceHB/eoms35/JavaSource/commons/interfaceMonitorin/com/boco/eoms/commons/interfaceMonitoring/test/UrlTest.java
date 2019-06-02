package com.boco.eoms.commons.interfaceMonitoring.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlTest {

	/**
	 * @param args
	 */
	
	public String getContent(String strUrl)
	 // 一个public方法，返回字符串，错误则返回"error open url"
	 {
		String result="";
	  try{
	   
	   URL url=new URL(strUrl);
	   BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
	   String s="";
	   
	   StringBuffer sb=new StringBuffer("");
	   while((s=br.readLine())!=null)
	   {     
	    sb.append(s+"\r\n");    
	   }
	   br.close();
	   result="Success";
	  }
	  catch(Exception e){
		  result="Unsuccessful"; 
	   
	  }
	  return result;
	 }

	public static void main(String[] args) {
		UrlTest ou=new UrlTest();
		  System.out.println(ou.getContent("http://localhost:8080/EOMS_J2EE/services/InterSwitchAlarms"));
		

	}

}
