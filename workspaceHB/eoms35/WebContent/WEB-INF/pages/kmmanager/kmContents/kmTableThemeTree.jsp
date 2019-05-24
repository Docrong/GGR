<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.km.table.util.KmTableThemeConstants" />
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%
String listType = StaticMethod.nullObject2String(request.getAttribute("listType"));

%>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>

<script type="text/javascript">
  var config = {
	/**************
	 * Tree Configs
	 **************/
	treeGetNodeUrl:"${app}/kmmanager/themes.do?method=getNodesShow",
	treeRootId:'<%=KmTableThemeConstants.TREE_ROOT_ID%>',
	treeRootText:'知识库分类',
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/kmmanager/kmContentss.do?method=search<%=listType %>",text:""},

	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config

  AppFrameTree.onBeforeSubmit = function(form, node){
  	form.action += "&nodeLeaf=" + node.attributes.leaf;
  }

  Ext.onReady(AppFrameTree.init, AppFrameTree);
</script>

<div id="headerPanel" class="x-layout-inactive-content">
  <h1><fmt:message key="kmContents.tree.header" /></h1>
</div>

<div id="helpPanel" style="padding: 10px;" class="x-layout-inactive-content">
  <dl><fmt:message key="kmTableTheme.help" /></dl>
</div>

<div id="treePanel" class="x-layout-inactive-content">
  <div id="treePanel-tb" class="tb"></div>
  <div id="treePanel-body"></div>
</div>

<div id="formPanel" class="x-layout-inactive-content">
  <iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width: 100%; height: 100%" src=""></iframe>
</div>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>