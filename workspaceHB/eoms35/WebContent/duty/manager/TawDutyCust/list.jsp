<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%
	String strRoomId = StaticMethod.null2String((String)request.getAttribute("STRROOMID"));
	
%>
<head>
<title>ֵ���¼�б�</title>

<script language="javascript">
function onRemove(str)
{
  if (str!="")
  {
    if (confirm("��ȷ��Ҫɾ��ѡ���������Ű���Ϣ��")==true)
    {
      document.tawDutyCustForm.id.value=str;
      document.tawDutyCustForm.action="del.do";
      document.tawDutyCustForm.submit();
      return true;
    }
  }
  else
  {
    alert("��ѡ����Ҫɾ���Ļ����Ű���Ϣ!");
    return false;
  }

}
</script>
</head>
<html:form method="post" action="/TawDutyCust/add.do">
<input type="hidden" name="strRoomId" value="<%=strRoomId %>">
<input type="hidden" name="id" value="">
<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0" class="clssclbar">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center" class="table_title">
    &nbsp;&nbsp;�Ű��¼��ʾ���� �б�  </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="100%" class="clsbkgd">
    <tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
      <tr class="td_label">
        <td width="10%">
          <div align="center">���        </div></td>
        <td width="19%"><div align="center">�ƶ���</div></td>
        <td width="62%">
          <div align="center">��������</div></td>
        <td width="9%">
		  <div align="center">����</div></td>
     </tr>
 <% int i=0; %>
	<logic:iterate id="tawDutyCust" name="DUTYCUSTLIST" type="com.boco.eoms.duty.model.TawDutyCust">
<% i++; %>	
<tr class="tr_show">
     <td><%=i %></td>
     <td>
     <%=tawDutyCust.getUserId()%></td>
     <td><bean:write name="tawDutyCust" property="flagName" scope="page"/></td>
     <td align="center"> <div align="center"><a href='javascript:onRemove(<%=tawDutyCust.getId()%>);'>ɾ��</a>&nbsp;          </div></td>
</tr>
</logic:iterate>

      </table>
</td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
<tr>
<td height="32" align="right">

      <html:submit styleClass="clsbtn2">
        ����
      </html:submit>

  </td>
</tr>
</table>
</body>
</html:form>
