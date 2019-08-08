<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>

<html>

<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script language="javascript" src="<%=request.getContextPath()%>/css/calendar.js"></script>
</head>

<title>�����������Ŷ�Ӧ</title>

<html:html>

<html:form method="post" action="/TawBureaudataCityzone/save">
<body>

<div align="center">
    <center>
        <br>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    <bean:message key="label.add"/>�������Ŷ�Ӧ
                </td>
            </tr>
        </table>

        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="tr_show">
                <td width="15%" class="clsfth">����ID</td>
                <td width="400"><html:text property="cityId" title="����ID"/></td>
            </tr>
            <tr class="tr_show">
                <td width="15%" class="clsfth">��������</td>
                <td width="400"><html:text property="cityName" title="��������"/></td>
            </tr>
            <tr class="tr_show">
                <td width="15%" class="clsfth">����</td>
                <td width="400"><html:text property="zoneNum" title="����"/></td>
            </tr>

        </table>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" height="32" align="right">
                    <html:submit onclick="bCancel=false;" styleClass="clsbtn2">
                        <bean:message key="label.save"/>
                    </html:submit>
                    &nbsp;
                    <html:reset styleClass="clsbtn2">
                        <bean:message key="label.reset"/>
                    </html:reset>&nbsp;&nbsp;
                </td>
            </tr>
        </table>
    </center>
</div>

</html:form>

<logic:messagesPresent>
    <html:messages id="error">
        <script type="text/javascript">

            alert("<bean:write name="error"/>");

        </script>
    </html:messages>
</logic:messagesPresent>

</html:html>
</body>

</html>