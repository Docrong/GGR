<%@ page language="java" import="com.boco.eoms.base.util.ApplicationContextHolder,com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/supplierkpi/tawSupplierkpiInfos.do?method=getNodes",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('供应商')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'newnode', text:'${eoms:a2u('新增')}',cls:'new-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiInfo.do?name=" />'},
		{id:'edtnode', text:'${eoms:a2u('修改')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiInfo.do?id=" />'},
		{id:'view', text:'${eoms:a2u('查看')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/viewTawSupplierkpiInfo.do?method=view&id=" />'},
		{id:'delnode', isDelete:true, text:'${eoms:a2u('删除')}',cls:'remove-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiInfo.do?method=delete&id=" />'},
		{id:'kpi', text:'${eoms:a2u('已定制评估指标')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiInfo.do?method=supplierkpiList&id=" />'}
	],//end of ctxMenu
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
	<h1>${eoms:a2u('供应商基本信息管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('添加一个供应商')}</dt>
		<dd>${eoms:a2u('step1.在树图中"供应商"节点上点击右键，选择"新建供应商"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中填写相关信息后点击"保存"按钮')}</dd>
		<dt>${eoms:a2u('修改一个供应商的信息')}</dt>
		<dd>${eoms:a2u('step1.在树图中某一个供应商节点上点击右键，选择"修改"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中修改相关信息后点击"保存"按钮')}</dd>
		<dt>${eoms:a2u('删除供应商')}</dt>
		<dd>${eoms:a2u('在树图中的供应商节点上点击右键，并选择"删除"')}</dd>
		<dt>${eoms:a2u('查看一个供应商的详细信息')}</dt>
		<dd>${eoms:a2u('在树图中"供应商"节点上点击右键，选择"查看详细信息"，右侧页面即可呈现该供应商的详细信息')}</dd>
		<dt>${eoms:a2u('查看已定制的评估指标')}</dt>
		<dd>${eoms:a2u('在树图中的供应商上点击右键，选择"已定制的评估指标"，右侧页面即可呈现已定制的指标')}</dd>
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