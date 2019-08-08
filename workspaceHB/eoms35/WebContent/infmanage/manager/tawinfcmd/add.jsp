<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
    <head>
        <title>������������</title>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>

    <body onload="refresh()">
    <html:form action="/TawInfCmd/save" method="post">
        <br>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" align="center" class="table_title">
                    <b>
                        <bean:message key="label.add"/>&nbsp;<bean:message key="TawInfCmd.Name"/>
                    </b>
                </td>
            </tr>
        </table>
        <html:hidden property="deptId"/>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
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
                    &nbsp<bean:message key="TawInfCmd.Cmd_id"/><font color="#FFOOOO">**</font>
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
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" colspan="2" height="32" align="right">
                    <input Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.save"/>"
                           onClick="toSubmit()">
                    &nbsp;
                    <input Class="clsbtn2" type="reset" name="toreset" value="<bean:message key="label.reset"/>">
                    &nbsp;&nbsp;
                </td>
            </tr>
        </table>
    </html:form>
    </body>
</html:html>

<script language="javascript">
    function toSubmit() {
        if (window.document.all.cmdSwich.value == "") {
            alert("��ѡ��һ�ֽ����������ͣ�");
            return false;
        }

        if (window.document.all.cmdId.value == "") {
            alert("�����Ų���Ϊ�գ�");
            return false;
        }

        if (window.document.all.cmdName.value == "") {
            alert("�������Ʋ���Ϊ�գ�");
            return false;
        }

        if (window.document.all.cmdParam.value == "") {
            alert("�����������Ϊ�գ�");
            return false;
        }

        if (window.document.all.paramScope.value == "") {
            alert("������ȡֵ��Χ����Ϊ�գ�");
            return false;
        }

        if (window.document.all.cmdDes.value == "") {
            alert("����Ļ�����������Ϊ�գ�");
            return false;
        }

        window.document.tawInfCmdForm.submit();
    }

    function refresh() {
        window.document.tawInfCmdForm.cmdSwich.value = "";
        window.document.tawInfCmdForm.cmdId.value = "";
        window.document.tawInfCmdForm.cmdName.value = "";
        window.document.tawInfCmdForm.cmdParam.value = "";
        window.document.tawInfCmdForm.paramScope.value = "";
        window.document.tawInfCmdForm.cmdDes.value = "";
    }
</script>


