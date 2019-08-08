<%@ page contentType="text/html; charset=GB18030" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<html>
<head>
    <title>
        试题导入
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script language="JavaScript">
        function gohead() {
            history.go(-1);
        }

        function addattach(flag) {
            if (document.all.ImportWordForm.attachName.value == "")
                return false;
            else {
                var fileStr = document.all.ImportWordForm.attachName.value;
                var index = fileStr.lastIndexOf("\\");
                fileStr = fileStr.substring(index + 1, fileStr.length);
                document.all.ImportWordForm.fileName.value = fileStr;
                document.all.ImportWordForm.submit();
                return true;

            }
        }
    </script>
</head>
<body bgcolor="#ffffff">
<eoms:DictType typeName="Specialty"/>
<eoms:DictType typeName="Manufacturer"/>
<eoms:DictType typeName="EquipId"/>
<FORM name="ImportWordForm" METHOD="POST" ACTION="manage/file_word_up.jsp" ENCTYPE="multipart/form-data">
    <input type="hidden" name="fileName" value="">
    <center>
        <table cellSpacing="0" cellPadding="0" width="85%" border="0">
            <tr>
                <td class="table_title" align="center"><b>试题导入</b></TD>
            </tr>
        </table>
        <br>
        <br>
        <br>
        <table border="0" width="70%" cellspacing="1" class="table_show">
            <tr class="tr_show">
                <td>
                    专业
                </td>
                <td>
                    <html:select property="specialtySel" value="1">
                        <html:options collection="Specialty" property="value" labelProperty="label"/>
                    </html:select>
                </td>
                <td>
                    设备
                </td>
                <td>
                    <html:select property="manufacturerSel" value="1">
                        <html:options collection="Manufacturer" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr class="tr_show">
                <td>
                    厂家
                </td>
                <td>
                    <html:select property="equipIdSel" value="1">
                        <html:options collection="EquipId" property="value" labelProperty="label"/>
                    </html:select>

                </td>
                <td>
                    发布类型
                </td>
                <td>
                    <select name="issueType">
                        <option value="1">学习库</option>
                        <option value="2" selected> 试题库</option>
                    </select>
                </td>
            </tr>
            <tr class="tr_show">
                <td>
                    分数
                </td>
                <td>
                    <select name="s_value">
                        <option value="1">1分</option>
                        <option value="2">2分</option>
                        <option value="3">3分</option>
                        <option value="5">5分</option>
                        <option value="10">10分</option>
                    </select>
                </td>
                <td>

                </td>
                <td>

                </td>
            </tr>
        </table>
        <table border="0" width="70%" cellspacing="1" class="table_show">
            <tr class="tr_show" align="center">
                <td class="clsfth" width="25%" align="center">
                    <B>附件</B>
                </td>
                <td>
                    <input type="file" name="attachName" class="clstext">
                    <INPUT TYPE="button" VALUE="导入" onclick="return addattach(1);">
                    <input name="reset" type="button" id="reset" value="返回" onClick="javascript:gohead()">
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>
