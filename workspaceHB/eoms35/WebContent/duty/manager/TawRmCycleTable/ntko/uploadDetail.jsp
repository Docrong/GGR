<%@ page contentType="text/html;charset=gb2312" %>
<%@ page language="java" import="java.sql.*,java.util.*,com.jspsmart.upload.*"%>
 
<%@ page import="com.jspsmart.upload.SmartUpload" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%
	 
	SmartUpload mySmartUpload = new SmartUpload();
    
	// Initialization
 	mySmartUpload.initialize(pageContext);
	// Upload
	mySmartUpload.upload();  
	com.jspsmart.upload.File myFile = null;
	myFile = mySmartUpload.getFiles().getFile(1); 
	myFile.saveAs(request.getParameter("filePath")); 

%>