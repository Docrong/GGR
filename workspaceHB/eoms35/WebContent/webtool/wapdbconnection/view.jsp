<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="com.boco.eoms.mobilewap.webtool.vo.WapDBConnectionVO" %>
<%
WapDBConnectionVO vo = (WapDBConnectionVO)request.getAttribute("wapDBConnectionVO");
String app = request.getContextPath();
%>
<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body onload="javascript:top.leftFrame.location.reload()">
	<table width="100%">
	 <tr>
	   <td class="title">ϵͳ��Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/wapdbconnection?act=view&flag=modify&dbId=<%=vo.getDbId()%>">�޸�</a>
        |<a href="<%=app %>/webtool/wapdbconnection?act=remove&dbId=<%=vo.getDbId()%>">ɾ��</a>
       </td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th width="30%">���ӳ�����</th>
	   <td><%=vo.getDbName() %></td>
	 </tr>
	 <tr>
	   <th>���ӳ�����</th>
	   <td>
	     <%=vo.getDescription() %>
	   </td>
	 </tr>
	 <tr>
	   <th>���ݿ�����</th>
	   <td>
	    <%=vo.getDbType() %>
       </td>
	 </tr>
	 <tr>
	   <th>�ַ�������</th>
	   <td><%=vo.getDB_Charset() %></td>
	 </tr>
	 <tr>
	   <th>���ӳ�URL</th>
	   <td><%=vo.getDbConnectionUrl() %>
	   </td>
	 </tr>
	 <tr>
	   <th>�û���</th>
	   <td><%=vo.getUserName()%>
	   </td>
	 </tr>
	 <tr>
	   <th>����</th>
	   <td><%=vo.getPassword()%>
	   </td>
	 </tr>
	</table>
</body>
</html>