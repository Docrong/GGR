<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/workplan/tawwpmodel/modellistgetnodes.do",
    treeRootId:'-1',
    treeRootText:"作业计划模板列表",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/workplan/tawwpmodel/modellists.do?Mtype=",text:"详细列表"},
	ctxMenu:[
		{id:'newnode', text:'新增模板',cls:'new-mi',url:'${app}/workplan/tawwpmodel/modeladd.do?typeId='},
		{id:'importnode', text:'导入模板',cls:'edit-mi',url:'${app}/workplan/tawwpmodel/modelimportadd.do?typeId='}
		//{id:'listnode', text:'详细列表',cls:'list-mi',url:'${app}/workplan/tawwpmodel/modellists.do?netType='},
		//{id:'copynode', text:'复制上月计划',cls:'list-mi',url:'${app}/../tawwpmonth/netselect.do?type='},
		//{id:'delnode', isDelete:true, text:'',cls:'remove-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=deleteFolder&folderPath='}
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
    <h1>作业计划模板管理</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>作业计划</dt>
		<dd>作业计划管理主要是面向作业计划的制定、发布与监控。由于这些流程中的许多部分是值班管理人员没有权限进行操作的，而且此项功能事关公司全局事务，因此，独立出来单独处理。
另外，该项功能将在进一步的强化中与企业计划管理系统关联或重复，可以通过接口系统进行数据的交互与同步。事实上，该项功能实现之后，企业计划管理中与生产相关的部分功能可以直接通过该系统实现。
</dd>
	    <dt>新建一个模板类型</dt>
		<dd>右键点击任何一个存在的模板类型,选择"新建模板";右侧展现新增原始页面,选择系统类型、选择网元类型、选择所属类型、填入模板名称;保存后在相应模板类型可见!</dd>
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