<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html:form method="post" action="/TawRmChangeduty/save">
<html:hidden property="strutsAction"/>

  <table bgcolor="#666666" cellpadding="1" cellspacing="0" border="0" width="500">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="500">
  <tr>
<td bgcolor="#d6e0ed">

    <c:choose>
      <c:when test="${requestScope['tawRmChangedutyForm'].strutsAction == 1}">
        <bean:message key="label.add"/>
      </c:when>
      <c:otherwise>
        <bean:message key="label.edit"/>
      </c:otherwise>
    </c:choose>
    &nbsp;TawRmChangeduty

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
    <c:choose>
      <c:when test="${requestScope['tawRmChangedutyForm'].strutsAction == 1}">
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.id"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="id" size="8"/></font></td>
</tr>
    </c:when>
    <c:otherwise>
    <html:hidden property="id"/>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.id"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><bean:write name="tawRmChangedutyForm" property="id"/></font></td>
</tr>
    </c:otherwise></c:choose>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.roomId"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="roomId" size="8"/></font></td>
</tr>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.inputdate"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="inputdate" size="30"/>(yyyy-mm-dd hh:mm:ss.fffffffff)</font></td>
</tr>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.workserial1"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="workserial1" size="8"/></font></td>
</tr>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.workserial2"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="workserial2" size="8"/></font></td>
</tr>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.expireddate"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="expireddate" size="30"/>(yyyy-mm-dd hh:mm:ss.fffffffff)</font></td>
</tr>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.hander"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="hander" size="255"/></font></td>
</tr>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.receiver"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="receiver" size="255"/></font></td>
</tr>
    <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmChangeduty.flag"/></b></font></td><td width="76%"><font size="2" face="Tahoma"><html:text property="flag" size="8"/></font></td>
</tr>
    <tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%">

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

    </td>
</tr>
  </table>
</td>
</tr>
  <tr>
<td>
<table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="right" valign="middle">
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


