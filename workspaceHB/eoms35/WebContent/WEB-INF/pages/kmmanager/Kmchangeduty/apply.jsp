<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
 
<head>
</head>
<body leftmargin="0" topmargin="0">

<form method="post" action="${app}/kmmanager/Kmchangeduty/save_apply.do">
<center>
<br>
<table cellpadding="0" cellspacing="0" border="0" width="90%">
  <tr>
<td width="100%" align="center" class="table_title">

    &nbsp;<bean:message key="TawRmChangeduty.tablename"/>
<%
String saveflag = String.valueOf(request.getParameter("SAVEFLAG"));
if (saveflag.trim().equals("true"))
{
%>
<font color="red"><bean:message key="TawRmChangeduty.savesuccess"/></font>
<%
}
%>
  </td>
</tr>

<tr>
<td>
	<table cellpadding="1" cellspacing="1" border="0" width="100%" >
	<tr align="center" valign="middle">
	<td width="45%" valign = "top">
	<!--3+1-->
		<table cellpadding="1" cellspacing="1" border="0" width="100%" class="listTable">
		<tr class="tr_show">
		<td colspan=3 align="center" class="label"><b>
		<bean:message key="TawRmChangeduty.workserial1"/></b>
		</td>
		</tr>
		<tr class="td_label">
		<td class="label"><bean:message key="TawRmChangeduty.select"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.starttime"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.dutyman"/></td>
		</tr>
		<%
		Vector vector_from = new Vector();
		Vector getWorkserial_from = new Vector();
		Vector getStarttime_from = new Vector();
		Vector getDutyman_from = new Vector();
		Vector getUsername_from = new Vector();
		vector_from = (Vector)request.getAttribute("VECTORFROM");
		getWorkserial_from = (Vector)vector_from.elementAt(0);
		getStarttime_from = (Vector)vector_from.elementAt(1);
		getDutyman_from = (Vector)vector_from.elementAt(2);
		getUsername_from = (Vector)vector_from.elementAt(3);
		%>
		<%
		if (getWorkserial_from.size()>0){
		for (int i=0;i<getWorkserial_from.size();i++){
		%>
		<tr class="tr_show">
		<td>
		<input id = "radio_from" type="radio" name="radio_from" 	value="<%="from,"+String.valueOf(getWorkserial_from.elementAt(i))+","+String.valueOf(getDutyman_from.elementAt(i))+","+String.valueOf(getStarttime_from.elementAt(i))%>" <%if (i==0){%>checked = "checked"<%}%>>
		</td>
		<td>
		<%=String.valueOf(getStarttime_from.elementAt(i))%>
		</td>
		<td>
		<%=String.valueOf(getUsername_from.elementAt(i))%>
		</td>
		</tr>
		<%}%>
		<%}else{%>
		<tr class="tr_show">
		<td colspan=5>
		<bean:message key="TawRmChangeduty.alertnoreceiver"/>
		</td>
		</tr>
		<%}%>
		</table>
  <!--3+1-->
	</td>
	<td width="10%">>></td>
	<td width="45%" valign = "top" class="tr_show">
  <!--3+2-->
		<table cellpadding="1" cellspacing="1" border="0" width="100%" class="listTable">
		<tr class="tr_show">
		<td colspan=3 align="center" class="label"><b>
		<bean:message key="TawRmChangeduty.workserial2"/></b>
		</td>
		</tr>
		<tr class="td_label">
		<td class="label"><bean:message key="TawRmChangeduty.select"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.starttime"/></td>
		<td class="label"><bean:message key="TawRmChangeduty.dutyman"/></td>
		</tr>
		<%
		Vector vector_to = new Vector();
		Vector getWorkserial_to = new Vector();
		Vector getStarttime_to = new Vector();
		Vector getDutyman_to = new Vector();
		Vector getUsername_to = new Vector();
		vector_to = (Vector)request.getAttribute("VECTORTO");
		getWorkserial_to = (Vector)vector_to.elementAt(0);
		getStarttime_to = (Vector)vector_to.elementAt(1);
		getDutyman_to = (Vector)vector_to.elementAt(2);
		getUsername_to = (Vector)vector_to.elementAt(3);
		%>
		<%
		if (getWorkserial_to.size()>0){
		for (int i=0;i<getWorkserial_to.size();i++){
		%>
		<tr class="tr_show">
		<td>
		<input id = "radio_to" type="radio" name="radio_to" value="<%="to,"+String.valueOf(getWorkserial_to.elementAt(i))+","+String.valueOf(getDutyman_to.elementAt(i))+","+String.valueOf(getStarttime_to.elementAt(i))%>" <%if (i==0){%>checked = "checked"<%}%>>
		</td>
		<td>
		<%=String.valueOf(getStarttime_to.elementAt(i))%>
		</td>
		<td>
		<%=String.valueOf(getUsername_to.elementAt(i))%>
		</td>
		</tr>
		<%}%>
		<%}else{%>
		<tr class="tr_show">
		<td colspan=5>
		<bean:message key="TawRmChangeduty.alertnoreceiver"/>
		</td>
		</tr>
		<%}%>
		</table>
  <!--3+2-->
	</td>
	</tr>
	</table>
  <!--2+1-->
</td>
</tr>

<tr>
<td>
  <!--2+2-->
	      <table cellpadding="0" cellspacing="0" border="0" width="100%" class="listTable">
            <tr>
              <td class="label">&nbsp;</td>
            </tr>
            <%if (getWorkserial_from.size()>0 && getWorkserial_to.size()>0){%>
            <tr>
              <td class="label"> <b><bean:message key="TawRmChangeduty.applyreson"/></b>
                <input type="text" name="reason" size="80" value="">
              </td>
            </tr>
            <tr>
              <td height="32" align="left"> <html:submit styleClass="button">
                <bean:message key="TawRmChangeduty.apply"/> </html:submit> </td>
            </tr>
            <%}else{%>  
            <tr class="table_title">
              <td> <bean:message key="TawRmChangeduty.alertcannotapply"/> </td>
            </tr>
            <%}%>
          </table>
	<!--2+2-->
</td>
</tr>
</table>
  <!--1-->
  </center>
<%
int roomId = Integer.parseInt(String.valueOf(request.getAttribute("roomId")).trim());
String user_id = String.valueOf(request.getAttribute("USERID")).trim();
String time_from = String.valueOf(request.getAttribute("TIMEFROM")).trim();
String time_to = String.valueOf(request.getAttribute("TIMETO")).trim();
%>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name = "user_id" value = "<%=user_id%>">
<input type="hidden" name = "time_from" value = "<%=time_from%>">
<input type="hidden" name = "time_to" value = "<%=time_to%>">
</form>
</body>

