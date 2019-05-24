<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.base.node.WapNode" %>

<%
List nodeList = (List)request.getAttribute("nodeList");
String app = request.getContextPath();
%>
<html>
<head>
<head>
<title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body onload="javascript:top.leftFrame.location.reload();">
	<table width="100%">
	 <tr>
	   <td class="title">系统列表</td>
	   <td align="right"><a href="<%=app %>/webtool/wapnode/add.jsp">新增系统</a></td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	 <tr>
	   <th width="25%">系统名称1</th>
	   <th width="30%">系统描述</th>	
	   <th width="15%">修改操作</th>
	   <th width="15%">查看操作</th>
	   <th width="15%">删除操作</th>
	      
	 </tr>
	 <%
	 Boolean rowCount = true;
	 String thClass = "";
	 String tdClass = "";
	 for(Iterator it = nodeList.iterator(); it.hasNext();){ 
	     //WapNodeVO wapNodeVO =(WapNodeVO)it.next();
	     WapNode wapNodeVO =(WapNode)it.next();
	     if(rowCount){
	       thClass = "spec";
	       tdClass = "";
	     }else{
	       thClass = "specalt";
	       tdClass = "alt";	       
	     }
	 %>
	 <tr>
	   <th class="<%=thClass%>"><%=wapNodeVO.getName() %></td>
	   <td class="<%=tdClass%>"><%=wapNodeVO.getDescription() %></td>
	   <td class="<%=tdClass%>"><a href="<%=app %>/webtool/wapnode?act=view&flag=modify&wapNodeId=<%=wapNodeVO.getNodeId()%>">修改</a></td>
	   <td class="<%=tdClass%>"><a href="<%=app %>/webtool/wapnode?act=view&flag=view&wapNodeId=<%=wapNodeVO.getNodeId()%>">查看</a></td>
	   <td class="<%=tdClass%>"><a href="<%=app %>/webtool/wapnode?act=remove&wapNodeId=<%=wapNodeVO.getNodeId()%>">删除</a></td>	   	   
	 </tr>
	 <%
	 	rowCount = !rowCount;
	 } %>
	</table>
</body>
</html>