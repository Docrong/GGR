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
<title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
  <table width="100%">
	 <tr>
	   <td class="title">��ʱ��ת��Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">ҳ���ǩ�б�</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=onevent&class_str=<%=tag.getClassStr()%>">�޸Ĵ˱�ǩ</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">ɾ���˱�ǩ</a>
       </td>
	 </tr>
  </table>
  <table class="form_table">
	<tr>
		<td width="100%" colspan="2"><font color="#FF0000">*ע��1�����10����</font></td>
	</tr>
    <tr>
		<th width="30%">��ʱʱ�䣺</td>
		<td><%=tag.getValue() %>����</td>
	</tr>
	<tr>
		<th width="30%">��תҳ��ID</td>
		<td><%=tag.getHref() %></td>
	</tr>
</table>
</form>
</body>
</html>
