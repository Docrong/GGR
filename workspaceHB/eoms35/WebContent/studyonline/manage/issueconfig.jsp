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
        ������Ϣ����
    </title>

    <script language="javascript">
        function check() {
            var form = document.forms[0];
            if (!checkLength(form.issueStarttime, 1, 20)) return false;
            if (!checkLength(form.issueEndtime, 1, 20)) return false;
            if (!confirm("�Ƿ�ȷ�Ϸ�����"))
                return false;
        }
    </script>

</head>
<body>
<html:form action="/issueDO" method="get">
    <SCRIPT language=javascript>
        <!--
        //������������������ʾ
        var outObject;
        var old_dd = null;
        writeCalender();
        bltSetDay(bltTheYear, bltTheMonth);
        //-->
    </SCRIPT>
    <table cellpadding="0" cellspacing="0" width="95%">
        <tr>
            <td align="center" class="table_title">
                ������Ϣ����
            </td>
        </tr>
    </table>
    <center>
        <html:select property="configSel" size="20" value="0" style="width:600" title="����ѡ��">
            <html:options collection="ConfigList" property="value" labelProperty="label"/>
        </html:select>
        <br>
        <p>
            ��ʼʱ��<font color="red">*</font>
            <html:text property="issueStarttime" style="width:200" onfocus="setday(this)" readonly="true" title="��ʼʱ��"/>
        </p>
        <p>
            ����ʱ��<font color="red">*</font>
            <html:text property="issueEndtime" style="width:200" onfocus="setday(this)" readonly="true" title="����ʱ��"/>
        </p>
        <html:submit value="����" onclick="return check()"/>
    </center>
</html:form>
</body>
</html>
