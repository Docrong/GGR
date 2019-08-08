<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<html:html>
    <head>
        <title>用户IP地址资料列表</title>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>

    <form name="listForm">
        <body>
        <br>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" align="center" class="table_title">
                    <b>
                        &nbsp;&nbsp;<bean:message key="TawInfIp.Name"/><bean:message key="label.list"/>
                    </b>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="tr_show">
                <td width="100%" colspan="10" height="25" align="center"><bean:write name="pagerHeader" scope="request"
                                                                                     filter="false"/><%! String key;%></td>
            </tr>
            <tr class="tr_show">
                <td width="5%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.User_id"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.User_name"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.Dept_name"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message
                        key="TawInfIp.User_address"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.User_type"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.Dev_port"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.Dev_id"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.User_logic"/></td>

                <td width="10%" height="25" class="clsfth" align="center"><bean:message key="TawInfIp.Logicport"/></td>

                <td width="5%" height="25" align="center"><font color="#cc0000">查看</font></td>

                <td width="5%" height="25" align="center"><font color="#cc0000">修改</font></td>

                <td width="5%" height="25" align="center"><font color="#cc0000">删除</font></td>
            </tr>

            <logic:iterate id="tawInfIp" name="TAW_INF_IP_LIST" type="com.boco.eoms.infmanage.model.TawInfIp">
                <tr class="tr_show">
                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="userId"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="userName"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="deptName"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="userAddress"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="userType"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="devPort"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="devId"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="userLogic"
                                                                                          scope="page"/></td>

                    <td width="10%" height="25" class="clsfth" align="center"><bean:write name="tawInfIp"
                                                                                          property="logicPort"
                                                                                          scope="page"/></td>

                    <%
                        java.util.HashMap map = new java.util.HashMap();

                        map.put("id", String.valueOf(tawInfIp.getId()));  //记录id
                        map.put("deptId", String.valueOf(tawInfIp.getDeptId()));  //部门Id
                        pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
                    %>

                    <td width="5%" height="25" align="center"><font color="#cc0000"><html:link page="/TawInfIp/view.do"
                                                                                               name="map"
                                                                                               scope="page"><img
                            src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" alt="显示"></html:link>&nbsp;</font>
                    </td>

                    <td width="5%" height="25" align="center"><font color="#cc0000"><html:link
                            page="/TawInfIp/update.do" name="map" scope="page"><img
                            src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0" alt="编辑"></html:link>&nbsp;</font>
                    </td>

                    <td width="5%" height="25" align="center"><font color="#cc0000"><html:link page="/TawInfIp/del.do"
                                                                                               name="map"
                                                                                               scope="page"><img
                            src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0" alt="删除"></html:link>&nbsp;</font>
                    </td>
                </tr>
            </logic:iterate>
            　
        </table>
        <table border="0" width="100%" cellspacing="0">
            <tr>
                <td width="100%" colspan="10" height="32" align="right">
                    <input type='button' Class="clsbtn2" value='打印' onclick='javascript:window.print()'>&nbsp;&nbsp;
                    <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                           class="clsbtn2"/>
                </td>
            </tr>
        </table>
        </body>

    </form>
</html:html>
