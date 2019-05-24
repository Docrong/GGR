<%@ page contentType="text/html; charset=ISO8859_1" %><%@page language="java" import="com.jspsmart.upload.*,mcs.common.db.*,java.sql.*,java.util.*,java.util.Date"%><%  
  
	//request.setCharacterEncoding("GBK");
	request.getParameter("gb2312");
	SmartUpload mySmartUpload=new SmartUpload();
	String name=request.getParameter("name");
	//out.println("name"+name);
 	 //name=java.net.URLEncoder.encode(name);
	String name_one=name.substring(name.lastIndexOf("_")+1,name.length());
	String name_two=name.substring(name.lastIndexOf(".")+1,name.length());
	//String url="/duty/upload/"+name_one;
	String url=request.getContextPath()+"/duty/upload/"+ name;
	// out.println("name"+url);
  	 mySmartUpload.initialize(pageContext);
 	 if(name_two.equals("doc"))
 	{
 		 response.sendRedirect(url);
 	}
 	else
 	{
 		mySmartUpload.setContentDisposition(null);
 		mySmartUpload.downloadFile(url);
 	}  
   	     %>