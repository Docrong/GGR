<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script type="text/javascript">
	Try.these(
		function(){
			parent.frames['portal-south'].location.reload();
		}
	);
</script>

<div class="successPage">
	<h1 class="header">${eoms:a2u('操作成功')}</h1>
	<div class="content"></div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>