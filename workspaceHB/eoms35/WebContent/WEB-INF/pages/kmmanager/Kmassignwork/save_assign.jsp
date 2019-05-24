<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
	parent.AppFrameTree.refreshTree();
</script>
<div class="successPage">
	<h1 class="header">${eoms:a2u('操作成功！')}</h1>
	<div class="content">
	<%=request.getAttribute("ROOMNAME") %>

&nbsp;&nbsp;
	${eoms:a2u('排班已成功执行！')}<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>
