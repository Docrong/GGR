<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<script language="javascript">
    function getStr() {
        var sel_idStr = "";
        var i = 0;
        for (i = 0; i < buttonbar.mail.length; i++) {
            if (document.buttonbar.mail[i].checked) {
                if (sel_idStr == "") {
                    sel_idStr = document.buttonbar.mail[i].value;
                } else {
                    sel_idStr = sel_idStr + ";" + document.buttonbar.mail[i].value;
                }
            }
        }

        window.location.href = 'mailto:' + sel_idStr;

    }
</script>
<script language='JavaScript'>
    var bCheck = false;

    function checkAll() {
        bCheck = !bCheck;
        var ChkEls = document.getElementsByName('mail');
        for (var i = 0; i < ChkEls.length; i++) {
            ChkEls.item(i).checked = bCheck;
        }
    }
</script>

<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<body>
<center>
    <br>
    <form name="buttonbar">
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    &nbsp;&nbsp;<bean:message key="TawInfAddressBook.label"/>&nbsp;&nbsp;<bean:message
                        key="label.list"/></td>
            </tr>
        </table>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="td_label">
                <td width="6%" height="25"><bean:message key="label.select"/></td>
                <td width="20%" height="25"><bean:message key="TawInfAddressBook.company"/></td>
                <td width="20%" height="25"><bean:message key="TawInfAddressBook.deptName"/></td>
                <td width="20%" height="25"><bean:message key="TawInfAddressBook.name"/></td>

                <td width="34%" height="25"><bean:message key="TawInfAddressBook.email"/></td>

            </tr>
            <logic:iterate id="tawInfAddressBook" name="TAWAPMS" type="com.boco.eoms.infmanage.model.TawInfAddressBook">

                <tr class="tr_show">
                    <td width="6%" height="25"><input type="checkbox" name="mail"
                                                      value='<bean:write name="tawInfAddressBook" property="email" scope="page"/>'>
                    </td>
                    <td width="20%" height="25"><bean:write name="tawInfAddressBook" property="company"
                                                            scope="page"/></td>
                    <td width="20%" height="25"><bean:write name="tawInfAddressBook" property="deptName"
                                                            scope="page"/></td>
                    <td width="20%" height="25"><bean:write name="tawInfAddressBook" property="name" scope="page"/></td>
                    <td width="34%" height="25"><a
                            href='mailto:<bean:write name="tawInfAddressBook" property="email" scope="page"/>'><bean:write
                            name="tawInfAddressBook" property="email" scope="page"/></a></td>


                </tr>

            </logic:iterate>
        </table>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" height="32" align="right">
                    <input type='button' value='<bean:message key="label.all"/>' name='checkall' onclick='checkAll()'>
                    <input type='button' Class="clsbtn2" value='<bean:message key="label.sent"/>' onclick='getStr()'>
                    <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                           class="clsbtn2"/>
                </td>
            </tr>
        </table>
    </form>
</center>


</body>
</html>