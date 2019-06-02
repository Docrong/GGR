<%@ page contentType="text/html; charset=gb2312"%>



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


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
<td bgcolor="#d6e0ed">
      &nbsp;&nbsp;<bean:message key="label.view"/>&nbsp;

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

<logic:present name="tawRmExchangeForm" scope="request">

        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmExchange.roomId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmExchangeForm" property="roomId" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmExchange.id"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmExchangeForm" property="id" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmExchange.exchangetime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmExchangeForm" property="exchangetime" scope="request"/>

            </font></td>
</tr>

</logic:present>
<logic:notPresent name="tawRmExchangeForm" scope="request">
  <tr>
    <td>
    <bean:message key="error.notfound"/>
    </td>
  <tr>
</logic:notPresent>

</table>
</td>
</tr>
</table>
</td>
</tr>
</table>

