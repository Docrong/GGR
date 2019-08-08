<%@ page contentType="text/html; charset=GB2312" %>
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
    <form name="buttonbar">
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    &nbsp;&nbsp;<bean:message key="TawInfAddressBook.label"/>&nbsp;&nbsp;<bean:message
                        key="label.list"/></td>
            </tr>
            <tr>
                <td width="100%" height="25" align="right"><bean:write name="pagerHeader" scope="request"
                                                                       filter="false"/><%! String key;%></td>
            </tr>
        </table>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="td_label">
                <td width="11%" height="25"><bean:message key="TawInfAddressBook.company"/></td>
                <td width="10%" height="25"><bean:message key="TawInfAddressBook.deptName"/></td>
                <td width="7%" height="25"><bean:message key="TawInfAddressBook.name"/></td>
                <td width="8%" height="25"><bean:message key="TawInfAddressBook.groupName"/></td>
                <td width="11%" height="25"><bean:message key="TawInfAddressBook.mobile"/></td>
                <td width="11%" height="25"><bean:message key="TawInfAddressBook.officeTel"/></td>
                <td width="11%" height="25"><bean:message key="TawInfAddressBook.smart"/></td>
                <td width="19%" height="25"><bean:message key="TawInfAddressBook.email"/></td>
                <td width="4%" align="center">²é¿´</font></td>
                <td width="4%" align="center">ÐÞ¸Ä</font></td>
                <td width="4%" align="center">É¾³ý</font></td>

            </tr>
            <logic:iterate id="tawInfAddressBook" name="TAWAPMS" type="com.boco.eoms.infmanage.model.TawInfAddressBook">

                <%
                    String id = String.valueOf(tawInfAddressBook.getId());
                %>
                <tr class="tr_show">

                    <td width="11%" height="25"><bean:write name="tawInfAddressBook" property="company"
                                                            scope="page"/></td>
                    <td width="10%" height="25"><bean:write name="tawInfAddressBook" property="deptName"
                                                            scope="page"/></td>
                    <td width="7%" height="25"><bean:write name="tawInfAddressBook" property="name" scope="page"/></td>
                    <td width="8%" height="25"><bean:write name="tawInfAddressBook" property="groupName"
                                                           scope="page"/></td>
                    <td width="11%" height="25"><bean:write name="tawInfAddressBook" property="mobile"
                                                            scope="page"/></td>
                    <td width="11%" height="25"><bean:write name="tawInfAddressBook" property="officeTel"
                                                            scope="page"/></td>
                    <td width="11%" height="25"><bean:write name="tawInfAddressBook" property="smart"
                                                            scope="page"/></td>
                    <td width="19%" height="25"><a
                            href='<%=request.getContextPath()%>/infmanage/TawInfAddressBook/count.do?mail=<bean:write name="tawInfAddressBook" property="email" scope="page"/>&id=<%=id%>'
                            target="blank"><bean:write name="tawInfAddressBook" property="email" scope="page"/></a></td>

                    <%
                        java.util.HashMap map = new java.util.HashMap();
                        //map.put("userId", tawRmUser.getUserId());
                        map.put("id", String.valueOf(tawInfAddressBook.getId()));
                        pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
                    %>

                    <td width="4%" align="center">
                        <html:link page="/TawInfAddressBook/view.do" name="map" scope="page"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0"
                                alt="ÏÔÊ¾"></html:link>&nbsp;
                    </td>
                    <td width="4%" align="center">
                        <html:link page="/TawInfAddressBook/edit.do" name="map" scope="page"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"
                                alt="±à¼­"></html:link>&nbsp;
                    </td>
                    <td width="4%" align="center">
                        <html:link page="/TawInfAddressBook/del.do" name="map" scope="page"><img
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
                    <input Class="clsbtn2" type="button" name="toadd" value="<bean:message key="label.query"/>"
                           onclick="toQuery()">&nbsp;&nbsp;
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

    function toQuery() {
        window.location.href = "query.do";
    }

</script>
