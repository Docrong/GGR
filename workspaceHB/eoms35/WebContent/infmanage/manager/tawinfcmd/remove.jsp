<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
    <head>
        <title>É¾³ýÃüÁî¿â×ÊÁÏ</title>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>

    <html:form action="/TawInfCmd/deldone" method="post">
        <body>
        <br>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" align="center" class="table_title">
                    <b>
                        <bean:message key="label.remove"/>&nbsp;<bean:message key="TawInfCmd.Name"/>
                    </b>
                </td>
            </tr>
        </table>
        <html:hidden property="id"/>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmdswich"/>
                </td>
                <td width="70%" height="25">
                    &nbsp<bean:write name="tawInfCmdForm" property="cmdSwich" scope="request"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_id"/>
                </td>
                <td width="70%" height="25">
                    &nbsp<bean:write name="tawInfCmdForm" property="cmdId" scope="request"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_name"/>
                </td>
                <td width="70%" height="25">
                    &nbsp<bean:write name="tawInfCmdForm" property="cmdName" scope="request"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_param"/>
                </td>
                <td width="70%" height="25">
                    &nbsp<bean:write name="tawInfCmdForm" property="cmdParam" scope="request"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Param_scope"/>
                </td>
                <td width="70%" height="25">
                    &nbsp<bean:write name="tawInfCmdForm" property="paramScope" scope="request"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_des"/>
                </td>
                <td width="70%" height="25">
                    &nbsp<bean:write name="tawInfCmdForm" property="cmdDes" scope="request"/>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="1" align="center">
            <tr>
                <td width="100%" height="32" align="right">
                    <input Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.remove"/>"
                           onClick="toSubmit()">
                    &nbsp;
                    <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                           class="clsbtn2"/>
                    &nbsp;&nbsp;
                </td>
            </tr>
        </table>
        </body>
    </html:form>
</html:html>

<script language="javascript">
    function toSubmit() {
        window.document.tawInfCmdForm.submit();
    }
</script>


