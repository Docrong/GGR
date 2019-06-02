<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/duty/TawRmRecordPer/getroom.do",
    treeRootId:'-1',
    treeRootText:"${eoms:a2u('所属机房')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/duty/tawRmReplace.do?method=xgetAudit&roomId=",text:"${eoms:a2u('显示待审核')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('显示待审核')}',cls:'new-mi',url:'${app}/duty/tawRmReplace.do?method=xgetAudit&roomId='} 
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
    <h1>${eoms:a2u("显示待审核")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	 	<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('替班管理是提供对班的自动化管理，它的主要流程是申请人提出替班（选择替班人），提交到管理员处由管理员审核，如果都同意则生效。')}</dd>
	    <dt>${eoms:a2u('选择机房：')}</dt>
		<dd>${eoms:a2u('列出来的机房都是管理员对用户配置的机房域。')}</dd>
		<dt>${eoms:a2u('申请 ：')}</dt>
		<dd>${eoms:a2u('选择机房域，点击右键，选择【申请替班】 进入选择时间页面，选择开始时间，和结束日期，选择替班人，点击申请。选择左边的值班班次，可以多个选择。在下面填写申请原因，提交。如果对方在这个时间内有值班计划则不能申请替班')}</dd>
		<dt>${eoms:a2u('审核 ：')}</dt>
		<dd>${eoms:a2u('选择机房域，点击右键，选择【显示待审核】 进入待审核页面。管理员进入则可以审核【同意】与【不同意】 当同意的时候本次申请成功，如果不同意本次申请失败。')}</dd>
		<dt>${eoms:a2u('替班记录 ：')}</dt>
		<dd>${eoms:a2u('选择机房域，点击右键，选择【替班记录】 进入替班记录页面。可以根据管理员的同意与否查看替班记录')}</dd>
		
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