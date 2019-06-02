<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
	try{
	  //刷新父框架中的整颗树
	  parent.AppFrameTree.refreshTree();
	  //刷新父框架中当前选中的节点
	  //parent.AppFrameTree.reloadNode();
	}catch(e){}
</script>
<div class="failurePage">
	<h1 class="header">${eoms:a2u('操作失败！')}</h1>
	<div class="content">
		${eoms:a2u('您的操作未能成功执行!')}<br/><%=request.getAttribute("falseMessage") %>
	</div>
</div>
<%@ include file="/common/header_eoms_innerpage.jsp"%>