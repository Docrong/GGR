<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>


<div class=failurePage>
	<h1 class="header">${eoms:a2u('操作失败！')}</h1>
	<div class="content">
		${eoms:a2u('您本专业的评估填写实例生成失败,请与系统管理员联系!')}
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>