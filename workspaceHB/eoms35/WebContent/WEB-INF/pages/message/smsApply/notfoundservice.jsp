<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
  try{
	parent.AppFrameTree.refreshTree();
  }catch(e){}
</script>
<div class="failurePage">
	<h1 class="header">操作失败</h1>
	<div class="content">
	${content}<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>