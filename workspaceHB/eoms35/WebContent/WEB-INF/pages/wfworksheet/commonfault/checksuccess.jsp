<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<html>
<script type="text/javascript">
	function intoOwner(){
	 	location.href = "${app}/sheet/commonfault/commonfault.do?method=showCheckList";
	}
	window.setTimeout(intoOwner, 3000);
</script>
<style type="text/css">
.successPage span.data{
	color:#1465B7;
	margin-left:65px;
}
</style>
<body>
<div class="successPage">
	<h1 class="header">工单数据已经成功提交!</h1>
</div>
</body>
</html>
<%@ include file="/common/footer_eoms.jsp"%>