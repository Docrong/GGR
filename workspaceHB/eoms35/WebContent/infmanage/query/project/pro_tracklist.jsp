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
                        &nbsp;&nbsp;项目跟踪信息列表
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


                <td width="25%" height="25" class="clsfth" align="center">
                    <center>执行时间</center>
                </td>
                <td width="25%" height="25" class="clsfth" align="center">
                    <center>完成比例</center>
                </td>
                <td width="25%" height="25" class="clsfth" align="center">
                    <center>执行人</center>
                </td>


            </tr>
            <%
                String pro_code = StaticMethod.getNodeName("SYSTEM.DICTTYPE.project_code");
                int listNum = 0;

            %>

            <logic:iterate id="project" name="Project_LIST" type="com.boco.eoms.infmanage.model.Project">

                <tr class="tr_show">
                    <td width="25%" height="25" class="clsfth" align="center">
                        <center><bean:write name="project" property="track_exec_time" scope="page"/></center>
                    </td>

                    <td width="25%" height="25" class="clsfth" align="center">
                        <center><bean:write name="project" property="project_sign" scope="page"/>%</center>
                    </td>
                    <td width="25%" height="25" class="clsfth" align="center">
                        <center><bean:write name="project" property="project_executor_name" scope="page"/></center>
                    </td>

                </tr>
            </logic:iterate>
            　
        </table>

        </body>


    </form>
</html:html>
