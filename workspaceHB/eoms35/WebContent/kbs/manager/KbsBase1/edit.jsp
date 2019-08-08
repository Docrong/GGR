<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" import="java.util.*,java.lang.*,java.io.*,org.apache.struts.util.*" %>
<%@ page import="com.boco.eoms.common.tree.WKTree" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.common.util.StaticVariable" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%
    String webapp = request.getContextPath();
    String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"), "1");
    String path = request.getContextPath();
    WKTree wk_tree = new WKTree();
    String strTree = wk_tree.strWKTree(Integer.parseInt(regionId));
    String sdomIds = StaticMethod.nullObject2String(request.getAttribute("SDOMIDS"));
    String spes = StaticMethod.nullObject2String(request.getAttribute("SPELIST"));
    String subspes = StaticMethod.nullObject2String(request.getAttribute("SUBSPELIST"));
    String flag = StaticMethod.nullObject2String(request.getAttribute("FLAG"));
    String sheetflag = StaticMethod.nullObject2String(request.getAttribute("SHEETFLAG"), "");
    String worksheetId = StaticMethod.nullObject2String(request.getAttribute("WORKSHEETID"), "");
    String sheetId = StaticMethod.nullObject2String(request.getAttribute("SHEETID"), "");
    String mapPath = StaticMethod.nullObject2String(request.getAttribute("MAPPATH"), "");
%>


<SCRIPT type="text/javascript">


    function goto_transmit() {
        if (checkInput())
            document.kbsBaseForm.submit();
    }

    function checkInput() {
        var code = document.kbsBaseForm.code.value;
        var name = document.kbsBaseForm.name.value;
        var noteslength;

        if (code == "" || code == null) {
            alert("案例编码不能为空.");
            return false;
        } else if (name == "" || name == null) {
            alert("案例名称不能为空.");
            return false;
        }

        return true;
    }

    function dealTimeLimit() {
    }


</SCRIPT>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<?import namespace=BOCOimplementation="<%=request.getContextPath()%>/css/button/genericButton.htc"/>


<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>

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

