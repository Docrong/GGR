<%@ page language="java" import="com.boco.eoms.base.util.ApplicationContextHolder,com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import ="java.util.ArrayList"%>
<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/supplierkpi/tawSupplierkpiInstanceTypes.do?method=getNodes",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('供应商管理实例填写')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'Extra1', text:'${eoms:a2u('未填写')}',cls:'new-mi',url:'<c:url value="tawSupplierkpiInstances.do?id=" />'},
		{id:'Extra2', text:'${eoms:a2u('已填写')}',cls:'new-mi',url:'<c:url value="tawSupplierkpiInstances.do?method=searchView&id=" />'}
	],	
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
	<h1>${eoms:a2u('填写评估结果')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('填写评估数据')}</dt>
		<dd>${eoms:a2u('step1.在树图中专业节点上点击右键，选择"未填写"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面填写评估数据和备注（可批量填写），之后点击"保存"按钮')}</dd>
		<dt>${eoms:a2u('查看已填写数据')}</dt>
		<dd>${eoms:a2u('step1.在树图中专业节点上点击右键，选择"已填写"，右侧页面会列出已填写数据列表')}</dd>
		<dd>${eoms:a2u('step2.选择"年份"和"月份"可以查看已填写的历史数据')}</dd>
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
