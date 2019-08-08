<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<html>

<head>
    <html:base/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<title>�Ŷι���HLR��ѯ���</title>

<body>
<center>
    <br>
    <form name="buttonbar">
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    &nbsp;&nbsp;<bean:message key="label.view"/>�Ŷι���HLR&nbsp;��
                </td>
            </tr>
        </table>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <logic:present name="VO" scope="request">
                <tr class="tr_show">
                    <td class="clsfth">����</td>
                    <td>
                        <bean:write name="VO" property="cityId" scope="request"/>
                    </td>

                </tr>
                <tr class="tr_show">
                    <td width="15%" class="clsfth">����HLR</td>
                    <td width="200" class="clsfth"><bean:write name="VO" property="hlrIdName" scope="request"/></td>
                </tr>
                <tr class="tr_show">
                    <td class="clsfth">��ʼ�Ŷ�(���)</td>
                    <td>
                        <bean:write name="VO" property="beginSegment" scope="request"/>
                    </td>

                </tr>
                <tr class="tr_show">
                    <td class="clsfth">��ֹ�Ŷ�(���)</td>
                    <td>
                        <bean:write name="VO" property="endSegment" scope="request"/>
                    </td>

                </tr>
                <tr class="tr_show">
                    <td class="clsfth">��ע</td>
                    <td>
                        <bean:write name="VO" property="note" scope="request"/>
                    </td>

                </tr>

            </logic:present>
        </table>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" height="32" align="right">
                    <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                           class="clsbtn2"/>
                </td>
            </tr>
        </table>
        <logic:notPresent name="VO" scope="request">
            <bean:message key="error.notfound"/>
        </logic:notPresent>
    </form>
</center>
</div>

</body>

</html>
