<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapHrefTag" %>
<%@ page autoFlush="true" %>
<%
WapHrefTag tag = (WapHrefTag)request.getAttribute("href");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String app = request.getContextPath(); 
%>
<html>
<head>
<title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
<table width="100%">
	 <tr>
	   <td class="title">��������Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">ҳ���ǩ�б�</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=href&class_str=<%=tag.getClassStr()%>">�޸Ĵ˱�ǩ</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">ɾ���˱�ǩ</a>
	   </td>
	 </tr>
	</table>
	<table class="form_table">
	<tr>
	    <th width="30%">�����ӵ�ַ��</td>
		<td><%=tag.getHref() %></td>
	</tr>
	<tr >
		<td width="30%">�������֣�</td>
        <td><%=tag.getText() %></td>
	</tr>
</form>
</body>
</html>
