<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/kmmanager/KmrecordPer/getroom.do",
    treeRootId:'-1',
    treeRootText:"${eoms:a2u('值班小组')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/kmmanager/Kmchangeduty/query_audit.do?roomId=",text:"${eoms:a2u('换班审核')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('换班审核')}',cls:'new-mi',url:'${app}/kmmanager/Kmchangeduty/query_audit.do?roomId='}
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
    <h1>${eoms:a2u("换班审核")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('换班审核就是提供管理员对已经提交到管理员处的换班请求进行审核')}</dd>
	    <dt>${eoms:a2u('选择值班小组')}</dt>
		<dd>${eoms:a2u('列出来的值班小组都是管理员对用户配置的值班小组域，在选择的值班小组上点击右键，选择【排版管理】。或者【直接点击值班小组】')}</dd>
		<dt>${eoms:a2u('审核')}</dt>
		<dd>${eoms:a2u(' ')}</dd>
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