<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
    <head>
        <title>修改命令库资料</title>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>

    <html:form action="/TawInfCmd/updatedone" method="post">
        <body>
        <br>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" align="center" class="table_title">
                    <b>
                        <bean:message key="label.edit"/>&nbsp;<bean:message key="TawInfCmd.Name"/>
                    </b>
                </td>
            </tr>
        </table>
        <html:hidden property="id"/>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Select_swich"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:select property="cmdSwich">
                        <html:optionsCollection name="tawInfCmdForm" property="collectionSwich"/>
                    </html:select>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_id"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="cmdId" size="20"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_name"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="cmdName" size="20"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_param"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="cmdParam" size="20"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Param_scope"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="paramScope" size="20"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfCmd.Cmd_des"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="cmdDes" size="50"/>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="1" align="center">
            <tr>
                <td width="100%" colspan="2" height="25" bgcolor="#EEECED" align="right">
                    <input Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.save"/>"
                           onClick="toSubmit()">
                    &nbsp;
                    <input Class="clsbtn2" type="reset" name="toreset" value="<bean:message key="label.reset"/>">
                    &nbsp;&nbsp;
                </td>
            </tr>
        </table>
        </body>
    </html:form>
</html:html>

<script language="javascript">
    function toSubmit() {
        if (window.document.all.cmdId.value == "") {
            alert("命令编号不能为空！");
            return false;
        }

        if (window.document.all.cmdName.value == "") {
            alert("命令名称不能为空！");
            return false;
        }

        if (window.document.all.cmdParam.value == "") {
            alert("命令参数不能为空！");
            return false;
        }

        if (window.document.all.paramScope.value == "") {
            alert("参数的取值范围不能为空！");
            return false;
        }

        if (window.document.all.cmdDes.value == "") {
            alert("命令的基本描述不能为空！");
            return false;
        }

        window.document.tawInfCmdForm.submit();
    }
</script>
