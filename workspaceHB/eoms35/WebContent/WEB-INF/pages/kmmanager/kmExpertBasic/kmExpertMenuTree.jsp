<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.boco.eoms.km.expert.util.KmExpertMenuConstants" />
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>

<script type="text/javascript">
    var config = {
        /**************
        * Tree Configs
        **************/
        treeGetNodeUrl:'${app}/kmmanager/kmExpertMenu.do?method=getNodes',
        treeRootId:'<%=KmExpertMenuConstants.TREE_ROOT_ID%>',
        treeRootText:'<fmt:message key="kmExpertBasic.tree.rootText"/>',
        pageFrameId:'formPanel-page',
        onClick:{url:"${app}/kmmanager/kmExpertBasics.do?method=tree",text:""},

        /************************
        * Custom onLoad Functions
        *************************/
        onLoadFunctions:function(){
	}
  }; // end of config

  AppFrameTree.onBeforeSubmit = function(form, node){
  	form.action += "&fieldName=" + node.attributes.fieldName;
  }
  
  Ext.onReady(AppFrameTree.init, AppFrameTree);
</script>

<div id="headerPanel" class="x-layout-inactive-content">
    <h1><fmt:message key="kmExpertBasic.tree.rootText" /></h1>
</div>

<div id="helpPanel" style="padding: 10px;" class="x-layout-inactive-content">
    <dl><fmt:message key="kmExpertMenu.help" /></dl>
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
