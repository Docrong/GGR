<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<div class="failurePage">
	<h1 class="header">${eoms:a2u('操作未完成！')}</h1>
	<div class="content">
		${eoms:a2u('您本月评估报表已经全部生成,本次操作未生成新的数据,若有问题,请先检查定制的报表的生效时间是否合理,以及选择的指标是否已经填写!')}
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>