<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.tag.imp.SelectDynamicTag,
                 com.boco.eoms.mobilewap.tag.vo.SelectOptionVO" %>
<%@ page autoFlush="true" %>
<%
SelectDynamicTag tag = (SelectDynamicTag)request.getAttribute("selectDynamic");
SelectOptionVO soVO = tag.getOptionList();
String wapNodeId=tag.getCardId().substring(tag.getCardId().indexOf("_")+1,tag.getCardId().length());
String app = request.getContextPath(); 
%>
<html>
<head>
<title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body leftmargin="0" topmargin="0" rightmargin=0 bottommargin=0>
  <table width="100%">
	 <tr>
	   <td class="title">��̬��������Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&cardId=<%=tag.getCardId()%>">ҳ���ǩ�б�</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&cardId=<%=tag.getCardId()%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=selectdynamic&class_str=<%=tag.getClass_str()%>">�޸Ĵ˱�ǩ</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&cardId=<%=tag.getCardId()%>&tagKey=<%=request.getParameter("tagKey") %>">ɾ���˱�ǩ</a>
       </td>
	 </tr>
  </table>
  <table class="form_table">
    <tr>
		<th width="30%">����������</td>
		<td><%=tag.getName() %></td>
	</tr>
    <tr>
		<th width="30%">�Ƿ��ѡ</td>
		<td>
		   <%if(tag.getMultiple()==false)out.println("��ѡ"); 
             else out.println("��ѡ"); 
           %>
        </td>
	</tr>
	</table>
	<table class="form_table">
	<tr>
		<td width="33%">
		����optionֵ:<%=soVO.getVaule() %>
        </td>
		<td width="33%">
		    ��ʾ����:<%=soVO.getText() %>
        </td>
		<td width="33%">
		    ��תҳ��ID:<%=soVO.getOnpick() %>
        </td>		
	</tr>
</table>
</form>
</body>
</html>
