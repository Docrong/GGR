<jsp:directive.page import="com.boco.eoms.km.train.util.TrainSpecialtyConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
<script type="text/javascript">
  var config = {
	/**************
	 * Tree Configs
	 **************/
	treeGetNodeUrl:"${app}/kmmanager/trainSpecialtys.do?method=getNodes",
	treeRootId:'<%=TrainSpecialtyConstants.TREE_ROOT_ID%>',
	treeRootText:'<fmt:message key="trainSpecialty.tree.rootText"/>',
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/kmmanager/trainSpecialtys.do?method=edit",text:""},
	ctxMenu:[
		{id:'newnode', text:'<fmt:message key="trainSpecialty.tree.add"/>',cls:'new-mi',url:'${app}/kmmanager/trainSpecialtys.do?method=add&nodeId='},
		{id:'edtnode', text:'<fmt:message key="trainSpecialty.tree.edit"/>',cls:'edit-mi',url:'${app}/kmmanager/trainSpecialtys.do?method=edit&nodeId='},
		{id:'delnode', isDelete:true, text:'<fmt:message key="trainSpecialty.tree.delete"/>',cls:'remove-mi',url:'${app}/kmmanager/trainSpecialtys.do?method=remove&nodeId='}
	],//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
</script>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1><fmt:message key="trainSpecialty.tree.header" /></h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<fmt:message key="trainSpecialty.help" />
	</dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
	<iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>