<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>

<head>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<title>BSC1860投诉登记表<bean:message key="label.remove"/></title>

<html:html>
<body>

<div align="center">
    <center>
        <br>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    <bean:message key="label.remove"/>BSC1860投诉登记表<bean:message key="label.remove"/></td>
            </tr>
        </table>

        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <logic:present name="VO" scope="request">
                <tr class="tr_show">
                    <td class="clsfth">单号</td>
                    <td>
                        <bean:write name="VO" property="sheetId" scope="request"/>
                    </td>

                </tr>
                <tr class="tr_show">
                    <td class="clsfth">收单人</td>
                    <td>
                        <bean:write name="VO" property="receiver" scope="request"/>
                    </td>

                </tr>
                <tr class="tr_show">
                    <td class="clsfth">收单时间</td>
                    <td>
                        <bean:write name="VO" property="receTime" scope="request"/>
                    </td>

                </tr>
                <tr class="tr_show">
                    <td class="clsfth">状态</td>
                    <td>
                        <bean:write name="VO" property="status" scope="request"/>
                    </td>

                </tr>

            </logic:present>
        </table>
        <table border="0" width="95%" cellspacing="0">
            <html:form method="POST" action="/TawBsc1860/trash">
                <tr>
                    <td width="100%" height="32" align="right">
                        <input type="hidden" name="id" value="<bean:write name="VO" property="id" scope="request"/>">
                        <html:submit styleClass="clsbtn2"><bean:message key="label.remove"/></html:submit>
                        &nbsp;
                        <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                               class="clsbtn2"/>
                        &nbsp;&nbsp;
                    </td>
                </tr>
            </html:form>
        </table>
    </center>
</div>

</html:html>
</body>

</html>