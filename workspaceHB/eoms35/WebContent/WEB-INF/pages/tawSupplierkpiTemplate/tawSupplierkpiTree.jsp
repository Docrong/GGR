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
	treeGetNodeUrl:"${app}/supplierkpi/tawSupplierkpiTemplates.do?method=getAllNodes",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('专业评估指标')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'NewItem', text:'${eoms:a2u('新建评估指标项')}',cls:'new-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiItem.do?dictId=" />'},
		{id:'edtnode', text:'${eoms:a2u('修改评估指标项')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiItem.do?method=update&dictId=" />'},
		{id:'View', text:'${eoms:a2u('查看评估指标项')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiItem.do?method=view&dictId=" />'},
		{id:'NewItemType', text:'${eoms:a2u('新建项目类型')}',cls:'new-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiTemplate.do?method=addItemType&parentDictId=" />'},
		{id:'EditItemType', text:'${eoms:a2u('修改项目类型')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiItemType.do?parentDictId=" />'},
		{id:'delnode', isDelete:true, text:'${eoms:a2u('删除')}',cls:'remove-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiItem.do?method=delete&id=" />'}
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
	<h1>${eoms:a2u('评估指标管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('查看树图节点类型')}</dt>
		<dd>${eoms:a2u('将鼠标悬浮在树图的节点上，可以看到节点的类型，共有4种:服务、专业、项目分类、评估指标')}</dd>
		<dt>${eoms:a2u('新建项目类型')}</dt>
		<dd>${eoms:a2u('step1.右键点击树图中的专业节点或项目类型节点，选择"新建项目类型"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中填写项目类型的名称和备注，之后点击"保存"按钮')}</dd>
		<dt>${eoms:a2u('新建评估指标项')}</dt>
		<dd>${eoms:a2u('step1.右键点击树图中的项目分类节点，选择"新建评估指标项"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中填写新指标的相关信息，之后点击"保存"按钮')}</dd>
		<dd>${eoms:a2u('注意：新建评估指标会导致所属专业模型的审核状态重置为"未送审"状态')}</dd>
		<dt>${eoms:a2u('修改评估指标项')}</dt>
		<dd>${eoms:a2u('step1.右键点击树图中的评估指标节点,选择"修改评估指标项"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中修改指标的相关信息，之后点击"保存"按钮')}</dd>
		<dt>${eoms:a2u('删除评估指标项或项目分类')}</dt>
		<dd>${eoms:a2u('右键点击树图中的评估指标节点或项目分类节点，选择"删除"')}</dd>
		<dd>${eoms:a2u('注意：删除评估指标会导致所属专业模型的审核状态重置')}</dd>
		<dt>${eoms:a2u('查看评估指标项详细信息')}</dt>
		<dd>${eoms:a2u('右键点击树图中的评估指标节点，选择"查看评估指标"')}</dd>
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