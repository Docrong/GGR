<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script type="text/javascript">
	Try.these(
		function(){
			parent.frames['portal-south'].location.reload();
		}
	);
	var noTemplateManage = "${noTemplateManage}";
	if (noTemplateManage != "") {
		alert("<bean:message bundle="sheet" key="template.save.success"/>");
	}
</script>

<div class="successPage">
	<h1 class="header">${eoms:a2u('操作成功！')}</h1>
	<div class="content">
		<ul>
		<logic:present name="addUrl">
			<li><a href="${addUrl}"><bean:message bundle="sheet" key="worksheet.success.backAdd" /></a></li>
		</logic:present>
		
		<logic:present name="listUrl">
		<li><a href="${listUrl}"><bean:message bundle="sheet" key="worksheet.success.backList" /></a></li>
		</logic:present>
		
		<logic:present name="dealOperateListUrl">
		<li><a href="${dealOperateListUrl}"><bean:message bundle="sheet" key="worksheet.success.backDealOperateList" /></a></li>
		</logic:present>
		
		<logic:present name="dealTemplateListUrl">
		<li><a href="${dealTemplateListUrl}"><bean:message bundle="sheet" key="worksheet.success.backDealTemplateList" /></a></li>
		</logic:present>
		</ul>
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>