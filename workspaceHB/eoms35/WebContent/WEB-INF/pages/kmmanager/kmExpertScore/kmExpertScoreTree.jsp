<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.km.file.util.KmFileTreeConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod"%>
<%
String treeType = StaticMethod.nullObject2String(request.getAttribute("treeType"));
%>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>

<script type="text/javascript">
  var config = {
	/**************
	 * Tree Configs
	 **************/
	treeGetNodeUrl:"${app}/kmmanager/expertScore.do?method=getNodes",
	treeRootId:'1010104',
	treeRootText:'专业分类',
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/kmmanager/expertScore.do?method=showExpertScores&treeType=<%=treeType %>&nodeId=",text:""},

	ctxMenu:[
		{id:'newnode', text:'<fmt:message key="kmFileTree.tree.add"/>',cls:'new-mi',url:'${app}/kmmanager/kmFileTrees.do?method=add&node='},
		{id:'edtnode', text:'<fmt:message key="kmFileTree.tree.edit"/>',cls:'edit-mi',url:'${app}/kmmanager/kmFileTrees.do?method=edit&node='},
		{id:'delnode', isDelete:true, text:'<fmt:message key="kmFileTree.tree.delete"/>',cls:'remove-mi',url:'${app}/kmmanager/kmFileTrees.do?method=remove&node='},
		{id:'audnode', text:'配置审核人',cls:'edit-mi',url:'${app}/kmmanager/kmFileTrees.do?method=audit&node='},
		{id:'operate', text:'配置权限',cls:'edit-mi',url:'${app}/kmmanager/kmOperates.do?method=userTree&nodeType=file&node='}		
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

<div id="helpPanel" style="padding: 10px;" class="x-layout-inactive-content">
  <dl><fmt:message key="kmFileTree.help" /></dl>
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