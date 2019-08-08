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

<title>新增地市未确定HLR归属的号段</title>

<html:html>

<html:form method="post" action="/TawBureaudataNobelonghlr/save">
<body>

<div align="center">
    <center>
        <br>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    <bean:message key="label.add"/>地市未确定HLR归属的号段
                </td>
            </tr>
        </table>

        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="tr_show">
                <td class="clsfth">地市编号</td>
                <td><eoms:CityzoneDict/></td>
                <%//Modifed By Matao 2007-11-09 更改了城市编号标签%>
            </tr>
            <tr class="tr_show">
                <td width="15%" class="clsfth">起始号段（万号）</td>
                <td width="400"><html:text property="beginSegment" title="起始号段（万号）"/></td>
            </tr>
            <tr class="tr_show">
                <td width="15%" class="clsfth">终止号段（万号）</td>
                <td width="400"><html:text property="endSegment" title="终止号段（万号）"/></td>
            </tr>


            <tr class="tr_show">
                <input type="hidden" name="belongFlag" value="N"/>
                <td width="15%" class="clsfth">局数据编号</td>
                <td width="400"><html:text property="bureauNo" title="局数据编号"/></td>
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
