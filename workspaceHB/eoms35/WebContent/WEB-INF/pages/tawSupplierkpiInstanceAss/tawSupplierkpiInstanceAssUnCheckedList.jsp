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
	treeGetNodeUrl:"${app}/supplierkpi/editTawSupplierkpiInstanceAss.do?method=getNodesUnchecked",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('未审核评估实例')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'edtnode', text:'${eoms:a2u('审核填写实例')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiInstanceAss.do?specialType=" />'}
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
	<h1>${eoms:a2u('未审核填写实例')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('审核填写数据')}</dt>
		<dd>${eoms:a2u('step1.右键点击专业节点，选择"审核填写实例"，页面右侧会列出已填写数据')}</dd>
		<dd>${eoms:a2u('step2.点击数据值，可以打开数据查看页面')}</dd>
		<dd>${eoms:a2u('step3.填写审核意见并选择满意度后，点击"通过"按钮完成对填写数据的审核')}</dd>
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