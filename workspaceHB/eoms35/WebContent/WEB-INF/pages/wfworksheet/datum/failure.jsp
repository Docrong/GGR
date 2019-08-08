<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css"/>
    </head>
    <body>
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">
                <font style="font-size:14px;color:#CC0000;font-weight:bold">·¢Éú´íÎó:</font>
                <logic:messagesPresent>
                    <html:messages id="error">
                        <bean:write name="error"/>
                    </html:messages>
                </logic:messagesPresent>
                <br><br>
                <input type="button" class="clsbtn2" value="·µ»Ø" onclick="javascript:history.back();">
            </td>
        </tr>
    </table>
    </body>
</html:html>
