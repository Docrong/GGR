<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<head>
<title>record</title>


</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmGuestform/okey">
<br>
<center>
<table cellpadding="0" cellspacing="0" border="0" width="95%" align="center">
<tr>
<td class="table_title" align="center"><bean:message key="TawRmGuestform.tablename"/></td>
</tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="formTable" align=center>
<tr class="tr_show">
<td class="clsfth">
    <bean:message key="TawRmGuestform.starttime"/>
</td>
<td>
    <eoms:SelectDate name="starttime" formName="tawRmGuestformForm"/>
</td>
</tr>
</tr>
<tr class="tr_show">
<td class="clsfth">
    <bean:message key="TawRmGuestform.endtime"/>
</td>
<td>
    <eoms:SelectDate name="endtime" formName="tawRmGuestformForm"/>
</td>
</tr>
<tr class="tr_show">
<td class="clsfth">
    <bean:message key="TawRmGuestform.flag"/>
</td>
<td>
        <html:select property="flag" size="1">
        <html:option value="0"><bean:message key="TawRmGuestform.nosubmit"/></html:option>
      </html:select>
</td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="95%" align="center">
<tr>
<td colspan="2" align="right" height="32">
      <html:submit property="strutsButton" styleClass="clsbtn2">
         <bean:message key="label.query"/>
      </html:submit>
  </td>
</tr>
</table>
      <logic:messagesPresent>
      <bean:message key="errors.header"/>
      <ul>
        <html:messages id="error">
          <li>
            <bean:write name="error"/>
          </li>
        </html:messages>
      </ul>
      <hr/>
    </logic:messagesPresent>
</html:form>
</body>

