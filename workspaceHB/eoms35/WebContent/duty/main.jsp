<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut" %>
<%@page import="com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.commons.log.service.logSave" %>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String userid = sessionform.getUserid();
String discrip=userid + " 于" + StaticMethod.getCurrentDateTime() + " 登录模块.";
String menuId = StaticMethod.null2String(request.getParameter("id"));
TawSystemPrivAssignOut mgr = TawSystemPrivAssignOut.getInstance();
String menuName = mgr.getNameBycode(menuId);
logSave log= logSave.getInstance(menuName, userid, menuId,request.getRemoteAddr(), discrip,menuId);
log.info(); 
%>
<script type="text/javascript" src="${app}/scripts/layout/main.js"></script>
<script type="text/javascript">
	var config = {
		treeGetNodeUrl:"${app}/xtree.do?method=nav",
		treeRootId:"${param.id}",
		treeRootText:"<%=menuName%>",
		scheme:"${scheme}",
		serverName:"${serverName}"
		log : true /*是否记录树图点击信息*/
	};
	Ext.onReady(mainLayout);
</script>
<style type="text/css">
#classes{
	padding:5px;
	overflow:auto;
	background:url(${app}/styles/default/images/panlebg.gif) repeat-x left bottom;
}
.x-tree-node a:hover, .x-tree-node a:hover span{
	text-decoration:underline;
}	
</style>

<div id="classes" class="x-layout-inactive-content">
	<div id="classes-body"></div>
</div>

<!-- iframe -->
<iframe id="pages" name="pages" frameborder="0" src="${app}/duty/dutyevent.do?method=mainRecord" width="0" height="0"></iframe>

<%@ include file="/common/footer_eoms.jsp"%>
