<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<input type="hidden" value="" id="overtimeList" name="overtimeList" />
<form action="" id="fr" name="fr" method="post">
	<content tag="heading">
	<center>
		<font size="4"><b>超时查询列表</b>
		</font>
	</center>
	</content>
	<display:table name="overtimeList" cellspacing="0" cellpadding="0"
		id="overtimeList" class="table overtimeList" pagesize="10"
		sort="external"
		requestURI="${app}/filemanager/OvertimeAction.do?act=overtimeList"
		size="resultSize">
		<logic:present name="overtimeList" property="flowId">
			<display:column property="acceptDeptName" title="公司名称" />
			<display:column property="reportName" title="任务名称" />
			<display:column property="uploadTime" title="上传时间" />
			<display:column property="status" title="上传状态" />
			<display:column property="overtime" title="是否超时" />
		</logic:present>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>