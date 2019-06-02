<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"<html:rewrite page='/forums.do?method=getNodes4Forum'/>",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('所有专题信息')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"<html:rewrite page='/forums.do?method=edit&id=${ForumsForm.id}'/>",text:"${eoms:a2u('修改专题')}"},
	ctxMenu:[
		{id:'NewForum', text:'${eoms:a2u('添加子专题')}',rootCanShow:true,cls:'new-mi',url:'<html:rewrite page='/forums.do?method=initAdd&id='/>'},		
		{id:'EditForum', text:'${eoms:a2u('修改专题')}',cls:'edit-mi',url:'<html:rewrite page='/forums.do?method=edit&id='/>'},
		{id:'MoveForum', text:'${eoms:a2u('移动到...')}',cls:'edit-mi',url:'<html:rewrite page='/forums.do?method=list&id='/>'},
		{id:'DelForum', isDelete:true, text:'${eoms:a2u('删除')}',cls:'remove-mi',url:'<html:rewrite page='/forums.do?method=delete&id='/>'}
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
	<bean:message key="forumsList.heading"/>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('专题管理简介')}</dt>
		<dd>${eoms:a2u('信息的集合')}</dd>
		<dt>${eoms:a2u('在专题管理里新增一个专题')}</dt>
		<dd>${eoms:a2u('在树图中的某一专题信息上单击右键，并选择"添加子专题"')}</dd>
		<dt>${eoms:a2u('修改一个专题的信息')}</dt>
		<dd>${eoms:a2u('在树图中的专题信息上单击左键或单击右键并选择"修改专题"')}</dd>
		<dt>${eoms:a2u('移动专题')}</dt>
		<dd>${eoms:a2u('在树图中的专题上单击右键，并选择"移动到..."，选择要移动到哪个专题')}</dd>
		<dt>${eoms:a2u('删除专题')}</dt>
		<dd>${eoms:a2u('在树图中的专题上单击右键，并选择"删除"')}</dd>
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
