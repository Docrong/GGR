<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
	try{
	  //刷新父框架中的整颗树
	  parent.AppFrameTree.refreshTree();
	  //刷新父框架中当前选中的节点
	  //parent.AppFrameTree.reloadNode();
	}catch(e){}
</script>
<c:if test="${flag !=null}">
<div class="failurePage">
	<h1 class="header">投票失败！</h1>
	<div class="content">
		您对该问题已经投过票了 <br>当前的票数为:<%=request.getAttribute("countVote") %>
	</div>
</div>
</c:if>
<c:if test="${flag==null}">
<div class="successPage">
	<h1 class="header">投票成功！</h1>
	<div class="content">
		谢谢您的投票 <br>当前的票数为:<%=request.getAttribute("countVote") %>
	</div>
</div>
</c:if>
<%@ include file="/common/header_eoms_innerpage.jsp"%>