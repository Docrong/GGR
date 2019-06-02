<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.base.tag.WapTagInterface,
                com.boco.eoms.mobilewap.base.card.imp.WapCard" %>
<%
WapCard vo = (WapCard)request.getAttribute("cardVO");
List list = (List)request.getAttribute("wmlTagList");
String app = request.getContextPath(); 
String wapNodeId = (String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
%>
<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
   	<table width="100%">
	 <tr>
	   <td class="title">页面信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapNodeId=<%=wapNodeId%>&wapCardId=<%=wapCardId%>">修改此页面</a>
	    |<a href="<%=app %>/webtool/card?act=remove&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>">删除此页面</a>	   
	   </td>
	 </tr>
	</table>

	<table class="form_table">
	<tr>
	   <th width="30%">页面ID</th>
	   <td width="70%">
	   <%if(vo!=null){ %>
	     <%=vo.getCardId() %>&nbsp;
	   <%}else{%>
	     <%="" %>&nbsp;
	   <%} %>
	   </td>
	 </tr>
	 <tr>
	   <th>页面名称</th>
	   <td>
	   <%if(vo!=null){ %>
	     <%=vo.getName() %>&nbsp;
	   <%}else{%>
	     <%="" %>&nbsp;
	   <%} %>
	   </td>
	 </tr>
	 <tr>
	   <th>页面描述</th>
	   <td width="70%">
	   <%if(vo!=null){ %>
	     <%=vo.getDescription() %>&nbsp;
	   <%}else{%>
	     <%="" %>&nbsp;
	   <%} %>
	   </td>
	 </tr>
	 <tr>
	   <th>页面title</th>
	   <td>
	   <%if(vo!=null){ %>
	    <%=vo.getTitle() %>&nbsp;
	   <%}else{%>
	     <%="" %>&nbsp;
	   <%} %>
       </td>
	 </tr>
	</table>
	<br/>
	<table width="100%"  class="list_table">
	<tr>
	   <th width="30%">标签名称</th>
	   <th>所在页面中位置排序</th>	
    </tr>
	 <%
	 if(list!=null){
	 for(Iterator it = list.iterator(); it.hasNext();){ 
	     WapTagInterface tagVo =(WapTagInterface)it.next();
	 %>
	 <tr>
	   <th class="spec"><%=tagVo.getType() %></td>
	   <td><%=tagVo.getOrderId() %></td>   	 	   
	 </tr>
	 <%}} %>
	</table>
</body>
</html>