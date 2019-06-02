<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="org.w3c.dom.Document,
                 java.io.InputStream,
                 org.w3c.dom.Element,
                 com.boco.eoms.filemanager.XmlUtil,
                 com.boco.eoms.db.util.ConnectionPool"%>
<%@ page import="com.boco.eoms.filemanager.WorkSheetAccess"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<%
 String userId="";
 String flowId="";
 String type="";
 String strDoc="";
 String auditInfo="";
  try{
    Document temDoc = XmlUtil.getDocument((InputStream)request.getInputStream());
    if(temDoc!=null){
        Element root = temDoc.getDocumentElement();
        userId= root.getAttribute("userId");
        flowId = root.getAttribute("flowId");
        type = root.getAttribute("type");
        auditInfo = root.getAttribute("auditInfo");
   }
       WorkSheetAccess access=new WorkSheetAccess(ConnectionPool.getInstance().getConnection());
       if(type.equals("reject1")){
        strDoc=access.rejectReport(userId,flowId,auditInfo);
       }else if(type.equals("pass1")){
       	strDoc=access.passReport(userId,flowId,auditInfo);
       }else if(type.equals("reject2")){
       	strDoc=access.rejectReport2(userId,flowId,auditInfo);
       }else if(type.equals("pass2")){
       	strDoc=access.passReport2(userId,flowId,auditInfo);
       }else if(type.equals("reject3")){
       	strDoc=access.rejectReport3(userId,flowId,auditInfo);
       }else if(type.equals("pass3")){
       	strDoc=access.passReport3(userId,flowId,auditInfo);
       }
        access.release();
//        System.out.println(strDoc);
        out.print(strDoc);
  }catch(Exception ex){
    ex.printStackTrace();
  }
%>