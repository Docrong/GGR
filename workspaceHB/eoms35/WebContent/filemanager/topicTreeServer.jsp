<%@ page import="com.boco.eoms.filemanager.ReportTree"%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<%
  ReportTree templet=new ReportTree(request.getRealPath("/filemanager/topic.xml"));
  String outXml=templet.getReportTopicXml("open","block");
  out.print(outXml);
%>