<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
 <script type="text/javascript">
  var config = { 
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/myinfo/tawSystemMyinfo.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('个人信息管理')}",
	pageFrame:'formPanel-page',
	pageFrameId:'formPanel-page',
	ctxMenu:[  
		{id:'Child', text:'${eoms:a2u('角色查询')}',cls:'new-mi',url:'${app}/myinfo/tawSystemMyinfo.do?method=xgetRole&folderPath='},
		{id:'Edit', text:'${eoms:a2u('部门查询')}',cls:'list-mi',url:'${app}/myinfo/tawSystemMyinfo.do?method=xgetDept&folderPath='}
	    	
	],//end of ctxMenu 
	/************************s
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
	<h1>${eoms:a2u('个人信息管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('帮助')}</dt>
		<dd>${eoms:a2u('帮助')}</dd>
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