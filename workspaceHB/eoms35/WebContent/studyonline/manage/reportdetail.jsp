<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*" %>

<html>
<head>
    <title>
        题库管理
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<script language="javascript">
    function del() {
        if (confirm("是否确认删除？")) {
            var form = document.forms[0];
            for (var i = 0; i < form.elements.length; i++) {
                var obj = form.elements[i];
                if (obj.type == 'checkbox') {
                    if (obj.checked) {
                        form.checkSel.value = form.checkSel.value + ",'" + obj.name + "'";
                    }
                }
            }
            form.checkSel.value = form.checkSel.value.substring(1);
            if (form.checkSel.value == "" || form.checkSel.value == null) {
                alert("请先选择删除项");
                return false;
            }
            return true;
        }
        return false;
    }

    function view(image) {
        var win;
        win = window.open("/EOMS_J2EE/studyonline/manage/view.jsp?fileName=" + image, "图片显示", "height=350,width=350,left=0,top=350,resizable=yes,scrollbars=yes,status=no");
    }
</script>

<%
    String importId = StaticMethod.null2String(request.getParameter("importId"));
    String urlM = "modifySub.do";
    //String urlD = "deleteSub.do";
    int i = 0;
%>
<body bgcolor="#ffffff">
<html:form action="physicalDelSub.do">
    <html:hidden property="checkSel" value=""/>
    <html:hidden property="importId" value="<%=importId%>"/>
</html:hidden>
<table cellpadding="1" cellspacing="1" width="95%" align="center" border="0" class="table_show">
    <tr>
        <td width="6%" class="td_label">
        </td>
        <td width="6%" class="td_label">
            序号
        </td>
        <td class="td_label" align="center" width="70%">
            标题
        </td>
        <td class="td_label" align="center" width="6%">
            类型
        </td>
        <td class="td_label" align="center" width="6%">
            图片
        </td>
        <td class="td_label" align="center" width="6%">
            修改
        </td>
    </tr>
</table>
<center>
    <div id="divTable"
         style="position: relative; align: center; top: 0px;width:  95%; height:  88%; z-index: 1; overflow: auto; overflow-x: hidden">
        <table cellpadding="1" cellspacing="1" width="100%" border="0" class="table_show">
            <logic:iterate id="onlineWarehouse" name="SUBLIST" type="com.boco.eoms.studyonline.model.OnlineWarehouse">
                <%
                    String urlImage = StaticMethod.getGBString(
                            StaticMethod.null2String(onlineWarehouse.getImage()));
                    java.util.HashMap map = new java.util.HashMap();
                    map.put("subId", onlineWarehouse.getSubId());
                    pageContext.setAttribute("map", map);
                %>
                <tr class="tr_show" align="center">
                    <td width="6%">
                        <input type="checkbox" name="<%=onlineWarehouse.getSubId()%>">
                    </td>
                    <td width="6%">
                        <%=++i%>
                    </td>
                    <td width="70%" align="left">
                        <bean:write name="onlineWarehouse" property="title"/>
                    </td>
                    <td width="6%" align="left">
                        <logic:equal name="onlineWarehouse" property="issueType" value="1">
                            学习
                        </logic:equal>
                        <logic:equal name="onlineWarehouse" property="issueType" value="2">
                            考试
                        </logic:equal>
                    </td>
                    <td width="6%">
                        <logic:notEmpty name="onlineWarehouse" property="image">
                            <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0"
                                 onclick="view('<%=urlImage%>')">
                        </logic:notEmpty>
                    </td>
                    <td width="6%">
                        <html:link href="<%=urlM%>" name="map">
                            <img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0">
                        </html:link>
                    </td>
                </tr>
            </logic:iterate>
        </table>
    </div>
</center>
<br>
<center>
    <html:submit value="删除" onclick="return del()"/>
</center>
</html:form>
</body>
</html>

