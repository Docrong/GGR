<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,
                 com.boco.eoms.mobilewap.webtool.bo.WebToolBO,
                 com.boco.eoms.mobilewap.base.tag.imp.WapSelectStaticTag,
                 com.boco.eoms.mobilewap.base.tag.imp.WapSelectOption" %>
<%@ page autoFlush="true" %>
<%
WapSelectStaticTag tag = (WapSelectStaticTag)request.getAttribute("selectStatic");

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
	   <td class="title">��̬��������Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">ҳ���ǩ�б�</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=selectstatic&class_str=<%=tag.getClassStr()%>">�޸Ĵ˱�ǩ</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">ɾ���˱�ǩ</a>
       </td>
	 </tr>
  </table>
  <table class="form_table">
	<tr>
		<th width="30%">���������ƣ�</td>
		<td><%=tag.getName() %></td>
	</tr>
	<tr >
		<th width="30%">�Ƿ�Ϊ��ѡ��</td>
        <td>
	        <%if(tag.getMultiple().equals("false"))out.println("��ѡ");
	          else out.println("��ѡ"); %>
        </td>
	</tr>
	<tr>
		<th width="30%" >option�����б�</td>
		<td></td>
	</tr>
</table>
<table width="100%" class="form_table">
<%if(tag.getOptions()!=null) 
    for(int j=0; j<tag.getOptions().size(); j++){
    WapSelectOption pfVO = (WapSelectOption)tag.getOptions().get(j);
%>
     <tr>
         <th width="10%">ֵ:</td>
         <td width="23%"><%=pfVO.getVaule()%></td>
         <th width="10%">�ı�:</td>
         <td width="23%"h><%=pfVO.getText()%></td>			                
         <th width="10%">�ύ��ַ��</td>
      <td width="23%"><%=pfVO.getOnpick()%></td>
     </tr>
<%}%>
</table>
</form>
</body>
</html>
