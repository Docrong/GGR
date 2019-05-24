<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
${param.name}
<script type="text/javascript">
	//??????????
	parent.AppFrameTree.refreshTree();
	//?????????????
	//parent.AppFrameTree.reloadNode();
</script>

<div class="sheet-success">
	<div class="header"><h1>${eoms:a2u('保存工单日志')}</h1></div>
	<div class="content">
	${eoms:a2u('日志保存成功')}<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>