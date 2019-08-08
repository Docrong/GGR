<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*" %>
<html:html>
    <head>
        <title>子任务详细信息</title>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>
    <body>
    <html:form action="/project/taskdeldone" method="post">

        <style type="text/css">
            body {
                font-size: 9pt;
                color: #000000;
                LINE-HEIGHT: 18px
            }

            #tree {
                position: absolute;
                visibility: hidden;
                left: 72%;
                top: 10%;
                z-index: 2;
                background-color: #ECF2FE;
                padding: 12px;
                border-top: 1px solid #FeFeFe;
                border-left: 1px solid #FeFeFe;
                border-right: 3px solid #8E9295;
                border-bottom: 3px solid #8E9295;
            }
        </style>
        <br>
        <table border="0" width="95%" cellspacing="0">
            <bean:define id="project_name" name="projectForm" property="project_name" type="java.lang.String"/>
            <bean:define id="task_name" name="projectForm" property="task_name" type="java.lang.String"/>
            <bean:define id="exec_time" name="projectForm" property="task_exec_time" type="java.lang.String"/>
            <bean:define id="comp_time" name="projectForm" property="task_comp_time" type="java.lang.String"/>
            <bean:define id="dept_name" name="projectForm" property="task_dept_name" type="java.lang.String"/>
            <bean:define id="executor_name" name="projectForm" property="task_executor_name" type="java.lang.String"/>
            <bean:define id="scale" name="projectForm" property="task_scale" type="java.lang.Integer"/>
            <bean:define id="remark" name="projectForm" property="task_remark" type="java.lang.String"/>

            <html:hidden name="projectForm" property="id"/>
            <html:hidden name="projectForm" property="parent_id"/>
            <html:hidden name="projectForm" property="task_scale"/>
            <tr>
                <td width="106%" class="table_title" align="center">
                    <center><%=project_name%>项目----<%=task_name%>子任务信息</center>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>子任务名称</center>
                </td>
                <td width="70%" height="25">
                    <%=task_name%>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        子任务开始时间
                    </center>
                </td>
                <td width="75%" height="25">
                    <%=exec_time.substring(0, 10)%>
                </td>

            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        子任务完成时间
                    </center>
                </td>
                <td width="75%" height="25">
                    <%=comp_time.substring(0, 10)%>
                </td>

            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">
                    <center>子任务执行部门</center>
                </td>
                <td width="70%" height="25">
                    <%=dept_name%>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">
                    <center>子任务执行人</center>
                </td>
                <td width="70%" height="25">
                    <%=executor_name%>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>所占项目比例</center>
                </td>
                <td width="70%" height="25">
                            <%=scale%>%
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        子任务内容
                    </center>
                </td>
                <td width="75%" height="25">
                    <%=remark%>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" colspan="2" height="32" align="right">
                    <input Class="clsbtn2" type="submit" name="tosubmit" value="删除"/>
                    <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                           class="clsbtn2"/>
                </td>
            </tr>
        </table>
    </html:form>
    </body>
</html:html>
