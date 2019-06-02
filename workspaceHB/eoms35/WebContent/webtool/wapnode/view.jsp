<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.base.node.WapNode,
                com.boco.eoms.mobilewap.base.card.imp.WapCard" %>

<%

WapNode wapNodeVO = (WapNode)request.getAttribute("wapNodeVO");
List cardList = (List)request.getAttribute("cardList");
String app = request.getContextPath();
%>
<html>
<head><title>管理员WAP配置工具</title>

<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
	<table width="100%">
	 <tr>
	   <td class="title">系统信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card/add.jsp?wapNodeId=<%=wapNodeVO.getNodeId()%>">新增页面</a> |
	     <a href="<%=app %>/webtool/wapnode?act=view&flag=modify&wapNodeId=<%=wapNodeVO.getNodeId()%>">修改</a> |
	     <a href="<%=app %>/webtool/wapnode?act=remove&wapNodeId=<%=wapNodeVO.getNodeId()%>">删除</a>
	   </td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th width="30%">系统名称</th>
	   <td><%=wapNodeVO.getName() %></td>
	 </tr>
	 <tr>
	   <th>系统描述</th>
	   <td><%=wapNodeVO.getDescription() %></td>
	 </tr>
 
	 <tr>
	   <th>使用数据库</th>
	   <td><%=wapNodeVO.getDbName()%></td>
	 </tr>
	 <tr>

	   <th>系统首页</th>
	   <td><%=wapNodeVO.getName()%></td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	<tr><td colspan="3" class="nobg">标签列表</td></tr>
	 <tr>
	   <th width="30%">页面名称</th>
	   <th width="25%">页面ID</th>
	   <th width="45%">页面描述</th>	   
	 </tr>
	 <%for(Iterator it = cardList.iterator(); it.hasNext();){ 
	 
	     //CardVO cardVO =(CardVO)it.next();
	     WapCard cardVO =(WapCard)it.next();
	 %>
	 <tr>
	   <th class="spec"><%=cardVO.getName() %>&nbsp;</th>
	   <td><%=cardVO.getCardId() %>&nbsp;</td>
	   <td><%=cardVO.getDescription() %>&nbsp;</td>
	 </tr>
	 <%} %>
	</table>
</body>
</html>