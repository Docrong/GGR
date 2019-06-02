<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%
String app = request.getContextPath();
String temp1= app+"/webtool/scripts/wap.jsp"; 
String temp2= app+"/webtool/nodelist";
%>
<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body id="index">
	<table width="100%" height="100%" cellspacing="1" cellpadding="0" id="index_container">
	 <tr>
	   <td colspan="2" id="index_header">
	     <img src="<%=app%>/webtool/styles/images/edit.gif" align="absmiddle"> 管理员WAP配置工具
	   </td></tr>
	 <tr>
	   <td width="205" height="100%" align="center" id="index_left">
	     <iframe id="leftFrame" name="leftFrame" frameborder=0 src="<%=temp1%>" scrolling="no"></iframe></td>	
	   <td width="100%" height="100%" id="index_right">
	     <iframe id="rightFrame" name="rightFrame" frameborder=0 src="<%=temp2%>" scrolling="auto"></iframe></td>
	 </tr>
	</table>
</body>
</html>