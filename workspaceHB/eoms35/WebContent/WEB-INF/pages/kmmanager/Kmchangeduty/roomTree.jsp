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
	onClick:{url:"${app}/kmmanager/Kmchangeduty/query_apply.do?roomId=",text:"${eoms:a2u('查看')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('申请换班')}',cls:'new-mi',url:'${app}/kmmanager/Kmchangeduty/selectDate.do?roomId='},
		{id:'Delete', text:'${eoms:a2u('查看记录')}',cls:'new-mi',url:'${app}/kmmanager/Kmchangeduty/query_apply.do?roomId='}
		
		 ],//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
  //树的右键节点
 // {id:'List', text:'${eoms:a2u('批量申请')}',cls:'new-mi',url:'${app}/kmmanager/Kmchangeduty/batch_select.do?roomId='}
  </script>
  <style type="text/css">
  	body{background-image:none;}
  </style>
<div id="headerPanel" class="x-layout-inactive-content">
    <h1>${eoms:a2u("换班管理")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	 	<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('换班管理是提供对换班的自动化管理，它的主要流程是申请人提出换班，被换班人查看是否同意换班，如果同意提交到管理员处由管理员最终审核，如果都同意则生效。')}</dd>
	    <dt>${eoms:a2u('选择值班小组：')}</dt>
		<dd>${eoms:a2u('列出来的值班小组都是管理员对用户配置的值班小组域。')}</dd>
		<dt>${eoms:a2u('申请 ：')}</dt>
		<dd>${eoms:a2u('选择值班小组域，点击右键，选择【申请】 进入选择时间页面，选择原值班日期，和希望值班日期，点击申请。之后选择左边的原值班班次，再选择右面的希望值班班次。在下面填写申请原因，提交。')}</dd>
		<dt>${eoms:a2u('查看 ：')}</dt>
		<dd>${eoms:a2u('选择值班小组域，点击右键，选择【查看】 进入查看页面。（1）申请人可以查看【状态】知道申请流程走到哪里。可以对本次申请进行删除。（2）接受人查看可以对本次申请【同意】或者【不同意】，如果同意提交给管理员进行审核，如果不同意驳回给请求人。（3）管理员进入则可以审核【同意】与【不同意】 当同意的时候本次申请成功，如果不同意本次申请失败。')}</dd>
		
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