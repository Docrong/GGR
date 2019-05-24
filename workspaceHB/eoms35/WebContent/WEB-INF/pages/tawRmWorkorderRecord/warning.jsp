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
	<div class="header"><h1>${eoms:a2u('无法新增')}</h1></div>
	<div class="content">
	${eoms:a2u('您没有工单记录可以新增')}<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>