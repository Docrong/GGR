<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/workplan/tawwpmonth/monthlistgetnodes.do",
    treeRootId:'-1',
    treeRootText:'网元类型',
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/workplan/tawwpmonth/monthlists.do?Mtype=",text:"本月计划列表"},
    //onClick:{url:"${app}/workplan/tawwpmonth/monthlists.do?yearid=2009&monthid=5&type=",text:"本月计划列表"},
	/*ctxMenu:[
		{id:'newnode', text:'新建网元计划',cls:'new-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=newFolder&folderPath='},
		{id:'edtnode', text:'',cls:'edit-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=editFolder&folderPath='},
		{id:'sharenode', text:'',cls:'edit-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=shareFolder&folderPath='},
		{id:'copynode', text:'复制上月计划',cls:'list-mi',url:'${app}/../tawwpmonth/netselect.do?type='},
		{id:'delnode', isDelete:true, text:'',cls:'remove-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=deleteFolder&folderPath='}
	],
	*/
	//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
  
  </script>
  <<script type="text/javascript">
<!--
  function onMonth()
  {
    window.location.href("${app}/workplan/tawwpmonth/monthlistgetnodes.do");
  }
//-->
</script>
  <style type="text/css">
  	body{background-image:none;}
  </style>
<div id="headerPanel" class="x-layout-inactive-content">
    <h1>月度作业计划管理</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dl>
		<dt>作业计划</dt>
		<dd>作业计划管理主要是面向作业计划的制定、发布与监控。由于这些流程中的许多部分是值班管理人员没有权限进行操作的，而且此项功能事关公司全局事务，因此，独立出来单独处理。另外，该项功能将在进一步的强化中与企业计划管理系统关联或重复，可以通过接口系统进行数据的交互与同步。事实上，该项功能实现之后，企业计划管理中与生产相关的部分功能可以直接通过该系统实现。</dd>
	    <dt>新建月度计划</dt>
		<dd>选择相应网元类型,选择下面的月度计划的模板,选择相应的月份,选择新建网元计划;出现新建页面,选择需要作业计划的月份、网元名称,保存后月度计划建好了！</dd>
		<dt>编辑月度计划</dt>
		<dd>选择责任人编辑,编辑选择作业计划类型、执行负责人,点击保存;点击"临时作业计划",增加额外的作业计划;选择执行人编辑,选择每项内容的执行人！</dd>
	</dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
	<iframe id="formPanel-page"  name="formPanel-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>
<%@ include file="/common/footer_eoms.jsp"%>