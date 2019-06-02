<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/tree.js"></script>
<style>
div#north{
	padding:8px;
	border-bottom:1px solid #999;
	font-size:14px;
	text-align:left;
}
.x-layout-panel{
	border:0px none;
}
</style>
<div id="north">
  <content tag="heading"><fmt:message key="tawSystemDeptList.heading"/></content>
  <a href="tawSystemDepts.do" target="center"><fmt:message key="tawSystemDeptList.list"/></a>
  <a href="editTawSystemDept.do" target="center"><fmt:message key="tawSystemDeptList.addDept"/></a>
</div>

<div id="west">
<div id="tree" style="text-align:left">
<script type="text/javascript">
	var tree = new WebFXLoadTree("dept","${app}/xtree.do?method=dept&id=-1");
	tree.write();
	tree.setTarget("center");
    tree.expand();
</script>
</div>
</div>

<iframe id="center" name="center" frameborder="no" src="tawSystemDepts.do"></iframe>

<%@ include file="/common/footer_eoms.jsp"%>
