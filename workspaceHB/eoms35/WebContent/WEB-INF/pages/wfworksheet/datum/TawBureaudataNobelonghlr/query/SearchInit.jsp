<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>

<html:html>
    <head>
        <script language="javascript" src="<%=request.getContextPath()%>/css/DataFormat.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/css/calendar.js"></script>

        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">

    </head>

    <body>

    <center>

        <html:form action="/TawBureaudataNobelonghlr/searchDo">
        <center>

            <table cellSpacing="0" cellPadding="0" width="60%" border="0">
                <TR>
                    <td width="100%" class="table_title">��ѯ����δȷ��HLR�����ĺŶ�
                        <bean:message key="Worksheet.selectSearchConditon"/><!--��ѡ���ѯ����--></td>
                </tr>
                <tr>
                    <TD align="right">
                    </TD>
                </TR>
            </table>
            <table cellSpacing="0" cellPadding="0" width="60%" border="0">
                <TR>
                    <TD align="right" width="100%" colspan=2>
                        <div align="center">
                            <table border="0" cellspacing="1" cellpadding="1" width="80%" class="table_show"
                                   align=center>
                                <tr class="tr_show">
                                    <td noWrap width="20%">���б��</td>
                                    <td width="70%"><eoms:CityzoneDict/></td>
                                </tr>
                                <tr class="tr_show">
                                    <td noWrap width="20%">��ʼ�ŶΣ���ţ�(
                                        <bean:message key="Worksheet.blurSearch"/><!--ģ����ѯ--> )
                                    </td>
                                    <td width="70%"><html:text property="beginSegment" size="30"/></td>
                                </tr>
                                <tr class="tr_show">
                                    <td noWrap width="20%">��ֹ�ŶΣ���ţ�(
                                        <bean:message key="Worksheet.blurSearch"/><!--ģ����ѯ--> )
                                    </td>
                                    <td width="70%"><html:text property="endSegment" size="30"/></td>
                                </tr>
                                <!--   <tr class="tr_show">
			<td noWrap width="20%">�Ƿ���ȷ������</td>
			<td width="70%"><eoms:NetoptDict pageParamName="belongFlag" dictType="7003" /></td>
		</tr>
 -->
                            </table>
                            <BR>
                            <TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
                                <TR>
                                    <TD align="right">
                                        <html:submit styleClass="clsbtn2">
                                            ��ѯ
                                        </html:submit>
                                        &nbsp;
                                    </TD>
                                </TR>
                            </TABLE>

        </center>
        </html:form>
    </body>
</html:html>
