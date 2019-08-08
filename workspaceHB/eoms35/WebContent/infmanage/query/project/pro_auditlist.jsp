<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<html:html>
    <head>
        <title><bean:message key="label.list"/></title>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>

    <form name="form1">
        <body>
        <br>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>


                <td width="100%" align="center" class="table_title">
                    <b>
                        &nbsp;&nbsp;我的项目审核列表
                    </b>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="tr_show">
                <td width="100%" colspan="12" height="25" align="center"><bean:write name="pagerHeader" scope="request"
                                                                                     filter="false"/><%! String key;%></td>
            </tr>


            <tr class="tr_show">
                <td width="15%" height="25" class="clsfth" align="center">
                    <center>项目名称</center>
                </td>

                <td width="5%" height="25" class="clsfth" align="center">
                    <center>派发人</center>
                </td>
                <td width="15%" height="25" class="clsfth" align="center">
                    <center>开始时间</center>
                </td>
                <td width="15%" height="25" class="clsfth" align="center">
                    <center>完成时间</center>
                </td>
                <td width="5%" height="25" class="clsfth" align="center">
                    <center>执行人</center>
                </td>
                <td width="5%" height="25" class="clsfth" align="center">
                    <center>考核成绩</center>
                </td>


            </tr>

            <%
                String pro_code = StaticMethod.getNodeName("SYSTEM.DICTTYPE.project_code");
                int listNum = 0;

            %>
            <logic:iterate id="project" name="Project_LIST" type="com.boco.eoms.infmanage.model.Project">

                <bean:define id="sign" name="project" property="project_sign" type="java.lang.Integer"/>

                <%
                    java.util.HashMap map = new java.util.HashMap();

                    map.put("pro_code", pro_code);
                    map.put("id", String.valueOf(project.getProject_id()));  //id
                    pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
                %>

                <tr class="tr_show">
                    <td width="5%" height="25" class="clsfth" align="center">
                        <html:link page="/project/view.do" name="map" scope="page">
                            <center><bean:write name="project" property="project_name" scope="page"/></center>
                        </html:link></td>


                    <td width="5%" height="25" class="clsfth" align="center">
                        <center><bean:write name="project" property="project_send_user" scope="page"/></center>
                    </td>
                    <td width="15%" height="25" class="clsfth" align="center">
                        <center><bean:write name="project" property="project_exec_time" scope="page"/></center>
                    </td>
                    <td width="15%" height="25" class="clsfth" align="center">
                        <center><bean:write name="project" property="project_comp_time" scope="page"/></center>
                    </td>
                    <td width="5%" height="25" class="clsfth" align="center">
                        <center><bean:write name="project" property="project_executor_name" scope="page"/></center>
                    </td>

                    <td width="5%" height="25" class="clsfth" align="center">
                        <center><font color="red"><bean:write name="project" property="pro_audit" scope="page"/></font>
                        </center>
                    </td>


                </tr>
            </logic:iterate>
            　
        </table>
        <table border="0" width="100%" cellspacing="0">
            <tr>
                <td width="100%" colspan="10" height="32" align="right">
                    <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                           class="clsbtn2"/>
                </td>
            </tr>
        </table>
        </body>

    </form>
</html:html>
