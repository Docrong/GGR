<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

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
<td bgcolor="#d6e0ed">&nbsp;<bean:message key="label.remove"/>&nbsp;<bean:message key="TawRmSysteminfo.tablename"/>
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

<logic:present name="tawRmSysteminfoForm" scope="request">

        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.roomId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmSysteminfoForm" property="roomId" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.deptId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmSysteminfoForm" property="deptId" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.maxerrortime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmSysteminfoForm" property="maxerrortime" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.maxdutynum"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmSysteminfoForm" property="maxdutynum" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.exRequest"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmSysteminfoForm" property="exRequest" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.exAnswer"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmSysteminfoForm" property="exAnswer" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.dutyInform"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawRmSysteminfoForm" property="dutyInform" scope="request"/>

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
<td><html:form method="POST" action="/TawRmSysteminfo/trash">
        <input type="hidden" name="roomId" value="<bean:write name="tawRmSysteminfoForm" property="roomId" scope="request"/>">
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

