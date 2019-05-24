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
<head><title>����ԱWAP���ù���</title>

<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
	<table width="100%">
	 <tr>
	   <td class="title">ϵͳ��Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card/add.jsp?wapNodeId=<%=wapNodeVO.getNodeId()%>">����ҳ��</a> |
	     <a href="<%=app %>/webtool/wapnode?act=view&flag=modify&wapNodeId=<%=wapNodeVO.getNodeId()%>">�޸�</a> |
	     <a href="<%=app %>/webtool/wapnode?act=remove&wapNodeId=<%=wapNodeVO.getNodeId()%>">ɾ��</a>
	   </td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th width="30%">ϵͳ����</th>
	   <td><%=wapNodeVO.getName() %></td>
	 </tr>
	 <tr>
	   <th>ϵͳ����</th>
	   <td><%=wapNodeVO.getDescription() %></td>
	 </tr>
 
	 <tr>
	   <th>ʹ�����ݿ�</th>
	   <td><%=wapNodeVO.getDbName()%></td>
	 </tr>
	 <tr>

	   <th>ϵͳ��ҳ</th>
	   <td><%=wapNodeVO.getName()%></td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	<tr><td colspan="3" class="nobg">��ǩ�б�</td></tr>
	 <tr>
	   <th width="30%">ҳ������</th>
	   <th width="25%">ҳ��ID</th>
	   <th width="45%">ҳ������</th>	   
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