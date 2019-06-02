<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/supplierkpi/editTawSupplierkpiAssessInstance.do?method=getNodesUnchecked",
    treeRootId:'104',
	treeRootText:"${eoms:a2u('未审核实例')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"<c:url value="/supplierkpi/editTawSupplierkpiTemplateAssess.do?specialType=" />",text:"${eoms:a2u('审核')}"},
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
  </script>
  <style type="text/css">
  	body{background-image:none;}
  </style>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1>${eoms:a2u('供应商KPI管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl><!--
		<dt>${eoms:a2u('添加一个下级角色')}</dt>
		<dd>${eoms:a2u('在树图中的角色上点击右键，并选择"新建子节点"')}</dd>
		<dt>${eoms:a2u('修改一个角色的信息')}</dt>
		<dd>${eoms:a2u('在树图中的角色上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除角色')}</dt>
		<dd>${eoms:a2u('在树图中的角色上点击右键，并选择"删除"')}</dd>
		<dt>${eoms:a2u('查看角色的子角色')}</dt>
		<dd>${eoms:a2u('在树图中的角色上点击右键，并选择"子角色列表"')}</dd>
		<dt>${eoms:a2u('批量添加子角色')}</dt>
		<dd>${eoms:a2u('在树图中的角色上点击右键，并选择"添加子角色"')}</dd>
		-->
	</dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
	<iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>

<%@ include file="/common/footer_eoms.jsp"%>