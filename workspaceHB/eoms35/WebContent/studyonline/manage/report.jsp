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
        题目报批
    </title>

    <script language="javascript">
        function selectSpecialty() {
            document.all.specialtySel.value = "true";
        }

        function del() {
            document.forms[0].action = "delImport.do";
        }

        function reportDO() {
            document.forms[0].action = "reportDO.do";
        }

        function checkform() {
            var form = document.forms[0];
            if (form.specialtySel.value != "true" && form.action != "delImport.do") {
                alert("必须选择专业类型");
                return false;
            }

            for (var i = 0; i < form.elements.length; i++) {
                var obj = form.elements[i];
                if (obj.type == 'checkbox') {
                    if (obj.checked) {
                        form.checkSel.value = form.checkSel.value + ",'" + obj.name + "'";
                    }
                }
            }
            form.checkSel.value = form.checkSel.value.substring(1);
            var msg = "";
            if (form.action == "delImport.do")
                msg = "删除";
            else
                msg = "报批";
            if (form.checkSel.value == "") {
                alert(msg + "项尚未选择");
                return false;
            }
            if (!confirm("是否确认" + msg + "操作"))
                return false;
        }
    </script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <eoms:DictType typeName="Specialty"/>
    <%int i = 1;%>

</head>
<body bgcolor="#ffffff">
<html:form action="/reportDO" onsubmit="return checkform()">
    <html:hidden property="checkSel" value=""/>
    <html:hidden property="specialtySel" value=""/>
    <table cellpadding="1" cellspacing="1" width="95%" align="center" border="0" class="table_show">
        <tr>
            <td width="6%" class="td_label" align="center">
            </td>
            <td width="6%" class="td_label" align="center">
                序号
            </td>
            <td class="td_label" align="center" width="17%">
                入库人
            </td>
            <td class="td_label" align="center" width="23%">
                入库时间
            </td>
            <td class="td_label" align="center" width="10%">
                状态
            </td>
            <td class="td_label" align="center" width="10%">
                修改
            </td>
            <td class="td_label" align="center" width="28%">
                批复意见
            </td>
        </tr>
    </table>
    <center>
        <div id="divTable"
             style="position: relative; align: center; top: 0px;width:  95%; height:  80%; z-index: 1; overflow: auto; overflow-x: hidden">
            <table cellpadding="1" cellspacing="1" width="100%" border="0" class="table_show">
                <logic:iterate id="onlineInfo" name="INFOLIST" type="com.boco.eoms.studyonline.model.OnlineInfo">
                    <tr class="tr_show" align="center">
                        <td width="6%">
                            <input type="checkbox" name="<%=onlineInfo.getImportId()%>">
                        </td>
                        <td width="6%">
                            <%=i++%>
                        </td>
                        <td width="17%">
                            <bean:write name="onlineInfo" property="importUser"/>
                        </td>
                        <td width="23%">
                            <%=StaticMethod.getTimestampString(onlineInfo.getImportTime())%>
                        </td>
                        <td width="10%">
                            <bean:write name="onlineInfo" property="statusName"/>
                        </td>
                        <%
                            String url = "reportDetail.do";
                            java.util.HashMap map = new java.util.HashMap();
                            map.put("importId", onlineInfo.getImportId());
                            pageContext.setAttribute("map", map);
                        %>
                        <td width="10%">
                            <html:link href="<%=url%>" name="map">
                                <img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0">
                            </html:link>
                        </td>
                        <td width="28%">
                            <bean:write name="onlineInfo" property="comment"/>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>
        <br>
        <table cellpadding="1" cellspacing="1" width="60%" border="0" class="table_show">
            <tr class="td_label" align="center">
                <td width="20%">
                    所属专业<font color="red">*</font>
                <td width="80%">
                    <html:select property="specialty" value="0" style="width:200" onclick="selectSpecialty()">
                        <html:options collection="Specialty" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr class="tr_show" align="center">
                <td colspan="2">
                    <html:submit value="报批" onclick="return reportDO()"/>
                    <html:submit value="删除" onclick="return del()"/>
                </td>
            </tr>
        </table>
    </center>
</html:form>
</body>
</html>
