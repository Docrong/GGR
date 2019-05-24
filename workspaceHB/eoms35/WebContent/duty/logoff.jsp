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

<div class="successPage">
	<h1 class="header">${eoms:a2u('操作成功！')}</h1>
	<div class="content">
	${eoms:a2u('您的操作已成功执行。并且您已经退出值班')}<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>
