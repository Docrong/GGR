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
	treeGetNodeUrl:"${app}/supplierkpi/editTawSupplierkpiTemplateAssess.do?method=getNodesChecked",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('供应商评估模型')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'edtnode', text:'${eoms:a2u('批量定制评估指标')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiAssessInstance.do?specialType=" />'},
		{id:'View', text:'${eoms:a2u('已定制供应商')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiAssessInstance.do?method=viewCustomSuppliers&specialType=" />'}
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
	<h1>${eoms:a2u('供应商评估模型管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('批量定制评估指标')}</dt>
		<dd>${eoms:a2u('step1.右键点击树图中的专业模型节点，选择"批量定制评估指标"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中勾选供应商和需要定制的评估指标并点击"保存"按钮')}</dd>
		<dd>${eoms:a2u('注意：此操作会覆盖原有的定制关系')}</dd>
		<dt>${eoms:a2u('查看已定制该专业指标的供应商')}</dt>
		<dd>${eoms:a2u('step1.右键点击树图中的专业模型节点，选择"已定制供应商"，右侧页面中列出已定制该专业指标的供应商列表')}</dd>
		<dd>${eoms:a2u('step2.点击列表中记录后面的"查看"链接可以查看某一供应商定制该专业指标的情况（打勾的为已定制）')}</dd>
		<dd>${eoms:a2u('step3.可以重新勾选指标以修改当前的定制关系，之后点击"保存"按钮')}</dd>
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