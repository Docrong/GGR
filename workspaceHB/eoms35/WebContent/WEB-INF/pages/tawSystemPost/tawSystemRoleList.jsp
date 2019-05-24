<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
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
  <a href="../tawSystemRole/tawSystemRoles.do" target="center"><fmt:message key="tawSystemRoleList.list"/></a>
  <a href="../tawSystemRole/editTawSystemRole.do" target="center"><fmt:message key="tawSystemRoleList.addRole"/></a>
</div>

<div id="west">
<div id="tree" style="text-align:left;height:650px;width:300px;overflow:auto">
<script type="text/javascript">
	var tree = new WebFXLoadTree("role","${app}/xtree.do?method=roleTree&id=1");
	tree.write();
	tree.setTarget("center");
    tree.expand();
</script>
</div>
</div>

<iframe id="center" name="center" frameborder="no"></iframe>

<%@ include file="/common/footer_eoms.jsp"%>