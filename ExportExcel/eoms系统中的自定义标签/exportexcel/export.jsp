<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
%>
<html>
<head>
    <title>
        ExportExcel
    </title>
    <script type="text/javascript">
        function excelFile(btn) {
            btn.disabled = 'false';
            var sql = "${param.sql}";
            //alert(sql);
            window.location.href = "http://localhost:8080/exportexcel/exportExcel?sql=" + encodeURI(sql);
        }
    </script>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" type="text/css" media="all" href="${app}/styles/default/theme.css"/>
    <script type="text/javascript" src="${app}/scripts/util/iframe.js"></script>
</head>
<body style="background-image:none">
<div class="upload-box">
    <table>
        <td>
            <input type="button" class="btn" value="Excel导出" name="button" onclick="excelFile(this);">
        </td>
    </table>
</div>
<script type="text/javascript" src="${app}/scripts/util/iframe.js"></script>
</body>
</html>
