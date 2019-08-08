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
        考试信息发布
    </title>

    <script language="javascript">
        function check() {
            var form = document.forms[0];
            if (!checkLength(form.issueStarttime, 1, 20)) return false;
            if (!checkLength(form.issueEndtime, 1, 20)) return false;
            if (!confirm("是否确认发布？"))
                return false;
        }
    </script>

</head>
<body>
<html:form action="/issueDO" method="get">
    <SCRIPT language=javascript>
        <!--
        //以下四行用于日历显示
        var outObject;
        var old_dd = null;
        writeCalender();
        bltSetDay(bltTheYear, bltTheMonth);
        //-->
    </SCRIPT>
    <table cellpadding="0" cellspacing="0" width="95%">
        <tr>
            <td align="center" class="table_title">
                考试信息发布
            </td>
        </tr>
    </table>
    <center>
        <html:select property="configSel" size="20" value="0" style="width:600" title="发布选项">
            <html:options collection="ConfigList" property="value" labelProperty="label"/>
        </html:select>
        <br>
        <p>
            开始时间<font color="red">*</font>
            <html:text property="issueStarttime" style="width:200" onfocus="setday(this)" readonly="true" title="开始时间"/>
        </p>
        <p>
            结束时间<font color="red">*</font>
            <html:text property="issueEndtime" style="width:200" onfocus="setday(this)" readonly="true" title="结束时间"/>
        </p>
        <html:submit value="发布" onclick="return check()"/>
    </center>
</html:form>
</body>
</html>
