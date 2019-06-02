<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/  
	treeGetNodeUrl:"${app}/workbench/contact/tawWorkbenchContacts.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('鎵€鏈夊垎缁?)}",
	pageFrame:'formPanel-page',
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('鏂板缓鍒嗙粍')}',cls:'new-mi',url:'<c:url value="/workbench/contact/tawWorkbenchContactGroups.do?method=saveToPage&nodeid=" />',rootCanShow:true},
		{id:'Edit', text:'${eoms:a2u('淇敼鍒嗙粍')}',cls:'edit-mi',url:'<c:url value="/workbench/contact/tawWorkbenchContactGroups.do?method=xget&nodeid=" />'},
		{id:'Childs', text:'${eoms:a2u('鏂板浜哄憳')}',cls:'new-mi',url:'<c:url value="/workbench/contact/tawWorkbenchContacts.do?method=saveToPage&nodeid=" />',rootCanShow:true},
		{id:'Edits', text:'${eoms:a2u('淇敼浜哄憳')}',cls:'edit-mi',url:'<c:url value="/workbench/contact/tawWorkbenchContacts.do?method=xget&nodeid=" />'},
		{id:'Lists', text:'${eoms:a2u('浜哄憳璇︾粏')}',cls:'list-mi',url:'<c:url value="/workbench/contact/tawWorkbenchContacts.do?method=xlist&nodeid=" />'},
		{id:'Delete', isDelete:true, text:'${eoms:a2u('鍒犻櫎')}',cls:'remove-mi',url:'<c:url value="/workbench/contact/tawWorkbenchContacts.do?method=xdelete&nodeid=" />'}
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
	<h1>${eoms:a2u('閫氳褰?)}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('甯姪')}</dt>
		<dd>${eoms:a2u('甯姪')}</dd>
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