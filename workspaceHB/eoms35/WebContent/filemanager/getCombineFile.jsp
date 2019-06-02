<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%
String combineFile = request.getContextPath() + request.getParameter("filePath");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<div class="list-title">
生成汇总文件下载
</div>
<table width="50%" align="center" class="listTable">
<thead>
	<tr class="label" width="30%">
		<td>
			汇总数据表
		</td>
	</tr>
</thead>
<tbody>
	<tr>
		<td>
			<a href=<%=combineFile %> >
			<u>combineFile.xls</u>
			</a>
		</td>
	</tr>
	<tr>
		<td>
			<%--<input type="button" value="下载文件" class="button" onClick="javascript:window.open('<%=request.getContextPath() %>/filemanager/fileUpload/download.jsp?url=<%=combineFile %>&showName=loaddown.xls');" />--%>
			<input type="button" value="返回" onClick="javascript:window.history.back();" class="button"/>
		</td>
	</tr>
</tbody>
</table>
<%@ include file="/common/footer_eoms.jsp"%>