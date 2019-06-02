<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.webtool.vo.WapDBConnectionVO" %>
<%
List list = (List)request.getAttribute("wapDBConnectionList");
String app = request.getContextPath();
%>
<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body  onload="javascript:top.leftFrame.reloadtree()">
	<table width="100%">
	 <tr>
	  <td class="title">ϵͳ�б�</td>
	   <td align="right">
	   ��<a href="<%=app %>/webtool/wapdbconnection/add.jsp">�������ӳ�</a>
	   </td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	 <tr>
	   <th width="15%">����</th>
	   <th width="15%">���ݿ�����</th>
	   <th width="25%">����</th>	   
	   <th width="15%">�޸Ĳ���</th>
	   <th width="15%">�鿴����</th>
	   <th width="15%">ɾ������</th>	      
	 </tr>
	 <%for(Iterator it = list.iterator(); it.hasNext();){ 
	     WapDBConnectionVO vo =(WapDBConnectionVO)it.next();
	 %>
	 <tr>
	   <th><%=vo.getDbName() %></th>
	   <td><%=vo.getDbType() %></td>
	   <td><%=vo.getDescription() %></td>
	   <td><a href="<%=app %>/webtool/wapdbconnection?act=view&flag=modify&dbId=<%=vo.getDbId()%>">�޸�</a></td>
	   <td><a href="<%=app %>/webtool/wapdbconnection?act=view&flag=view&dbId=<%=vo.getDbId()%>">�鿴</a></td>
	   <td><a href="<%=app %>/webtool/wapdbconnection?act=remove&dbId=<%=vo.getDbId()%>">ɾ��</a></td>	   	   
	 </tr>
	 <%} %>
	</table>
</body>
</html>