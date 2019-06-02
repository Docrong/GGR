<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%@ page isErrorPage="true"%>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/comm.css" type="text/css">
<TITLE>提示信息</TITLE>
</HEAD>
<BODY topmargin="0" leftmargin="0" background="<%=request.getContextPath()%>/images/img/bg001.gif" >
<br>
<br>
<br>
<%
  String msg1 = request.getParameter("msg");
  String msg = new String(msg1.getBytes("ISO-8859-1"),"GB2312");
%>
<div align="center"><font color="BLUE"><%=msg%></font></div>
</BODY>
</HTML>
