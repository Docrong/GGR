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
	treeGetNodeUrl:"${app}/supplierkpi/tawSupplierkpiTemplates.do?method=getNodes",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('服务专业')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'ViewSpecial', text:'${eoms:a2u('填写数据监控')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiInstanceAss.do?method=monitorForm&specialType=" />'}
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
	<h1>${eoms:a2u('填写实例执行监控')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('填写数据监控')}</dt>
		<dd>${eoms:a2u('step1.右键点击树图中的专业节点，选择"填写数据监控"，右侧页面列出已填写数据')}</dd>
		<dd>${eoms:a2u('step2.若该专业的数据尚未送审，则可以点击数据值进入数据修改页面进行修改')}</dd>
		<dd>${eoms:a2u('step3.确认数据无误后点击"送审"与"归档"按钮将填写的数据送审或直接归档')}</dd>
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