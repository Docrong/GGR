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
	treeGetNodeUrl:"${app}/supplierkpi/tawSuppKpiInstReOrderQuery.do?method=getNodes",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('报表查询管理')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'edtnode', text:'${eoms:a2u('横向报表')}',cls:'new-mi',url:'<c:url value="tawSuppKpiInstReOrderQueryAction.do?dictId=" />'},
		{id:'View', text:'${eoms:a2u('纵向报表')}',cls:'new-mi',url:'<c:url value="tawSuppKpiInstReOrderQueryAction.do?method=search1&dictId=" />'}
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
	<h1>${eoms:a2u('评估报表模型查询')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('统计横向报表')}</dt>
		<dd>${eoms:a2u('step1.展开树图，在需要统计报表的专业节点上点击右键，选择"横向报表"')}</dd>
		<dd>${eoms:a2u('step2.选择报表名称和评估时间点，点击"查询"按钮')}</dd>
		<dd>${eoms:a2u('step3.在查询结果每一行数据的右侧点击"柱图"或"饼图"可以查看该统计结果的图形化展示')}</dd>
		<dt>${eoms:a2u('统计纵向报表')}</dt>
		<dd>${eoms:a2u('step1.展开树图，在需要统计报表的专业节点上点击右键，选择"纵向报表"')}</dd>
		<dd>${eoms:a2u('step2.选择各项查询条件，点击"查询"按钮')}</dd>
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
