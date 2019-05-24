<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
<script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/eva/evaTemplates.do?method=getDistributeNodes",
    treeRootId:'<%=EvaConstants.TREE_ROOT_ID%>',
	treeRootText:'待处理任务',
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'GenInstance', text:'生成考核任务',cls:'new-mi',url:'${app}/eva/evaKpiInstances.do?method=genInstancePage&nodeId='},
		{id:'FillInstance', text:'填写考核任务',cls:'edit-mi',url:'${app}/eva/evaKpiInstances.do?method=fillInstancePage&nodeId='},
		{id:'SendToAudit', text:'考核任务送审',cls:'edit-mi',url:'${app}/eva/evaKpiInstances.do?method=sendToAuditPage&nodeId='}
	],//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
</script>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1>待处理任务</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt></dt>
		<dd></dd>
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