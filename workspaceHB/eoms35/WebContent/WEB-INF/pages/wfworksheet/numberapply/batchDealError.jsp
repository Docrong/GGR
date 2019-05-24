<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="java.util.List" %>
<style type="text/css">
.successPage span.data{
	color:#1465B7;
	margin-left:65px;
}
</style>

<script type="text/javascript">
	function intoConfig(){
	var undo=document.location.href;
	 undo = undo.substring(0,undo.indexOf("?")+1)+"method=performBatchAdd&actionForword=${actionForword}&sheetKey=${sheetKey}";
	 location.href = undo;
	}
	window.setTimeout(intoConfig, 3000);
</script>

<div class="successPage">
<%String type = (String)request.getAttribute("type");
	if("1".equals(type)){
		String message = (String)request.getAttribute("message");
%>
<h1 class="header">${message }</h1>
<% } if ("2".equals(type)){
	String message = (String)request.getAttribute("message");
%>
<h1 class="header">${message }</h1>
<% } %>

<div class="content">
	   <ul>
	      <li><html:link href="#" onclick="intoConfig();">返回</html:link></li>
       </ul>
</div>























<%@ include file="/common/footer_eoms.jsp"%>