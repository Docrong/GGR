<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="com.boco.eoms.mobilewap.base.tag.imp.WapInputTag" %>
<%
WapInputTag tag = (WapInputTag)request.getAttribute("input");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String app = request.getContextPath(); 
%>
<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
  <table width="100%">
	 <tr>
	   <td class="title">输入框信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">页面标签列表</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=input&class_str=<%=tag.getClassStr()%>">修改此标签</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">删除此标签</a>
       </td>
	 </tr>
	</table>
	<table class="form_table">
	<tr>
	  <th width="30%">输入框名称</td>
	  <td><%=tag.getName() %></td>
	</tr>
	<tr>
	  <th width="30%">输入框类型</td>
	  <td><%=tag.getType()%></td>
	</tr>
	<tr>
	  <th width="30%">默认值</td>
	  <td><%=tag.getValue()%></td>
	</tr>
	<tr>
	  <th width="30%">输入格式要求</td>
	  <td><%=tag.getFormat() %></td>
	</tr>
	<tr>
	  <th width="30%">是否允许为空</td>
	  <td>
	    <%if((tag.getEmptyok()+"").equals("true"))out.println("可以");
	      else out.println("不可以");
	    %>	      
	  </td>
	</tr>
	<tr>
	  <th width="30%">输入框大小</td>
	  <td><%=(tag.getSize().equals("0"))?"":tag.getSize() %></td>
	</tr>
	<tr>
	  <th width="30%">输入最大字符数</td>
	  <td><%=(tag.getMaxlength().equals("0"))?"":tag.getMaxlength()%></td>
	</tr>
  </table>
</body>
</html>
	
