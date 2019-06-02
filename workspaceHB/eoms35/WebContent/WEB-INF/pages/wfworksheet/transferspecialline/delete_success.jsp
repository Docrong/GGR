<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<script type="text/javascript">
	Try.these(
		function(){
			parent.frames['portal-south'].location.reload();
		}
	);
	
	function intoUndo(){
	 var undo=document.location.href;
	 undo = undo.substring(0,undo.indexOf("?")+1)+"method=xquery";
	 if('${ifReference}'!=null&&'${ifReference}'!='')
	 {
	 undo.action="transferspeciallines.do?method=xquery";
	 }else{
 	 undo.action ="transferspeciallines.do?method=xquery";
	 }
	 location.href = undo;
	}
</script>

<div class="successPage">
	<h1 class="header">删除成功！</h1>
	<div class="content">
	   <ul>
	      <li><html:link href="#" onclick="intoUndo();">返回传输专线列表</html:link></li>
       </ul>
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>