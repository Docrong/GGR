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
    treeRootText:"${eoms:a2u('查询排班')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/duty/TawRmAssignwork/queryDuty.do?roomId=",text:"${eoms:a2u('排班查询')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('查询排班')}',cls:'new-mi',url:'${app}/duty/TawRmAssignwork/queryDuty.do?roomId='}
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
    <h1>${eoms:a2u("查询排班")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	   	<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('查询排班的主要功能是提供按时间段的查询和导出excel功能')}</dd>
	    <dt>${eoms:a2u('选择机房')}</dt>
		<dd>${eoms:a2u('列出来的机房都是管理员对用户配置的机房域，在选择的机房上点击右键，选择【查询排班】。或者【直接点击机房】')}</dd>
		<dt>${eoms:a2u('选择时间查询结果')}</dt>
		<dd>${eoms:a2u('在第二个页面内有时间的选择框，选择需要的时间段，点击【查询】，在结果集的页面内有排班情况的详细信息。如果需要打印当前页面的话点击【打印】，如果需要把当前页面导出成Excel文件点击【导出】并保存到本地')}</dd>	</dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
	<iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>

<%@ include file="/common/footer_eoms.jsp"%>