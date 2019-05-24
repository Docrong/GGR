<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<div class="failurePage">
	<h1 class="header">${eoms:a2u('发生错误')}</h1>  
	<div class="content">
	    <%=request.getAttribute("message")%>
	</div>
</div>

<%@ include file="/common/footer_eoms.jsp"%>

