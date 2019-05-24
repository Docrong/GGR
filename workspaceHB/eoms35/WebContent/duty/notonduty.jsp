

<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
	//刷新父框架中的整颗树
	parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	//parent.AppFrameTree.reloadNode();
</script>

<div class="failurePage">
	<h1 class="header">${eoms:a2u('不能操作此任务！')}</h1>
	<div class="content">
	<strong>${eoms:a2u('对不起,您现在不是值班状态,不能操作此任务')}</strong>
	<input type = "button" value='${eoms:a2u('返回')}' class="button"  onclick="javascript:history.back();">
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>