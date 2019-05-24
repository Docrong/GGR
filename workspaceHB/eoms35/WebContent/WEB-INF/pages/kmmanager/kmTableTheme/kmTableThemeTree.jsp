<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.km.table.util.KmTableThemeConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
<script type="text/javascript">
  var config = {
	/**************
	 * Tree Configs
	 **************/
	treeGetNodeUrl:"${app}/kmmanager/kmTableThemes.do?method=getNodes",
	treeRootId:'<%=KmTableThemeConstants.TREE_ROOT_ID%>',
	treeRootText:'<fmt:message key="kmTableTheme.tree.rootText"/>',
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/kmmanager/kmTableThemes.do?method=edit",text:""},
	ctxMenu:[
		{id:'newnode', text:'<fmt:message key="kmTableTheme.tree.add"/>',cls:'new-mi',url:'${app}/kmmanager/kmTableThemes.do?method=add&nodeId='},
		{id:'edtnode', text:'<fmt:message key="kmTableTheme.tree.edit"/>',cls:'edit-mi',url:'${app}/kmmanager/kmTableThemes.do?method=edit&nodeId='},
		{id:'delnode', isDelete:true, text:'<fmt:message key="kmTableTheme.tree.delete"/>',cls:'remove-mi',url:'${app}/kmmanager/kmTableThemes.do?method=remove&nodeId='},
		{id:'audnode', text:'配置审核人',cls:'edit-mi',url:'${app}/kmmanager/kmTableThemes.do?method=audit&nodeId='},
		{id:'operate', text:'配置权限',cls:'edit-mi',url:'${app}/kmmanager/kmOperates.do?method=userTree&nodeType=content&node='}
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
	<h1><fmt:message key="kmTableTheme.tree.header" /></h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<fmt:message key="kmTableTheme.help" />	
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