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
<title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body onload="javascript:top.leftFrame.location.reload();">
	<table width="100%">
	 <tr>
	   <td class="title">ϵͳ�б�</td>
	   <td align="right"><a href="<%=app %>/webtool/wapnode/add.jsp">����ϵͳ</a></td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	 <tr>
	   <th width="25%">ϵͳ����1</th>
	   <th width="30%">ϵͳ����</th>	
	   <th width="15%">�޸Ĳ���</th>
	   <th width="15%">�鿴����</th>
	   <th width="15%">ɾ������</th>
	      
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
	   <td class="<%=tdClass%>"><a href="<%=app %>/webtool/wapnode?act=view&flag=modify&wapNodeId=<%=wapNodeVO.getNodeId()%>">�޸�</a></td>
	   <td class="<%=tdClass%>"><a href="<%=app %>/webtool/wapnode?act=view&flag=view&wapNodeId=<%=wapNodeVO.getNodeId()%>">�鿴</a></td>
	   <td class="<%=tdClass%>"><a href="<%=app %>/webtool/wapnode?act=remove&wapNodeId=<%=wapNodeVO.getNodeId()%>">ɾ��</a></td>	   	   
	 </tr>
	 <%
	 	rowCount = !rowCount;
	 } %>
	</table>
</body>
</html>