<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
${param.name}
<script type="text/javascript">
	//刷新父框架中的整颗树
	//parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	parent.AppFrameTree.reloadNode();
</script>
<div class="successPage">
	<h1 class="header">${eoms:a2u('操作完成！')}</h1>
	<div class="content">
		${eoms:a2u('您本专业的KPI指标已经全部填写完成,如有下一专业,请填写下一专业！')}
	</div>
	<div class="content">
		${eoms:a2u('或者在菜单树上点击右键,点击【已填写】可查看填写完毕的内容！')}
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>