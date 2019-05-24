<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/  
	treeGetNodeUrl:"${app}/interfaceMonitoring/interfaceMonitoringLog.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('所有接口')}",
	pageFrame:'formPanel-page',
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('新建接口组')}',cls:'new-mi',url:'<c:url value="/interfaceMonitoring/interfaceMonitoringLog.do?method=saveToPage&nodeid=" />',rootCanShow:true},
		{id:'Edit', text:'${eoms:a2u('修改分组')}',cls:'edit-mi',url:'<c:url value="/interfaceMonitoring/interfaceMonitoringLog.do?method=xgetInterfaceConfigurationGroup&nodeid=" />'},
		{id:'Childs', text:'${eoms:a2u('新增接口')}',cls:'new-mi',url:'<c:url value="/interfaceMonitoring/interfaceMonitoringLog.do?method=savePage&nodeid=" />',rootCanShow:true},
		{id:'Edits', text:'${eoms:a2u('修改接口')}',cls:'edit-mi',url:'<c:url value="/interfaceMonitoring/interfaceMonitoringLog.do?method=xget&nodeid=" />'},
		{id:'Lists', text:'${eoms:a2u('接口详细')}',cls:'list-mi',url:'<c:url value="/interfaceMonitoring/interfaceMonitoringLog.do?method=xlist&nodeid=" />'},
		{id:'Delete', isDelete:true, text:'${eoms:a2u('删除')}',cls:'remove-mi',url:'<c:url value="/interfaceMonitoring/interfaceMonitoringLog.do?method=xdelete&nodeid=" />'}
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
	<h1>${eoms:a2u('接口服务监控')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('接口监控有助于用户对所使用接口进行全面监控，能够看到所配置的WebService服务启动是否正常。如果启动正常，接口图标为绿色对勾，否则为红色叹号。')}</dd>
		<dd>${eoms:a2u('验证接口是否正常也可以通过在浏览器中输入接口地址查看，如果WebService服务启动正常，页面信息为：')}</dd>
		<dd>${eoms:a2u('HandleredService')}</dd>
		<dd>${eoms:a2u('Hi there, this is an AXIS service!')}</dd>
		<dd>${eoms:a2u('Perhaps there will be a form for invoking the service here... ')}</dd>
	</dl>
	<br/>
	<dl>
	    <dt>${eoms:a2u('添加接口组')}</dt>
		<dd>${eoms:a2u('为接口进行归类，便于管理众多添加的接口。')}</dd>
		<dt>${eoms:a2u('新增接口')}</dt>
		<dd>${eoms:a2u('新增加WebService服务监控接口。将接口的提供方、操作名、详细信息填写完毕，进行【保存】。')}</dd>
		<dt>${eoms:a2u('删除接口')}</dt>
		<dd>${eoms:a2u('删除失效的监控接口。')}</dd>
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