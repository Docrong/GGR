<%@ page contentType="text/html; charset=GB2312" pageEncoding="GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page
        import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.SaveSessionBeanForm,java.util.*,java.io.*" %>
<html:html>
    <head>
        <title>
            分数情况查询
        </title>
        <script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>

    <%
        String userId = ((SaveSessionBeanForm) session.getAttribute(
                "SaveSessionBeanForm")).getWrf_UserID();
        String userNames = ((SaveSessionBeanForm) session.getAttribute(
                "SaveSessionBeanForm")).getWrf_UserName();
        String studyexami = (String) request.getAttribute("studyexami");
        int o = 0;
    %>

    <body bgcolor="#ffffff">
    <table cellpadding="0" cellspacing="0" width="95%">
        <tr>
            <td align="center" class="table_title">
                分数情况查询
            </td>
        </tr>
    </table>
    <table cellpadding="1" cellspacing="1" width="95%" align="center" border="0" class="table_show">
        <tr align="center" class="tr_show">
            <td class="td_label" width="20%">
                用户名
            </td>
            <td class="td_label" width="16%">
                总分
            </td>
            <td class="td_label" width="16%">
                正确数
            </td>
            <td class="td_label" width="16%">
                总体数
            </td>
            <td class="td_label" width="16%">
                正确率
            </td>
            <td class="td_label" width="16%">
                显示明细
            </td>
        </tr>
    </table>
    <center>
        <div id="divTable"
             style="position: relative; align: center; top: 0px;width:  95%; height:  380; z-index: 1; overflow: auto; overflow-x: hidden">
            <%if (studyexami.equals("")) {%>
            <table cellpadding="1" cellspacing="1" width="100%" border="0" class="table_show">
                <logic:iterate id="studyQO" name="QOlist" type="com.boco.eoms.studyonline.qo.studyQO">
                    <tr class="tr_show" align="center">
                        <td width="20%">
                            <bean:write name="studyQO" property="userName"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="mark"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="right"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="total"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="rate"/>%
                        </td>
                        <td width="16%">
                            <%
                                if (!"study".equalsIgnoreCase(studyQO.getIssueId())
                                        || userId.equalsIgnoreCase(studyQO.getUserId())) {
                                    java.util.HashMap map = new java.util.HashMap();
                                    map.put("userId", studyQO.getUserId());
                                    map.put("issueId", studyQO.getIssueId());
                                    pageContext.setAttribute("map", map);
                            %>
                            <html:link href="detail.do" name="map">
                                <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0">
                            </html:link>
                            <%}%>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
            <%} else if (studyexami.equals("1") && userId.equals("admin")) {%>
            <table cellpadding="1" cellspacing="1" width="100%" border="0" class="table_show">
                <logic:iterate id="studyQO" name="QOlist" type="com.boco.eoms.studyonline.qo.studyQO">
                    <bean:define id="userName" name="studyQO" property="userName" type="java.lang.String"/>

                    <tr class="tr_show" align="center">
                        <td width="20%">
                            <bean:write name="studyQO" property="userName"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="mark"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="right"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="total"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="rate"/>%
                        </td>
                        <td width="16%">
                            <%
                                if (!"study".equalsIgnoreCase(studyQO.getIssueId())
                                        || userId.equalsIgnoreCase(studyQO.getUserId())) {
                                    java.util.HashMap map = new java.util.HashMap();
                                    map.put("userId", studyQO.getUserId());
                                    map.put("issueId", studyQO.getIssueId());
                                    pageContext.setAttribute("map", map);
                            %>
                            <html:link href="detail.do" name="map">
                                <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0">
                            </html:link>
                            <%}%>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
            <%} else if (studyexami.equals("1") && !userId.equals("admin")) {%>
            <table cellpadding="1" cellspacing="1" width="100%" border="0" class="table_show">
                <logic:iterate id="studyQO" name="QOlist" type="com.boco.eoms.studyonline.qo.studyQO">
                    <bean:define id="userName" name="studyQO" property="userName" type="java.lang.String"/>
                    <%
                        if (userNames.equals(userName)) {%>
                    <tr class="tr_show" align="center">
                        <td width="20%">
                            <bean:write name="studyQO" property="userName"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="mark"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="right"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="total"/>
                        </td>
                        <td width="16%">
                            <bean:write name="studyQO" property="rate"/>%
                        </td>
                        <td width="16%">
                            <%
                                if (!"study".equalsIgnoreCase(studyQO.getIssueId())
                                        || userId.equalsIgnoreCase(studyQO.getUserId())) {
                                    java.util.HashMap map = new java.util.HashMap();
                                    map.put("userId", studyQO.getUserId());
                                    map.put("issueId", studyQO.getIssueId());
                                    pageContext.setAttribute("map", map);
                            %>
                            <html:link href="detail.do" name="map">
                                <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0">
                            </html:link>
                            <%}%>
                        </td>
                    </tr>
                    <%
                            o++;
                        }
                    %>
                </logic:iterate>
                <% if (o == 0) {%>
                <tr>没有你的考试结果</tr>
                <%
                    }
                    o = 0;%>
            </table>
            <%}%>
        </div>
    </center>
    </body>
</html:html>
