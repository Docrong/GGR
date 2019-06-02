<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/  
	treeGetNodeUrl:"${app}/workbench/contact/tawWorkbenchDeptContacts.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('所有分组')}",
	pageFrame:'formPanel-page',
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('新建分组')}',cls:'new-mi',url:'<c:url value="/workbench/contact/tawWorkbenchDeptContactGroups.do?method=saveToPage&nodeid=" />',rootCanShow:true},
		{id:'Edit', text:'${eoms:a2u('修改分组')}',cls:'edit-mi',url:'<c:url value="/workbench/contact/tawWorkbenchDeptContactGroups.do?method=xget&nodeid=" />'},
		{id:'Childs', text:'${eoms:a2u('新增人员')}',cls:'new-mi',url:'<c:url value="/workbench/contact/tawWorkbenchDeptContacts.do?method=saveToPage&nodeid=" />',rootCanShow:true},
		{id:'Import', text:'${eoms:a2u('部门树导入人员')}',cls:'new-mi',url:'<c:url value="/workbench/contact/import.do?nodeid=" />',rootCanShow:true},
		{id:'Edits', text:'${eoms:a2u('修改人员')}',cls:'edit-mi',url:'<c:url value="/workbench/contact/tawWorkbenchDeptContacts.do?method=xget&nodeid=" />'},
		{id:'Lists', text:'${eoms:a2u('人员详细')}',cls:'list-mi',url:'<c:url value="/workbench/contact/tawWorkbenchDeptContacts.do?method=xlist&nodeid=" />'},
		{id:'Delete', isDelete:true, text:'${eoms:a2u('删除')}',cls:'remove-mi',url:'<c:url value="/workbench/contact/tawWorkbenchDeptContacts.do?method=xdelete&nodeid=" />'}
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
	<h1>${eoms:a2u('通讯录')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('功能说明')}</dt>
	    <dd>${eoms:a2u('部门通讯录是记录本人联系人的信息，如手机号码，邮箱等。')}</dd>
	    <dd>${eoms:a2u('提供了个性化的分组功能，将不同的联系人按自己的要求放在各自的分组内，这样将很方便查找联系人。上级部门有权限查看下级部门建立的部门通讯录，下级部门是否能看到上级部门的通讯录取决于上级部门建立分组时是否选中【允许下级部门查看】')}</dd>
	    <dd>${eoms:a2u('还提供了按照分组的批量导入和导出功能，这样可以同时添加很多的联系人')}</dd>
	    <dd>${eoms:a2u('同时支持从部门树中导入系统内的部门人员')}</dd>
	    <dt>${eoms:a2u('新建分组')}</dt>
		<dd>${eoms:a2u('在【所有分组】上点击右键，选择【新建分组】。在新增分组里面选上“允许下级部门查看”时，该创建者所在的下级部门就有权限看到该分组数据，在所建立的分组上点击右键可以【修改】和【删除】分组。')}</dd>		
		<dt>${eoms:a2u('新增人员')}</dt>
		<dd>${eoms:a2u('在所选择的分组上点击右键 ，选择【新增人员】，也可以直接在所有分组上点击右键选择【新增人员】，在新增人员的页面内有一些是必填项，请认真填写，之后点击【保存】。在新增的人员上面点击右键可以【修改人员】，【人员详细】，和【删除人员】 其中在人员详情内点击邮箱地址可以直接给这个发送')}</dd>
		<dd>${eoms:a2u('点击【部门树导入人员】，可以从系统的人员树里导入系统内人员到部门通讯录里。')}</dd>
		<dt>${eoms:a2u('导入')}</dt>
		<dd>${eoms:a2u('点击【导入】，选择需要导入的分组名称，点击【导入】。注意的是导入的时候要按照一定的模板。')}</dd>
		<dt>${eoms:a2u('导出')}</dt>
		<dd>${eoms:a2u('点击【导出】，选择需要导入的分组名称，点击【导出】。直接导出excel的形式')}</dd>
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