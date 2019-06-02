<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<head>
<title><bean:message key="TawRmGuestform.roomTable"/></title>

</head>
<body>
<html:form method="post" action="/TawRmGuestform/query" >
<br>
<center>
<TABLE cellSpacing="0" cellPadding="0" width="95%" align="center" border="0">
<tr>
<td width="100%" align="center" class="table_title">
      &nbsp;&nbsp;<bean:message key="label.view"/>&nbsp;
</td>
</tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<logic:present name="tawRmGuestformForm" scope="request">
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.guestname"/>
</td>
<td>
<bean:write name="tawRmGuestformForm" property="guestname" scope="request"/>
</td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.inputdate"/>
</td>
<td>
<bean:write name="tawRmGuestformForm" property="inputdate" scope="request"/>
</td>
</tr>

<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.company"/>
</td>
<td>
<bean:write name="tawRmGuestformForm" property="company" scope="request"/>
</td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.dutyman"/>
</td>
<td>
<bean:write name="tawRmGuestformForm" property="dutyman" scope="request"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.department"/>
</td>
<td>
<bean:write name="tawRmGuestformForm" property="department" scope="request"/>
</td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.sender"/>
</td>
<td>
<bean:write name="tawRmGuestformForm" property="sender" scope="request"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.starttime"/>
</td>
<td colspan="3">
<bean:write name="tawRmGuestformForm" property="starttime" scope="request"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.endtime"/>
</td>
<td colspan="3">
<bean:write name="tawRmGuestformForm" property="endtime" scope="request"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.purpose"/>
</td>
<td colspan="3">
<bean:write name="tawRmGuestformForm" property="purpose" scope="request"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.concerned"/>
</td>
<td colspan="3">
<bean:write name="tawRmGuestformForm" property="concerned" scope="request"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.affection"/>
</td>
<td colspan="3">
<bean:write name="tawRmGuestformForm" property="affection" scope="request"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.notes"/>
</td>
<td colspan="3">
<bean:write name="tawRmGuestformForm" property="notes" scope="request"/>
</td>
</tr>
</logic:present>
<logic:notPresent name="tawRmGuestformForm" scope="request">
<tr>
    <td>
    <bean:message key="error.notfound"/>
    </td>
  <tr>
</logic:notPresent>
</table>
<TABLE cellSpacing="0" cellPadding="0" width="95%" align="center" border="0">
<tr>
 <TD align="right" height="32">
       <html:submit styleClass="clsbtn2">
         <bean:message key="label.cancel"/>
      </html:submit>
</td>
</tr>
</table>
</center>
</html:form>
</body>
