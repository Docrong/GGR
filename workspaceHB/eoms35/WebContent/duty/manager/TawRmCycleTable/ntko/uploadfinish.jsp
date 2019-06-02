<%@ include file="../../../../common/taglibs.jsp"%>
<%@ include file="../../../../common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
	//刷新父框架中的整颗树
	//parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	//parent.AppFrameTree.reloadNode();
</script>

<div class="successPage">
	<h1 class="header">${eoms:a2u('操作成功！')}</h1>
	<div class="content">
	<strong>${eoms:a2u('您的操作已成功执行！')}</strong>
	<input type="button" name="button" class="button" value='${eoms:a2u('返回值班记录')}' Onclick="window.location.href ='${app}/duty/TawRmRecord/record.do'">
	<input type = "button" value='${eoms:a2u('返回上一层')}' class="button"  onclick="javascript:history.back();">
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>