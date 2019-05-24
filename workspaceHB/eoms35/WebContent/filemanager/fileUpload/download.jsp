<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.common.resource.Util"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page language="java"%>
<jsp:directive.page import="com.boco.eoms.filemanager.extra.fileupload.SmartUpload"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<%
   SmartUpload mySmartUpload = new SmartUpload();
    // Initialization
   mySmartUpload.initialize(pageContext);  
     String showName= StaticMethod.dbNull2String(request.getParameter("showName"));
    // Download file
      String path=Util.UNI2GBK(request.getParameter("url"));
    // With a physical path
//    String fileName=Util.GBK2UNI(path.substring(path.lastIndexOf("/")+1,path.length()));

//    fileName=utilBean.repalceDangerName(fileName);
    // With options
    mySmartUpload.downloadFile(path,"application/x-msdownload", showName);
%>
