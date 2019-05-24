<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.webtool.vo.WapDBConnectionVO" %>
<%
List list = (List)request.getAttribute("wapDBConnectionList");
String app = request.getContextPath();
%>
<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body  onload="javascript:top.leftFrame.reloadtree()">
	<table width="100%">
	 <tr>
	  <td class="title">系统列表</td>
	   <td align="right">
	   　<a href="<%=app %>/webtool/wapdbconnection/add.jsp">新增连接池</a>
	   </td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	 <tr>
	   <th width="15%">名称</th>
	   <th width="15%">数据库类型</th>
	   <th width="25%">描述</th>	   
	   <th width="15%">修改操作</th>
	   <th width="15%">查看操作</th>
	   <th width="15%">删除操作</th>	      
	 </tr>
	 <%for(Iterator it = list.iterator(); it.hasNext();){ 
	     WapDBConnectionVO vo =(WapDBConnectionVO)it.next();
	 %>
	 <tr>
	   <th><%=vo.getDbName() %></th>
	   <td><%=vo.getDbType() %></td>
	   <td><%=vo.getDescription() %></td>
	   <td><a href="<%=app %>/webtool/wapdbconnection?act=view&flag=modify&dbId=<%=vo.getDbId()%>">修改</a></td>
	   <td><a href="<%=app %>/webtool/wapdbconnection?act=view&flag=view&dbId=<%=vo.getDbId()%>">查看</a></td>
	   <td><a href="<%=app %>/webtool/wapdbconnection?act=remove&dbId=<%=vo.getDbId()%>">删除</a></td>	   	   
	 </tr>
	 <%} %>
	</table>
</body>
</html>