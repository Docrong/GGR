<jsp:directive.page import="com.boco.eoms.km.table.util.KmTableGeneralConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
		
<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
<script type="text/javascript"> 
  var config = {
	/**************
	 * Tree Configs
	 **************/
	treeGetNodeUrl:"${app}/kmmanager/kmTableGenerals.do?method=getNodes&tableId=${tableId}",
	treeRootId:'${tableId}',
	treeRootText:'${tableChname}',
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/kmmanager/kmTableColumns.do?method=edit&nodeId=",text:""},
	ctxMenu:[		
		{id:'newnode', text:'<fmt:message key="kmTableGeneral.tree.add"/>',cls:'new-mi',url:'${app}/kmmanager/kmTableColumns.do?method=add&nodeId=${tableId}'},
		{id:'edtnode', text:'<fmt:message key="kmTableGeneral.tree.update"/>',cls:'edit-mi',url:'${app}/kmmanager/kmTableColumns.do?method=edit&nodeId='},
		{id:'delnode', isDelete:true, text:'<fmt:message key="kmTableGeneral.tree.delete"/>',cls:'remove-mi',url:'${app}/kmmanager/kmTableColumns.do?method=remove&nodeId='}
	],//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
  
  function createModel(){
   var nodeId=AppFrameTree.tree.root.id;
   //alert(nodeId);
   location.href='${app}/kmmanager/kmTableGenerals.do?method=createModel&nodeId='+nodeId;
   return true;
  }
</script>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1><fmt:message key="kmTableGeneral.tree.header" />	
	</h1>
		
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<fmt:message key="kmTableGeneral.help" />			
	</dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
	  <input type="button" style="margin-right: 5px"
		onclick="createModel()"
		value="<fmt:message key="kmTableGeneral.createTable"/>" />	
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div> 

<div id="formPanel" class="x-layout-inactive-content"> 
	<iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>