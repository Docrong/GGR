<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="com.boco.eoms.common.tree.WKTree" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%
    String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"), "1");
    String path = request.getContextPath();
    WKTree wk_tree = new WKTree();
    String strTree = wk_tree.strWKTree(Integer.parseInt(regionId));
    String url = "";
    String dept1 = "";
    String wsClass = "-1";
    String sdomIds = StaticMethod.nullObject2String(request.getAttribute("SDOMIDS"));
%>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
</head>

<title><bean:message key="label.query"/><bean:message key="TawRmUser.name"/></title>

<body background="<%=request.getContextPath()%>/images/img/bg001.gif" onLoad="initIt();">
<html:form method="get" action="/queryDo">
    <!--html:hidden property="strutsAction" value="3"/-->

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


    <input type="hidden" name="path" id="path" value="<%=path%>">
    <input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">
    <div id="tree">
        <font face="ËÎÌו" style="font-size: 9pt" COLOR="#990000"><B><bean:message
                key="label.deptTree"/></B>&nbsp;[&nbsp;<A HREF="javascript:headerDisplay(0);"><bean:message
                key="label.hide"/></A>&nbsp;]</FONT>
        <BR>
        <script type="text/javascript">
            var path = document.all.path.value;
            var domids = document.all.sdomids.value;
            var Tree = new Array;
            <%=strTree%>
            if (domids == "")
                createTree9(Tree, <%=regionId%>, 0, path, "", "",
                    "window.studyQueryForm.cid", "selOnly",
                    "window.studyQueryForm.deptId", "window.studyQueryForm.deptName", "tree");
            else
                createTree10(Tree, <%=regionId%>, 0, path, domids, "",
                    "window.studyQueryForm.cid", "selOnly",
                    "window.studyQueryForm.deptId", "window.studyQueryForm.deptName", "tree");
        </script>
    </div>

    <div align="center">
        <center>
            <br>
            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="106%" class="table_title" align="center"><bean:message key="label.query"/></td>
                </tr>
            </table>
            <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;<bean:message key="TawRmUser.userId"/></td>
                    <td width="70%" height="25">&nbsp;&nbsp;&nbsp;<html:text property="userId" size="30"
                                                                             styleClass="clstext"/></td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;<bean:message key="TawRmUser.userName"/></td>
                    <td width="70%" height="25">&nbsp;&nbsp;&nbsp;<html:text property="userName" size="30"
                                                                             styleClass="clstext"/></td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;<bean:message key="TawRmUser.deptId"/></td>
                    <td width="70%" height="25">&nbsp;&nbsp;&nbsp;<html:text property="deptName" size="30"
                                                                             styleClass="clstext" readonly="true"/>
                        <A HREF="javascript:headerDisplay(1);">
                            <font face="ËÎÌו" style="font-size: 9pt"><bean:message key="label.deptTree"/></FONT>
                        </A>
                        <html:hidden property="deptId"/>
                    </td>
                </tr>

                <logic:messagesPresent>
                    <bean:message key="errors.header"/>
                    <ul>
                        <html:messages id="error">
                            <li>
                                <bean:write name="error"/>
                            </li>
                        </html:messages>
                    </ul>
                    <hr/>
                </logic:messagesPresent>
            </table>
            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="100%" height="32" align="right">
                        <html:submit property="strutsButton" styleClass="clsbtn2">
                            <bean:message key="label.query"/>
                        </html:submit>
                        &nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </center>
    </div>
</html:form>

</body>

</html>
