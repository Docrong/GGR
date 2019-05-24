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
	treeGetNodeUrl:"${app}/supplierkpi/editTawSupplierkpiTemplateAssess.do?method=getNodesUnchecked",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('专业评估未审核模型')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'edtnode', text:'${eoms:a2u('审核专业模型')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiTemplateAssess.do?specialType=" />'}
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
	<h1>${eoms:a2u('专业评估未审核模型')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('审核专业模型')}</dt>
		<dd>${eoms:a2u('step1.右键点击专业模型节点，选择"审核专业模型"，页面右侧会列出专业模型的信息以及下属的评估指标')}</dd>
		<dd>${eoms:a2u('step2.填写审核意见后点击"通过"按钮即完成对模型的审核')}</dd>
		<dt>${eoms:a2u('驳回专业模型')}</dt>
		<dd>${eoms:a2u('step1.右键点击专业模型节点，选择"审核专业模型"，页面右侧会列出专业模型的信息以及下属的评估指标')}</dd>
		<dd>${eoms:a2u('step2.填写审核意见并勾选可能不合格的评估指标，之后点击"驳回"按钮即完成对模型的驳回')}</dd>
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