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
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
   	<table width="100%">
	 <tr>
	   <td class="title">ҳ����Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapNodeId=<%=wapNodeId%>&wapCardId=<%=wapCardId%>">�޸Ĵ�ҳ��</a>
	    |<a href="<%=app %>/webtool/card?act=remove&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>">ɾ����ҳ��</a>	   
	   </td>
	 </tr>
	</table>

	<table class="form_table">
	<tr>
	   <th width="30%">ҳ��ID</th>
	   <td width="70%">
	   <%if(vo!=null){ %>
	     <%=vo.getCardId() %>&nbsp;
	   <%}else{%>
	     <%="" %>&nbsp;
	   <%} %>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ������</th>
	   <td>
	   <%if(vo!=null){ %>
	     <%=vo.getName() %>&nbsp;
	   <%}else{%>
	     <%="" %>&nbsp;
	   <%} %>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ������</th>
	   <td width="70%">
	   <%if(vo!=null){ %>
	     <%=vo.getDescription() %>&nbsp;
	   <%}else{%>
	     <%="" %>&nbsp;
	   <%} %>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ��title</th>
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
	   <th width="30%">��ǩ����</th>
	   <th>����ҳ����λ������</th>	
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