<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<html:html>
    <head>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">

    </head>

    <html:form action="/TawInfGroup/editsave" method="POST">
        <html:hidden property="groupId"/>
        <html:hidden property="userId"/>
        <body>
        <br>
        <table border="0" width="80%" cellspacing="0" align="center">
            <tr>
                <td width="100%" align="center" class="table_title">

                    <bean:message key="label.edit"/>&nbsp;<bean:message key="TawInfAddressBook.groupName"/>

                </td>
            </tr>
        </table>
        <table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align="center">
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth" align="center">&nbsp;
                    &nbsp<bean:message key="TawInfAddressBook.groupName"/>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="groupName" size="40"/>
                </td>
            </tr>
        </table>
        <table border="0" width="80%" cellspacing="0" align="center">
            <tr>
                <td width="100%" colspan="2" height="32" align="right">

                    <input Class="clsbtn2" type="submit" name="tosubmit" value="<bean:message key="label.save"/>"
                           onClick="toSubmit()">
                    &nbsp;
                    <input Class="clsbtn2" type="reset" name="toreset" value="<bean:message key="label.reset"/>">
                    &nbsp;
                    <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                           class="clsbtn2"/>
                </td>

            </tr>
        </table>
        </body>
    </html:form>

</html:html>
<script language="javascript">

    function toSubmit() {

        if (document.all.groupName.value == "") {
            alert("组别不能空！");
            return false;
        }

        window.document.tawInfGroupForm.submit();
    }
</script>
