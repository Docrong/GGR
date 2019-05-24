<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
 
<script type="text/javascript">
	//刷新父框架中的整颗树
	parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	//parent.AppFrameTree.reloadNode();
</script>
<div class="successPage">
	<h1 class="header">${eoms:a2u('操作失败！')}</h1>
	<div class="content">
	${eoms:a2u('您的操作排班已成功，但是短信发送失败！请联系管理员')}<br/>
 
	</div>
</div>
 
<%@ include file="/common/footer_eoms_innerpage.jsp"%>