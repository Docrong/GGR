<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"<html:rewrite page='/forums.do?method=getNodes4Thread'/>",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('所有专题信息')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"<html:rewrite page='/thread.do?method=list&forumsId='/>",text:"${eoms:a2u('信息列表')}"},
	ctxMenu:[
		{id:'NewThread', text:'${eoms:a2u('信息发布')}',cls:'new-mi',url:'<html:rewrite page='/thread.do?method=edit&forumsId='/>'},
		{id:'ListThreads', text:'${eoms:a2u('信息列表')}',cls:'new-mi',url:'<html:rewrite page='/thread.do?method=list&forumsId='/>'},
		{id:'ListUnreadThreads', text:'${eoms:a2u('未读信息')}',cls:'new-mi',url:'<html:rewrite page='/thread.do?method=listUnread&forumsId='/>'}
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
	<!-- <img src="${app}/styles/default/images/header-user.gif">  -->
	<bean:message key="threadTree.heading"/>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('信息列表简介')}</dt>
		<dd>${eoms:a2u('专题下拥有多条信息，在信息列表提供发布信息、修改信息、删除信息、查询浏览历史、归类信息（将某信息归入其他专题）等')}</dd>
		<dt>${eoms:a2u('发布信息')}</dt>
		<dd>${eoms:a2u('在树图中的某专题上单击右键，并选择"信息发布"，即可发布信息。注意：发布信息时会根据发布人所在部门限制查看部门范围。')}</dd>
		<dt>${eoms:a2u('修改信息')}</dt>
		<dd>${eoms:a2u('在树图中的某专题上单击左键或单击右键选择"信息列表"，左键单击某信息，若有权限即可修改信息内容。')}</dd>
		<dt>${eoms:a2u('删除信息')}</dt>
		<dd>${eoms:a2u('在树图中的某专题上单击左键或单击右键选择"信息列表"，右侧显示信息列表，将要删除的信息勾选，点击删除按扭。')}</dd>
		<dt>${eoms:a2u('归类')}</dt>
		<dd>${eoms:a2u('在树图中的某专题上单击左键或单击右键选择"信息列表"，右侧显示信息列表，将要归类的信息勾选，点击归类按扭。')}</dd>
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
