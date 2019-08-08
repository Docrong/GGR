<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<body>
<center>
    <br>
    <form>
        <table border="0" width="60%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    &nbsp;&nbsp;<bean:message key="label.list"/></td>
            </tr>
            <tr>
                <td width="100%" height="25" align="right"><bean:write name="pagerHeader" scope="request"
                                                                       filter="false"/><%! String key;%></td>
            </tr>
        </table>

        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="td_label">

                <td width="40%" height="25"><bean:message key="TawInfAddressBook.groupName"/></td>


                <td width="30%" align="center">ÐÞ¸Ä</font></td>
                <td width="30%" align="center">É¾³ý</font></td>

            </tr>
            <logic:iterate id="tawInfGroup" name="TAWAPMS" type="com.boco.eoms.infmanage.model.TawInfGroup">

                <tr class="tr_show">

                    <td width="40%" height="25" align="center"><bean:write name="tawInfGroup" property="groupName"
                                                                           scope="page"/></td>


                    <%
                        java.util.HashMap map = new java.util.HashMap();
                        //map.put("userId", tawRmUser.getUserId());
                        map.put("id", String.valueOf(tawInfGroup.getGroupId()));
                        pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
                    %>


                    <td width="30%" align="center">
                        <html:link page="/TawInfGroup/edit.do" name="map" scope="page"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"
                                alt="±à¼­"></html:link>&nbsp;
                    </td>
                    <td width="30%" align="center">
                        <html:link page="/TawInfGroup/delDo.do" name="map" scope="page"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0"
                                alt="É¾³ý"></html:link></td>

                </tr>

            </logic:iterate>
        </table>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" height="32" align="right">
                    <input Class="clsbtn2" type="button" name="toadd" value="<bean:message key="label.add"/>"
                           onclick="toAdd()">&nbsp;&nbsp;
                    <input type='button' Class="clsbtn2" value='´òÓ¡' onclick='javascript:window.print()'>&nbsp;&nbsp;

                </td>
            </tr>
        </table>

    </form>
</center>


</body>
</html>
<script language="javascript">

    function toAdd() {
        window.location.href = "add.do";
    }
</script>
