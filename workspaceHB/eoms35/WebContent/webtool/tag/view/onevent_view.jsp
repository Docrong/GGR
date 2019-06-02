<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapOneventTag" %>
<%@ page autoFlush="true" %>
<%
WapOneventTag tag = (WapOneventTag)request.getAttribute("onevent");
String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String app = request.getContextPath(); 
%>
<html>
<head>
<title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
  <table width="100%">
	 <tr>
	   <td class="title">定时跳转信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">页面标签列表</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=onevent&class_str=<%=tag.getClassStr()%>">修改此标签</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">删除此标签</a>
       </td>
	 </tr>
  </table>
  <table class="form_table">
	<tr>
		<td width="100%" colspan="2"><font color="#FF0000">*注：1秒等于10毫秒</font></td>
	</tr>
    <tr>
		<th width="30%">定时时间：</td>
		<td><%=tag.getValue() %>毫秒</td>
	</tr>
	<tr>
		<th width="30%">跳转页面ID</td>
		<td><%=tag.getHref() %></td>
	</tr>
</table>
</form>
</body>
</html>
