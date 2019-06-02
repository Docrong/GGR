<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.km.file.util.KmFileTreeConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript" src="${app}/scripts/kmmanager/AppFrameTreeKm.js"></script>
<script type="text/javascript">
	AppFrameTreeKm.fixUrl=function(url) {
			if (url.substr(url.length - 1) == "=") {
				return url + this.selNode.id;
			} else if (url.indexOf('?') > 0) {
				return url + "&orgId=" + this.selNode.id+ "&orgType=" + this.selNode.attributes.nodeType;
			} else {
				return url + "?orgId=" + this.selNode.id+ "&orgType=" + this.selNode.attributes.nodeType;
			}
		};
  var config = {
	/**************
	 * Tree Configs
	 **************/
	treeGetNodeUrl:"${app}/kmmanager/kmOperates.do?method=userFromDept",
	treeRootId:'-1',
	treeRootText:'权限管理',
	pageFrameId:'config-page',
	onClick:{url:"${app}/kmmanager/kmOperates.do?method=operateConfig&nodeType=${operType}&nodeId=${nodeId}",text:"权限配置"},
	ctxMenu:[
	
	],//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTreeKm.init, AppFrameTreeKm);

</script>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1>权限管理</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		注：
	<br><br>
	读权限:<br>对目录和对应的文件只能进行查看操作
	<br><br>
	写权限:<br>对目录和对应的文件可以进行增加，查看，修改，删除操作
	<br><br>
	授予权限：<br>对目录和对应的文件可以进行增加，查看，修改，删除操作，
			   并且可给别的用户赋予对自己有权限目录的各种操作权限
	<br><br>
	排出权限：<br>对该用户清除对此目录的所有操作权限（主要用在整个部门配好权限后对某些用户的权限排出上）

	</dl>
</div>
<div id="treePanel" >
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
	<iframe id="config-page" name="config-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>