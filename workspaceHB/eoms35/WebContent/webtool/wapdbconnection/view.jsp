<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="com.boco.eoms.mobilewap.webtool.vo.WapDBConnectionVO" %>
<%
WapDBConnectionVO vo = (WapDBConnectionVO)request.getAttribute("wapDBConnectionVO");
String app = request.getContextPath();
%>
<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body onload="javascript:top.leftFrame.location.reload()">
	<table width="100%">
	 <tr>
	   <td class="title">系统信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/wapdbconnection?act=view&flag=modify&dbId=<%=vo.getDbId()%>">修改</a>
        |<a href="<%=app %>/webtool/wapdbconnection?act=remove&dbId=<%=vo.getDbId()%>">删除</a>
       </td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th width="30%">连接池名称</th>
	   <td><%=vo.getDbName() %></td>
	 </tr>
	 <tr>
	   <th>连接池描述</th>
	   <td>
	     <%=vo.getDescription() %>
	   </td>
	 </tr>
	 <tr>
	   <th>数据库类型</th>
	   <td>
	    <%=vo.getDbType() %>
       </td>
	 </tr>
	 <tr>
	   <th>字符集设置</th>
	   <td><%=vo.getDB_Charset() %></td>
	 </tr>
	 <tr>
	   <th>连接池URL</th>
	   <td><%=vo.getDbConnectionUrl() %>
	   </td>
	 </tr>
	 <tr>
	   <th>用户名</th>
	   <td><%=vo.getUserName()%>
	   </td>
	 </tr>
	 <tr>
	   <th>密码</th>
	   <td><%=vo.getPassword()%>
	   </td>
	 </tr>
	</table>
</body>
</html>