<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.boco.eoms.common.tree.*" %>
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

    RelativeDrop rel = new RelativeDrop();
    String users = rel.strRelativeDrop(sdomIds);
//String users = rel.strRelativeDrop("");
%>

<script language="JavaScript">
    var User = new Array;
    var dept_Id;
    <%=users%>

    function toSubmit() {
        if (window.document.all.deptName.value == "") {
            alert("部门名称不能为空！");
            return false;
        }

        if (window.document.all.userId.value == "") {
            alert("用户编号不能为空！");
            return false;
        }

        if (window.document.all.userName.value == "") {
            alert("用户名称不能为空！");
            return false;
        }

        if (window.document.all.userAddress.value == "") {
            alert("用户IP地址不能为空！");
            return false;
        }

        if (window.document.all.userTel.value == "") {
            alert("联系电话不能为空！");
            return false;
        }

        if (window.document.all.userType.value == "") {
            alert("用户类型不能为空！");
            return false;
        }

        if (window.document.all.devPort.value == "") {
            alert("数据设备端口不能为空！");
            return false;
        }

        if (window.document.all.devId.value == "") {
            alert("设备编号不能为空！");
            return false;
        }

        if (window.document.all.userLogic.value == "") {
            alert("用户逻辑电路号不能为空！");
            return false;
        }

        if (window.document.all.logicPort.value == "") {
            alert("逻辑端口不能为空！");
            return false;
        }

        window.document.tawInfIpForm.submit();
    }
</script>

<html:html>
    <head>
        <title>修改用户IP地址资料</title>
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
        <script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
    </head>

    <html:form action="/TawInfIp/updatedone" method="post">
        <body>
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

            <font face="宋体" style="font-size: 9pt" COLOR="#990000">
                <B><bean:message key="label.deptTree"/></B>&nbsp;[&nbsp;<A
                    HREF="javascript:headerDisplay(0);"><bean:message key="label.hide"/></A>&nbsp;]</FONT>
            <br>
            <script type="text/javascript">
                var path = document.all.path.value;
                var domids = document.all.sdomids.value;
                var Tree = new Array;
                <%=strTree%>
                if (domids == "") {
                    createTree9(Tree, <%=regionId%>, 0, path, "", "",
                        "window.tawInfIpForm.cid", "selOnly",
                        "window.tawInfIpForm.deptId",
                        "window.tawInfIpForm.deptName", "tree");


                } else
                    createTree10(Tree, <%=regionId%>, 0, path, domids, "",
                        "window.tawInfIpForm.cid", "selOnly",
                        "window.tawInfIpForm.deptId",
                        "window.tawInfIpForm.deptName", "tree");

            </script>
        </div>
        <br>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" align="center" class="table_title">
                    <b>
                        <bean:message key="label.edit"/>&nbsp;<bean:message key="TawInfIp.Name"/>
                    </b>
                </td>
            </tr>
        </table>
        <html:hidden property="id"/>
        <html:hidden property="deptId"/>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;&nbsp;&nbsp;部门<font color="#FF0000">**</font></td>
                <td width="70%" height="25">
                    <html:text property="deptName" size="30" styleClass="clstext" readonly="true"/>
                    <A HREF="javascript:headerDisplay(1);">
                        <font face="宋体" style="font-size: 9pt"><bean:message key="label.deptTree"/></FONT></A>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.User_id"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="userId" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.User_name"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="userName" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.User_address"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="userAddress" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.User_tel"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="userTel" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.User_type"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="userType" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.Dev_port"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="devPort" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.Dev_id"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="devId" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.User_logic"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="userLogic" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.Logicport"/><font color="#FF0000">**</font>
                </td>
                <td width="70%" height="25">
                    <html:text styleClass="clstext" property="logicPort" size="50"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">&nbsp;
                    &nbsp<bean:message key="TawInfIp.Remark"/>
                </td>
                <td width="70%" height="25">
                    <html:textarea styleClass="clstext" property="remark" rows="10" cols="60"/>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="1" align="center">
            <tr>
                <td width="100%" colspan="2" height="25" bgcolor="#EEECED" align="right">
                    <input Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.save"/>"
                           onClick="toSubmit()">
                    &nbsp;
                    <input Class="clsbtn2" type="reset" name="toreset" value="<bean:message key="label.reset"/>">
                    &nbsp;&nbsp;
                </td>
            </tr>
        </table>
    </html:form>
    </body>
</html:html>