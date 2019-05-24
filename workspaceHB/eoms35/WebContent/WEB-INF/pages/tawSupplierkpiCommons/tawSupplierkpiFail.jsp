<%@ page language="java" import="com.boco.eoms.base.util.StaticMethod" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%String failInfo = StaticMethod.null2String(request.getAttribute("failInfo").toString());%>
<script type="text/javascript">
	//刷新父框架中的整颗树
	//parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	parent.AppFrameTree.reloadNode();
</script>
<div class="failurePage">
	<h1 class="header">${eoms:a2u('操作失败！')}</h1>
	<div class="content">
	<%if ((failInfo != null) && (!"".equals(failInfo))) {%>
	<%=failInfo%><br/>
	<%}else{%>
		${eoms:a2u('您的未能成功执行!')}<br/>
	<%}%>
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>