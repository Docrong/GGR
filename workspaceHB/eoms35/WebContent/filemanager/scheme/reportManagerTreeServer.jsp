<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.filemanager.ReportTree"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<%
  ReportTree templet=new ReportTree(request.getRealPath("/filemanager/topic.xml"));
  String outXml=templet.getSchemeTreeXml("open","block");
  out.print(outXml);
%>