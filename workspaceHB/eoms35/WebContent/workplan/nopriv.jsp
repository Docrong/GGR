<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
${param.name}
<script type="text/javascript">
	//刷新父框架中的整颗树
	parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	//parent.AppFrameTree.reloadNode();
</script>

<div class="sheet-success">
	<div class="header"><h1>您无权使用此功能！</h1></div>
	<div class="content">
	您的操作已成功执行。<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>