<html:html>
    <head>
        <title>编辑案例</title>
        <html:base/>
    </head>
    <eoms:DictType typeName="ApplyType"/>
    <eoms:DictType typeName="CompliantType"/>
    <body>
    <!--<base target="_self">-->
    <html:form method="post" action="/KbsBase/update">
        <script language="javascript">
            <html:javascript formName="kbsBaseForm" dynamicJavascript="true" staticJavascript="true"/>
        </script>
        <input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">
        <input type="hidden" name="path" id="path" value="<%=path%>">
        <input type="hidden" name="mapPath" id="mapPath" value="<%=mapPath%>">
        <div id="tree">
            <font face="宋体" style="font-size: 9pt" COLOR="#990000"><B><bean:message key="label.deptTree"/></B>&nbsp;[&nbsp;<A
                    HREF="javascript:headerDisplay(0);"><bean:message key="label.hide"/></A>&nbsp;]</font>
            <BR>
            <script type="text/javascript">

                var path = document.all.path.value;
                var domids = document.all.sdomids.value;
                var Tree = new Array;
                <%=strTree%>
                if (domids == "")
                    createTree9(Tree, <%=regionId%>, 0, path, "", "",
                        "window.kbsBaseForm.cid", "selOnly",
                        "window.kbsBaseForm.deptId", "window.kbsBaseForm.deptName", "tree");
                else
                    createTree10(Tree, <%=regionId%>, 0, path, domids, "",
                        "window.kbsBaseForm.cid", "selOnly",
                        "window.kbsBaseForm.deptId", "window.kbsBaseForm.deptName", "tree")
                    ;

            </script>
        </div>
        <center>
            <br>
            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="100%" class="table_title" align="center">
                        <b>
                            <bean:message key="label.edit"/>&nbsp;案例
                        </b>　
                    </td>
                </tr>
            </table>

            <table border="0" width="95%" cellspacing="1" cellpadding="1"
                   class="table_show" align=center>

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;案例编码
                    </td>
                    <td width="70%" height="25">
                        <html:text styleClass="clstext" property="code" size="30"/>
                        <font color="#FF0000">**</font>
                    </td>
                </tr>
                <%if (!sheetId.equals("")) {%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        工单号
                    </td>
                    <td width="70%" height="25">
                        <%if (sheetflag.equals("0")) {%>
                        <a href="/EOMS_J2EE/newworksheet/Faultsheet/detail.do?id=<%=worksheetId.toString()%>"><%=sheetId.toString()%>
                        </a>
                        <%} else {%>
                        <a href="/EOMS_J2EE/newworksheet/Applysheet/detail.do?id=<%=worksheetId.toString()%>"><%=sheetId.toString()%>
                        </a>
                        <%}%>
                    </td>
                </tr>
                <%}%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;案例主题
                    </td>
                    <td width="70%" height="25">
                        <html:text styleClass="clstext" property="name" size="30"/>
                        <font color="#FF0000">**</font>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="25%" class="clsfth">&nbsp;提交人</td>
                    <bean:define id="authorDeptName" name="kbsBaseForm" property="authorDeptName"
                                 type="java.lang.String"/>
                    <bean:define id="authorName" name="kbsBaseForm" property="authorName" type="java.lang.String"/>
                    <td width="75%"><%=authorDeptName%> <%=authorName%>
                    </td>

                </tr>
                <tr class="tr_show">
                    <td width="25%" class="clsfth">&nbsp;作者</td>

                    <bean:define id="zuozhe" name="kbsBaseForm" property="zuozhe" type="java.lang.String"/>
                    <td width="75%"><%=StaticMethod.getGBString(StaticMethod.null2String(zuozhe, ""))%>
                    </td>

                </tr>
                <%
                    System.out.println(flag);
                    if (flag.equals("1")) {
                %>
                <tr class="tr_show">
                    <td class="clsfth">投诉类型</td>
                    <td>
                        <html:select property="applyType" style="width: 3.6cm;">
                            <OPTION VALUE="*15">其他网络投诉</OPTION>
                            <OPTION VALUE="*14">他数据业务类投诉</OPTION>

                            <OPTION VALUE="*13">CMNET业务类投诉</OPTION>
                            <OPTION VALUE="*12">GPRS业务投诉类</OPTION>
                            <OPTION VALUE="*11">话音增值业务类投诉</OPTION>
                            <OPTION VALUE="*10">交叉覆盖类投诉</OPTION>
                            <OPTION VALUE="*9">HLR故障类投诉</OPTION>
                            <OPTION VALUE="*8">智能网平台类投诉</OPTION>
                            <OPTION VALUE="*7">彩信业务类投诉</OPTION>
                            <OPTION VALUE="*6">短信业务类投诉</OPTION>
                            <OPTION VALUE="*5">IP电话类投诉</OPTION>

                            <OPTION VALUE="*4">互联互通类投诉</OPTION>
                            <OPTION VALUE="*3">漫游类</OPTION>
                            <OPTION VALUE="*2">通信质量</OPTION>
                            <OPTION VALUE="*1">网络覆盖</OPTION>

                        </html:select>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td class="clsfth">用户品牌</td>
                    <td>
                        <html:select property="custType" style="width: 3.6cm;">
                            <html:options collection="CompliantType" property="value" labelProperty="label"/>
                        </html:select>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;关键字
                    </td>
                    <td width="70%" height="25">
                        <html:text styleClass="clstext" property="keyword" size="30"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;投诉描述
                    </td>
                    <td width="70%" height="25">
                        <html:textarea rows="6" style="width:10.8cm;" styleClass="clstext" property="description"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;投诉原因分析
                    </td>
                    <td width="70%" height="25">
                        <html:textarea rows="6" style="width:10.8cm;" styleClass="clstext" property="cause"/>
                    </td>
                </tr>
                <%} else {%>
                <tr class="tr_show">
                    <td class="clsfth">专业类型</td>
                    <td>
                        <eoms:SelectDictRel jsDealTimeLimit="true" name="specialtyType" typeName="Specialty"
                                            formName="kbsBaseForm" relProperty="faultType" relTypeName="FaultType"
                                            value="15"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td class="clsfth">故障类型</td>
                    <td>
                        <html:select property="faultType" style="width: 3.6cm;">
                            <html:options collection="faultType" property="value" labelProperty="label"/>
                        </html:select>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;关键字
                    </td>
                    <td width="70%" height="25">
                        <html:text styleClass="clstext" property="keyword" size="30"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;故障描述
                    </td>
                    <td width="70%" height="25">
                        <html:textarea rows="6" style="width:10.8cm;" styleClass="clstext" property="description"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;故障原因分析
                    </td>
                    <td width="70%" height="25">
                        <html:textarea rows="6" style="width:10.8cm;" styleClass="clstext" property="cause"/>
                    </td>
                </tr>
                <%}%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;处理过程
                    </td>
                    <td width="70%" height="25">
                        <html:textarea property="deal" rows="6" style="width:10.8cm;" styleClass="clstext"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;附件
                    </td>
                    <td width="70%" height="25">
                        <eoms:attachment name="kbsBaseForm" property="attachName" scope="request" idField="attachName"
                                         appCode="kbs"/>
                    </td>
                </tr>
            </table>
            <br/>
            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="100%" height="32" align="right">
                        <html:hidden property="id"/>
                        <input type="submit" class="clsbtn2" onclick="checkInput()" value=
                            <bean:message key="label.save"/> name="button1">&nbsp;&nbsp;
                        <html:reset styleClass="clsbtn2">
                            <bean:message key="label.reset"/>
                        </html:reset>
                        <input type="button" class="clsbtn2" value="<bean:message key="label.cancel"/>" name="button"
                               onclick="history.back();">
                        &nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </center>
    </html:form>
    <logic:messagesPresent>
        <html:messages id="error">
            <script type="text/javascript">
                <!--
                alert("<bean:write name="error"/>");
                -->
            </script>
        </html:messages>
    </logic:messagesPresent>

    </body>
</html:html>
