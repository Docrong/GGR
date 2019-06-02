<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<head>
 
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmChangeduty/save">
<html:hidden property="strutsAction"/>
<br>
<table cellpadding="0" cellspacing="0" border="0" width="600" align="center">
  <tr width="100%">
<td width="100%" align="center" class="table_title">
    &nbsp;<bean:message key="TawRmChangeduty.tablename"/>
<%
String roomId = (String)request.getAttribute("roomId");
String transactflag = String.valueOf(request.getParameter("TRANSACTFLAG"));
if (transactflag.trim().equals("true"))
{
%>
<font color="red"><bean:message key="TawRmChangeduty.transactsuccess"/></font>
<%
}
%>
  </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">
 
 

<tr >
<td>
&nbsp;
</td>
</tr>


<tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="listTable" align=center>
<tr class="tr_show">
<td colspan=10>${eoms:a2u('审批交换班请求')}</td>
</tr>
<tr class="td_label">
<td class="label"><bean:message key="TawRmChangeduty.id"/></td>
<td class="label"><bean:message key="TawRmChangeduty.hander"/></td>
<td class="label"><bean:message key="TawRmChangeduty.starttime"/></td>
<td class="label"><bean:message key="TawRmChangeduty.receiver"/></td>
<td class="label"><bean:message key="TawRmChangeduty.starttime"/></td>
<td class="label"><bean:message key="TawRmChangeduty.applyreason"/></td>
<td class="label"><bean:message key="TawRmChangeduty.flag"/></td>
<td class="label"><bean:message key="TawRmChangeduty.operate"/></td>
</tr>

<%
Vector managerQueryVector = new Vector();
managerQueryVector = (Vector)request.getAttribute("MANAGERQUERYVECTOR");
Vector getId = (Vector)managerQueryVector.elementAt(0);
Vector getWorkserial1 = (Vector)managerQueryVector.elementAt(1);
Vector getWorkserial2 = (Vector)managerQueryVector.elementAt(2);
Vector getHander = (Vector)managerQueryVector.elementAt(3);
Vector getReceiver = (Vector)managerQueryVector.elementAt(4);
Vector getFlag = (Vector)managerQueryVector.elementAt(5);
Vector getReason = (Vector)managerQueryVector.elementAt(6);
if (getId.size()==0){
%>
<tr class="tr_show">
<td colspan=10 class="label">${eoms:a2u('无交换班请求')}</td>
</tr>
<%
}else{
for(int i=0;i<getId.size();i++){
%>
<tr class="tr_show">
<td><%=i+1%></td>
<td><%=String.valueOf(getHander.elementAt(i))%></td>
<td><%=String.valueOf(getWorkserial1.elementAt(i))%></td>
<td><%=String.valueOf(getReceiver.elementAt(i))%></td>
<td><%=String.valueOf(getWorkserial2.elementAt(i))%></td>
<td><%=String.valueOf(getReason.elementAt(i))%></td>
<td>
 
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==1){%>
${eoms:a2u('本人同意，管理员未处理')}
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==2){%>
${eoms:a2u('本人不同意')}
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==4){%>
${eoms:a2u('管理员同意换班')}
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==5){%>
${eoms:a2u('管理员不同意换班')}

<%}%>
</td>
<td>  
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==1){%>
&nbsp;&nbsp;
<input type="button" name="button" class="button" value=<bean:message key="TawRmChangeduty.agree"/> Onclick="window.location.href ='../TawRmChangeduty/transact_audit.do?transact_id=<%=Integer.parseInt(String.valueOf(getId.elementAt(i)).trim())%>&roomId=<%=roomId %>&transact_flag=4'">
&nbsp;
<input type="button" name="button" class="button" value=<bean:message key="TawRmChangeduty.disagree"/> Onclick="window.location.href ='../TawRmChangeduty/transact_audit.do?transact_id=<%=Integer.parseInt(String.valueOf(getId.elementAt(i)).trim())%>&roomId=<%=roomId %>&transact_flag=5'">
<%}
else{%>
<input type="button" name="button" class="button" value="${eoms:a2u('删除记录')}" Onclick="window.location.href ='../TawRmChangeduty/transact_audit.do?transact_id=<%=Integer.parseInt(String.valueOf(getId.elementAt(i)).trim())%>&roomId=<%=roomId %>&transact_flag=3'">
<%}%>
</td>
</tr>
<%}}%>
</table>
</td>
</tr>


 
</table>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

