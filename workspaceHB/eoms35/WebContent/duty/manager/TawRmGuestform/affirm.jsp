<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>

<script language="JavaScript" >
function alert_success(){
tawRmGuestformForm.submit();
}
</script>
<head> 
<title><bean:message key="TawRmGuestform.roomOk"/></title>

</head>
<html:form method="post" action="/TawRmGuestform/update"
  onsubmit="return validateTawRmGuestformForm(this);">
<html:javascript formName="tawRmGuestformForm" dynamicJavascript="true" staticJavascript="false"/>
<html:hidden property="strutsAction"/>
<br>
<center>
<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0">
<tr>
<td width="100%" align="center" class="table_title">
    <c:choose>
      <c:when test="${requestScope['tawRmGuestformForm'].strutsAction == 1}">
        <bean:message key="label.add"/>
      </c:when>
      <c:otherwise>
      <bean:message key=" TawRmGuestform.ok"/>
       
      </c:otherwise>
    </c:choose>
    &nbsp;<bean:message key="TawRmGuestform.tablename"/>

  </td>
</tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
    <c:choose>
      <c:when test="${requestScope['tawRmGuestformForm'].strutsAction == 1}">
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.id"/>
</td>
<td>
<html:text property="id" size="8"/>
</td>
    </c:when>
    <c:otherwise>
    <html:hidden property="id"/>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.id"/>
</td>
<td>

	<bean:write name="tawRmGuestformForm" property="id"/>
</td>

    </c:otherwise></c:choose>
<html:hidden property="roomId" />
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.guestname"/>
</td>
<td>
<html:text property="guestname" size="8"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.company"/>
</td>
<td>
<html:text property="company" size="20"/>
</td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.dutyman"/>
</td>
<td>
<html:text property="dutyman" size="10"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.department"/>
</td>
<td>
<html:text property="department" size="20"/>
</td>
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.sender"/>
</td>
<td>
<html:text property="sender" size="10"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.starttime"/>
</td>
<td colspan="3">
	<bean:write name="tawRmGuestformForm" property="starttime"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.endtime"/>
</td>
<td colspan="3">
        <%//<html:text property="starttime" size="10"/>(yyyy-mm-dd hh:mm:ss)%>
        <eoms:SelectTime name="endtime" formName="tawRmGuestformForm"/>
</td>
</tr>
<tr class="tr_show">
<td noWrap class="clsfth">
	<bean:message key="TawRmGuestform.purpose"/>
</td>
<td colspan="3">
<html:textarea property="purpose" rows="4" cols="50"/>
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
<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0">
<tr>
 <TD align="right" height="32">
       <input type="button" class="clsbtn2" value=<bean:message key="label.ok" /> onclick="alert_success();">
</td>
</tr>
</table>
</center>
</html:form>

<logic:messagesPresent>
                  <html:messages id="error">
	<script type="text/javascript">
		<!--
                    alert("<bean:write name="error"/>");
		-->
	</script>
                  </html:messages>
</logic:messagesPresent>

