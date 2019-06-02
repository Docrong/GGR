<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<div class="failurePage">
	<h1 class="header">${eoms:a2u('操作未完成！')}</h1>
	<div class="content">
		${eoms:a2u('您本月评估填写实例已经全部生成,本次操作未生成新的实例!')}
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>