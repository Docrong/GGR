<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*" %>

<html>
<head>
    <script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script language="JavaScript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
    <title>
        ɾ��������Ϣ
    </title>

</head>
<body>
<html:form action="/examDeleteDO" method="get">
    <table cellpadding="0" cellspacing="0" width="95%">
        <tr>
            <td align="center" class="table_title">
                ɾ��������Ϣ
            </td>
        </tr>
    </table>
    <center>
        <html:select property="configSel" size="20" value="0" style="width:600">
            <html:options collection="ConfigList" property="value" labelProperty="label"/>
        </html:select>
        <br>

        <html:submit value="ɾ��" onclick="return <script language='JavaScript'>confirm('ɾ��������Ϣ���޷��ָ����Ƿ�ȷ��?')</script>"/>
    </center>
</html:form>
</body>
</html>
