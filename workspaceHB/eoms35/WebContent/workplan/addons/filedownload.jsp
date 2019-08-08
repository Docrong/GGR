<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>附件管理</title>
    <link rel="stylesheet" href="../css/table_style.css" type="text/css">
    <script language="JavaScript">
        function WinClose() {
            window.close();
        }
    </script>
</head>
<body>
<%
    String downloadFileUrl = (String) request.getParameter("url");
%>
<base>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
        <td class="table_title" width="80%" valign="middle">
            下载导出EXCEL表格
        </td>
    </tr>
</table>
<table align=center border=0 cellspacing="1" cellpadding="1" class="table_show">
    <tr class="tr_show">
        <td width="30%" class="td_label">
            <a target="_blank\" href="../../../EOMS_J2EE<%=downloadFileUrl%>">下载</a>
        </td>
    </tr>
    <tr class="tr_show">
        <td colspan="2" align=center>
            <input type="button" value="返回" onclick="javascript:window.history.back();" class="clsbtn2">
        </td>
    </tr>

</table>

</BODY>
</HTML>
