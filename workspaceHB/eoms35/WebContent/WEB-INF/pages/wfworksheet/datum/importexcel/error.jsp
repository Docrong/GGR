<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
    <body>
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0"
           background="<%=request.getContextPath()%>/template/img/bg001.gif">
        <tr>
            <td width="100%" height="100%" align="center">
                <table width="743" height="300" background="<%=request.getContextPath()%>/images/bg001.jpg">
                    <tr>
                        <td align="center">
                            <font style="font-size:14px;color:#CC0000;">
                                <strong>·¢Éú´íÎó:</strong>
                            </font>
                            <br><br>
                            <%=(String) request.getAttribute("error")%>
                            <br><br>
                            <input type="button" value="·µ»Ø" onclick="javascript:history.back();">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    </body>
</html:html>
