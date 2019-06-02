<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>

  <table bgcolor="#666666" cellpadding="1" cellspacing="0" border="0" width="500">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="500">
<tr>
<td bgcolor="#fecc51">&nbsp;</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="500">
    <tr>
<td bgcolor="#d6e0ed">&nbsp;&nbsp;Delete Confirmation
    </td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%"></td>
</tr>
    <tr>
<td>
<table bgcolor="#f2f2f2" width="500" cellspacing="0" border="0">
      <tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%"></td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%"></td>
</tr>

<logic:present name="tawRmGuestformForm" scope="request">

        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.id"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="id" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.roomId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="roomId" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.inputdate"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="inputdate" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.guestname"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="guestname" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.company"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="company" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.sender"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="sender" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.department"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="department" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.dutyman"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="dutyman" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.starttime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="starttime" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.endtime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="endtime" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.purpose"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="purpose" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.concerned"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="concerned" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.affection"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="affection" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.flag"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="flag" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmGuestform.notes"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmGuestformForm" property="notes" scope="request"/>

            </font></td>
</tr>

</logic:present>

    </table>
</td>
</tr>
    <tr>
<td>
<table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="right" valign="middle">
<td><html:form method="POST" action="/TawRmGuestform/trash">
        <input type="hidden" name="id" value="<bean:write name="tawRmGuestformForm" property="id" scope="request"/>">
                    <html:submit><bean:message key="label.remove"/></html:submit>
                    &nbsp;&nbsp;
                    <html:cancel><bean:message key="label.cancel"/></html:cancel>
            </html:form>

    </td>
</tr>
</table>
</td>
</tr>
  </table>
</td>
</tr>
</table>

