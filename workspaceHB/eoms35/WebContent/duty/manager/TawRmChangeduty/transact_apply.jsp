<%@ page contentType="text/html; charset=gb2312"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<html:form method="post" action="/TawRmChangeduty/save">
<html:hidden property="strutsAction"/>

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

    &nbsp;<bean:message key="TawRmChangeduty.tablename"/>

  </td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="45%"></td><td width="10%"></td><td width="45%"></td>
</tr>
  <tr>
<td>

<table bgcolor="#f2f2f2" width="500" cellspacing="0" border="0">
    <tr bgcolor="#FFFFFF">
<td width="45%"></td><td width="10%"></td><td width="45%"></td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="45%"></td><td width="10%"></td><td width="45%"></td>
</tr>
</table>

</td>
</tr>




  <tr>
<td>
<table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="center" valign="middle">
<td width="45%">ddd</td><td width="10%"></td><td width="45%"></td>
</tr>
<tr align="center" valign="middle">
<td width="45%"></td><td width="10%"></td><td width="45%"></td>
</tr>
<tr align="center" valign="middle">
</tr>
</table>
</td>
</tr>




  <tr>
<td>
<table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="center" valign="middle">
<td>

      <html:submit>
        <bean:message key="label.save"/>
      </html:submit>
      <html:cancel>
        <bean:message key="label.cancel"/>
      </html:cancel>

  </td>
</tr>
</table>
</td>
</tr>





  </table>
</td>
</tr>
</table>

</html:form>


