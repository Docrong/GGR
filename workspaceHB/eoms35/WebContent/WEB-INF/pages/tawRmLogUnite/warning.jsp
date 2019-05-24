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
	<h1 class="header">${eoms:a2u('日志合并失败')}</h1>
	<div class="content">
		${eoms:a2u('您没有可合并的日志')}<br/>
	</div>
</div>
<%@ include file="/common/header_eoms_innerpage.jsp"%>