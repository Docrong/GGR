<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/log/tawCommonLogConditions.do?method=getNodes",
    treeRootId:'0',
	treeRootText:"${eoms:a2u('所有模块业务')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"<html:rewrite page='/TawCommonLogCondition/querydo.do?searchbyoper='/>",text:"${eoms:a2u('信息列表')}"},
	ctxMenu:[
		{id:'newnode', text:'${eoms:a2u('取消建模块业务')}',cls:'new-mi',url:'<c:url value="/msg/newModule.do?status=1&parentid=" />'},
		{id:'editnode', text:'${eoms:a2u('1修改模块业务')}',cls:'edit-mi',url:'<c:url value="/msg/smsServices.do?method=xeditbefore&status=1&id=" />'},
		{id:'newsnode', text:'${eoms:a2u('2新增服务')}',cls:'new-mi',url:'<c:url value="/msg/newService.do?status=2&parentid=" />'},
		{id:'edtsnode', text:'${eoms:a2u('3修改服务')}',cls:'edit-mi',url:'<c:url value="/msg/smsServices.do?method=xedit2service&status=2&id=" />'},
		{id:'copynode', text:'${eoms:a2u('4复制服务ID至剪切板')}',cls:'edit-mi',url:'<c:url value="/msg/coptToClipboard.do?status=2&id=" />'},
		{id:'deletesnode', isDelete:true, text:'${eoms:a2u('5删除')}',cls:'remove-mi',url:'<c:url value="/msg/smsServices.do?method=xdelete&id=" />'}
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
	<img src="${app}/styles/default/images/header-msg.gif">
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
  		<dt>${eoms:a2u('功能说明')}</dt>
        <dd>${eoms:a2u('记录模块操作的日志，有助于管理员根据错误信息查看日志，找到问题所在。')}</dd>
    </dl>
    <br/>
	<dl>
  		<dt>${eoms:a2u('日志管理')}</dt>
        <dd>${eoms:a2u('系统日志管理提供对系统日志的查询、统计和删除功能。')}</dd>
		<dt>${eoms:a2u('模块日志查询')}</dt>
        <dd>${eoms:a2u('点击菜单树查询。')}</dd>
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
