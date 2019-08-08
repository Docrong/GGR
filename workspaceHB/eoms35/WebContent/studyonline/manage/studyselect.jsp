<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*" %>
<html:html>
    <head>
        <title>
            类型选择
        </title>
        <script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>
    <script language="javascript">
        function select() {
            var form = document.forms[0];
            var valueString = "";
            valueString = form.specialtySel[form.specialtySel.selectedIndex].text;
            valueString = valueString + ">" + form.manufacturerSel[form.manufacturerSel.selectedIndex].text;
            valueString = valueString + ">" + form.equipIdSel[form.equipIdSel.selectedIndex].text;
            valueString = valueString + ">" + form.rankSel[form.rankSel.selectedIndex].text;
            //alert(valueString);
            form.Value.value = valueString;
        }

        function doselect() {
            var form = document.forms[0];
            var flag = false;
            if (form.Value.value != null && form.Value.value != "") {
                for (var i = 0; i < form.studySel.options.length; i++) {
                    if (form.studySel.options[i].text == form.Value.value)
                        flag = true;
                }
                if (!flag) {
                    var text = new Option(form.Value.value);
                    var SelString = form.specialtySel.value
                        + ">" + form.manufacturerSel.value
                        + ">" + form.equipIdSel.value
                        + ">" + form.rankSel.value;
                    text.value = SelString;
                    form.studySel.options[form.studySel.options.length] = text;
                } else
                    alert("所选项重复");
            }
        }

        function deleteSel() {
            var form = document.forms[0];
            form.studySel.options[form.studySel.selectedIndex] = null;
        }

        function confirm(type) {
            var form = document.forms[0];
            if (form.studySel.options.length == 0) {
                alert("选择类型为空");
                return false;
            }
            //返回类型typeSel为 8>2>3>1;4>3>2>3 的形式
            for (var i = 0; i < form.studySel.options.length; i++) {
                if (i == 0) {
                    form.typeSel.value = form.studySel[i].value;
                    form.typeSelName.value = form.studySel[i].text;
                } else {
                    form.typeSel.value = form.typeSel.value + ";" + form.studySel[i].value;
                    form.typeSelName.value = form.typeSelName.value + ";" + form.studySel[i].text;
                }
            }
            //alert(form.typeSelName.value);

            if (type == 0)  //保存为默认
                form.action = "saveDefault.do";
            else if (type == 1) //出题
                form.action = "study.do";
            //return false;
        }

    </script>

    <eoms:DictType typeName="Specialty"/>
    <eoms:DictType typeName="Manufacturer"/>
    <eoms:DictType typeName="EquipId"/>

    <body>
    <html:form action="/study">
        <html:hidden property="typeSel"/>
        <html:hidden property="typeSelName"/>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td width="100%" align="center" class="table_title">
                    出题类型选择
                </td>
            </tr>
        </table>
        <table border="0" width="700" cellspacing="1" cellpadding="1" class="table_show" align="center">
            <tr class="tr_show">
                <td rowspan="3" align="center">
                    <p>专业</p>
                    <html:select size="5" property="specialtySel" style="width:150" value="1" onclick="select();">
                        <html:options collection="Specialty" property="value" labelProperty="label"/>
                    </html:select>
                    <p>设备</p>
                    <html:select size="5" property="manufacturerSel" style="width:150" value="6" onclick="select();">
                        <html:options collection="Manufacturer" property="value" labelProperty="label"/>
                    </html:select>
                    <p>厂家</p>
                    <html:select size="5" property="equipIdSel" style="width:150" value="1" onclick="select();">
                        <html:options collection="EquipId" property="value" labelProperty="label"/>
                    </html:select>
                    <p>级别</p>
                    <html:select size="4" property="rankSel" style="width:150" value="2" onclick="select();">
                        <html:options collection="RANK" property="value" labelProperty="label"/>
                    </html:select>
                </td>
                <td>
                    <p>所选类别:</p>
                    <html:text property="Value" readonly="true" style="width:400" value=""/>
                </td>
            </tr>
            <tr class="tr_show">
                <td>
                    <p>已选类别:</p>
                    <html:select size="20" property="studySel" style="width:400" value="0" ondblclick="deleteSel()">
                        <html:options collection="configLabel" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr class="tr_show">
                <td align="center">
                    <html:button value="选择" property="add" onclick="doselect()"/>
                    <html:button value="删除" property="delete" onclick="deleteSel()"/>
                </td>
            </tr>
            <tr class="tr_show" align="right">
                <td colspan="2">
                    <html:submit value="保存为默认" onclick="return confirm(0)"/>
                    <html:submit value="出题" onclick="return confirm(1)"/>
                </td>
            </tr>
        </table>


    </html:form>
    </body>
</html:html>
