<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<div class="sheet-success">
	<div class="header"><h1>${eoms:a2u('操作成功！')}</h1></div>
	<div class="content">
	<font size="4">${eoms:a2u('报表指标追加成功，并已生成相应报表!')}</font>
	</div>
	<div class="content">
	<font size="4">${eoms:a2u('可点击菜单树上的"评估报管理--->评估报表查询"查看具体内容!')}</font>
	</div>
	<div class="content">
	<font size="4">${eoms:a2u('或者点击菜单树上的"评估报管理--->厂商单体报表查询"查看具体内容!')}</font>
	</div>	
</div>
<%@ include file="/common/footer_eoms.jsp"%>