<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
 <script type="text/javascript">
  var config = { 
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/workbench/memo/tawWorkbenchMemoMain.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('便签管理')}",
	pageFrame:'formPanel-page',
	pageFrameId:'formPanel-page',
	ctxMenu:[  
		{id:'Child', text:'${eoms:a2u('新增便签')}',cls:'new-mi',url:'${app}/workbench/memo/editTawWorkbenchMemo.do?folderPath='},
		{id:'Edit', text:'${eoms:a2u('便签列表')}',cls:'list-mi',url:'${app}/workbench/memo/tawWorkbenchMemo.do?method=search&folderPath='},
	 	{id:'Delete', text:'${eoms:a2u('新增发送')}',cls:'new-mi',url:'${app}/workbench/memo/editTawWorkbenchMemoSendLog.do?folderPath='},
	 	{id:'List', text:'${eoms:a2u('便签查询')}',cls:'list-mi',url:'${app}/workbench/memo/tawWorkbenchMemoNodes.do?method=memoSearch&folderPath='},
	 	{id:'Share', text:'${eoms:a2u('查询')}',cls:'list-mi',url:'${app}/workbench/memo/tawWorkbenchMemoNodes.do?method=xSearch&folderPath='}
	 	
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
	<h1>${eoms:a2u('便签管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('功能说明')}</dt>
	    <dd>${eoms:a2u('【我的便签】的主要功能是方便信息的存储和流通，当有个新的信息的时候我们可以已便签的形式保存或者按照系统提供的发送方式发送给其他人.保存的信息或者已经发送的信息也同时可以发送给其他的人。')}</dd>
	    <dd>${eoms:a2u('【便签查询】是我记录的一些便签，包括未发送的和已发送的')}</dd>
	    <dd>${eoms:a2u('【我收到的便签】是别人发送给我的便签')}</dd>
	    <dd>${eoms:a2u('【发送记录】是我发送给别人便签的记录信息')}</dd>
	    <dt>${eoms:a2u('我的便签')}</dt>
		<dd>${eoms:a2u('点击【我的便签】的那个加号。会出现四个象限，每个象限表示不同的级别程度。在每个象限的上面点击【右键】，其中【新增便签】表示的是新增一个便签。但是不能发送给其他人员看，【便签列表】是查看这个象限的所有你新增便签。【新增发送】和【新增便签】的区别是可以选择人员和发送方式发送给你想发送给的人')}</dd>
		<dt>${eoms:a2u('便签查询')}</dt>
		<dd>${eoms:a2u('在【便签查询】上面点击右键【便签查询】，输入你要查询的条件，点击【查询】就是查询你增加的便签的一些信息')}</dd>
		<dt>${eoms:a2u('我接收到的便签')}</dt>
		<dd>${eoms:a2u('在【我接收到的便签】上面点击右键【查询】，输入你要查询的条件，点击【查询】就是查询我接收到的便签的一些信息')}</dd>
		<dt>${eoms:a2u('发送记录')}</dt>
		<dd>${eoms:a2u('在【发送记录】上面点击右键【查询】，输入你要查询的条件，点击【查询】就是查询我发送便签的一些信息')}</dd>
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