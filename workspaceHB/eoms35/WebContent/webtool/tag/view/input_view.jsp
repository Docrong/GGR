<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="com.boco.eoms.mobilewap.base.tag.imp.WapInputTag" %>
<%
WapInputTag tag = (WapInputTag)request.getAttribute("input");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String app = request.getContextPath(); 
%>
<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
  <table width="100%">
	 <tr>
	   <td class="title">�������Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">ҳ���ǩ�б�</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=input&class_str=<%=tag.getClassStr()%>">�޸Ĵ˱�ǩ</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">ɾ���˱�ǩ</a>
       </td>
	 </tr>
	</table>
	<table class="form_table">
	<tr>
	  <th width="30%">���������</td>
	  <td><%=tag.getName() %></td>
	</tr>
	<tr>
	  <th width="30%">���������</td>
	  <td><%=tag.getType()%></td>
	</tr>
	<tr>
	  <th width="30%">Ĭ��ֵ</td>
	  <td><%=tag.getValue()%></td>
	</tr>
	<tr>
	  <th width="30%">�����ʽҪ��</td>
	  <td><%=tag.getFormat() %></td>
	</tr>
	<tr>
	  <th width="30%">�Ƿ�����Ϊ��</td>
	  <td>
	    <%if((tag.getEmptyok()+"").equals("true"))out.println("����");
	      else out.println("������");
	    %>	      
	  </td>
	</tr>
	<tr>
	  <th width="30%">������С</td>
	  <td><%=(tag.getSize().equals("0"))?"":tag.getSize() %></td>
	</tr>
	<tr>
	  <th width="30%">��������ַ���</td>
	  <td><%=(tag.getMaxlength().equals("0"))?"":tag.getMaxlength()%></td>
	</tr>
  </table>
</body>
</html>
	
