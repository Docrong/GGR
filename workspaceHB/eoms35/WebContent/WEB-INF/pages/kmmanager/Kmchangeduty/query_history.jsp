<%@ page contentType="text/html; charset=gb2312"%>

<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<head>
<title>��ѯ�����¼</title>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmChangeduty/save">
<html:hidden property="strutsAction"/>
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
<tr>
<td class="table_title" align="center">�����������ѯ</td>
</tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="td_label">
<td>����ʱ��</td>
<td>������</td>
<td>ֵ�࿪ʼʱ��</td>
<td>������</td>
<td>ֵ�࿪ʼʱ��</td>
<td>����ԭ��</td>
<td>״̬</td>
</tr>

<%
Vector allQueryVector = new Vector();
allQueryVector = (Vector)request.getAttribute("ALLQUERYVECTOR");
Vector getInputdate = (Vector)allQueryVector.elementAt(0);
Vector getWorkserial1 = (Vector)allQueryVector.elementAt(1);
Vector getWorkserial2 = (Vector)allQueryVector.elementAt(2);
Vector getHander = (Vector)allQueryVector.elementAt(3);
Vector getReceiver = (Vector)allQueryVector.elementAt(4);
Vector getFlag = (Vector)allQueryVector.elementAt(5);
Vector getReason = (Vector)allQueryVector.elementAt(6);
if (getInputdate.size()==0){
%>
<tr align="center" valign="middle">
<td colspan=10>��ʱ������޽�����</td>
</tr>
<%
}else{
for(int i=0;i<getInputdate.size();i++){
%>
<tr class="tr_show">
<td><%=String.valueOf(getInputdate.elementAt(i))%></td>
<td><%=String.valueOf(getHander.elementAt(i))%></td>
<td><%=String.valueOf(getWorkserial1.elementAt(i))%></td>
<td><%=String.valueOf(getReceiver.elementAt(i))%></td>
<td><%=String.valueOf(getWorkserial2.elementAt(i))%></td>
<td><%=String.valueOf(getReason.elementAt(i))%></td>
<td>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==0){%>
��������δ����
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==1){%>
��������ͬ�⣬����Աδ����
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==2){%>
�������˲�ͬ��
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==4){%>
����Աͬ�⻻��
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==5){%>
����Ա��ͬ�⻻��
<%}%>
&nbsp;
</td>
</tr>
<%}}%>
</table>
</td>
</tr>
</table>

</html:form>
</body>

