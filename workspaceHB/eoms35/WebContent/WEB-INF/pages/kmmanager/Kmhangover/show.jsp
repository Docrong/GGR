<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/Kmhangover/save">
<html:hidden property="hangWorkserial" />
<html:hidden property="receiveWorkserial" />
<html:hidden property="flag" />
<html:hidden property="dealer" />
<html:hidden property="notes" />
<html:hidden property="id" />
<html:hidden property="hangQuestion0" />
<TABLE cellSpacing="1" cellPadding="1" width="450" align="center" border="0">
<tr>
<td class="table_title" align="center" colspan = "4" width = "100%"><bean:message key="TawRmHangover.hangquestion"/></td>
</tr>
</table>
<TABLE cellSpacing="1" cellPadding="1" width="450" align="center" border="0" class="table_show">
<tr class="tr_show">
<td align="center" class="clsfth" width = "30%"><bean:message key="TawRmHangover.prehangquestion"/></td>
<td align="center" width = "70%">
        <TEXTAREA id=hangQuestion0 name=hangQuestion0 rows=2 cols=45 style="BACKGROUND-COLOR: lightgrey" readOnly=""><bean:write name="kmhangoverForm" property="hangQuestion0" scope="request"/></TEXTAREA>
</td>
</tr>
<tr class="tr_show">
<td align="center" class="clsfth"><bean:message key="TawRmHangover.thishangquestion"/></td>
<td align="center">
        <TEXTAREA id=hangQuestion name=hangQuestion rows=2 cols=45 ><bean:write name="kmhangoverForm" property="hangQuestion" scope="request"/></TEXTAREA>
</td>
</tr>
</table>
<TABLE cellSpacing="1" cellPadding="1" width="450" align="center" border="0">
<TR>
        <TD align="right" height="32" colSpan="4">
        <html:submit styleClass="button">
        <bean:message key="label.save"/>
        </html:submit>
        </TD>
</TR>
</table>
</html:form>
</body>
</html>

