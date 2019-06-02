<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<input type="hidden" value="" id="auditInfoList" name="auditInfoList" />
<form action="" id="fr" name="fr" method="post">
	<content tag="heading">
	<center>
		<font size="2"><b>历史审核信息详细列表</b>
		</font>
	</center>
	</content>
	<display:table name="auditInfoList" cellspacing="0" cellpadding="0"
		id="auditInfoList" class="table auditInfoList" pagesize="10"
		sort="external"
		requestURI="${app}/filemanager/ReportMgrAction.do?act=auditList"
		size="resultSize">
		<logic:present name="auditInfoList" property="auditId">
			<display:column property="reportName" title="任务名" />
			<display:column property="status" title="报表状态" />
			<display:column property="auditUserName" title="操作人" />
			<display:column property="auditTime" title="操作时间" />
			<display:column property="auditInfo" title="回复信息" />
		</logic:present>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>