 
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm,java.util.*,com.boco.eoms.common.controller.*"%>
<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<head>
<title>dengji</title>

</head>
<body>
<script language="JavaScript" src="<%=request.getContextPath()%>/web/duty/manager/TawRmGuestform/ValidationScript.jsp">
</script>
<html:form method="post" action="/TawRmGuestform/save">
<!--   onsubmit="return validateTawRmGuestformForm(this);" -->
<html:javascript formName="tawRmGuestformForm" dynamicJavascript="true" staticJavascript="false"/>

<html:hidden property="strutsAction"/>
<%
TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
String room_id = (saveSessionBeanForm.getRoomId());
System.out.println("yew");
%>
<html:hidden property="roomId" value= "<%=room_id%>"/>
<br>
<center>
<TABLE cellSpacing="0" cellPadding="0" width="95%" align="center" border="0">
<tr>
<td width="100%" align="center" class="table_title">
    <c:choose>
      <c:when test="${requestScope['tawRmGuestformForm'].strutsAction == 1}">
        <bean:message key="label.add"/>
      </c:when>
      <c:otherwise>
        <bean:message key="label.edit"/>
      </c:otherwise>
    </c:choose>
    &nbsp;<bean:message key="TawRmGuestform.tablename"/>

  </td>
</tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.guestname"/>
</td>
      <td> <html:text property="guestname" size="8"/><font color="#FF0000">**</font>
      </td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.inputdate"/>
</td>
<td>

        <input name="inputdate" type="text" style="WIDTH: 115px; HEIGHT: 22px; BACKGROUND-COLOR: lightgrey" readOnly="" size="15" value="<eoms:TextDate />" />

</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.company"/>
</td>
      <td> <html:text property="company" size="20"/><font color="#FF0000">**</font>
      </td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.dutyman"/>
</td>
      <td> <html:text property="dutyman" size="10"/><font color="#FF0000">**</font>
      </td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.department"/>
</td>
      <td> <html:text property="department" size="20"/><font color="#FF0000">**</font>
      </td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.sender"/>
</td>
      <td> <html:text property="sender" size="10"/><font color="#FF0000">**</font> </td>
</tr>
<tr  class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.starttime"/>
</td>
<td noWrap colspan="3">
        <eoms:SelectTime name="starttime" formName="tawRmGuestformForm"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.purpose"/>
</td>
      <td colspan="3"> <html:textarea property="purpose" rows="4" cols="50"/><font color="#FF0000">**</font>
      </td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.concerned"/>
</td>
<td colspan="3">
<html:textarea property="concerned" rows="4" cols="50"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.affection"/>
</td>
<td colspan="3">
<html:textarea property="affection" rows="4" cols="50"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.notes"/>
</td>
<td colspan="3">
<html:textarea property="notes" rows="4" cols="50"/>
</td>
</tr>
</table>
<TABLE cellSpacing="0" cellPadding="0" width="95%" align="center" border="0">
<tr>
 <TD align="right" height="32">
       <html:submit styleClass="clsbtn2">
        <bean:message key="label.save"/>
      </html:submit>
<!--
      <html:cancel onclick="bCancel=true;">
        <bean:message key="label.cancel"/>
      </html:cancel>
-->
</td>
</tr>
</table>
</center>
</html:form>
</body>
<logic:messagesPresent>
                  <html:messages id="error">
	<script type="text/javascript">
		<!--
                    alert("<bean:write name="error"/>");
		-->
	</script>
                  </html:messages>
</logic:messagesPresent>

