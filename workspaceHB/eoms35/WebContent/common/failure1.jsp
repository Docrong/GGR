<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<div class="failurePage">
	<h1 class="header">${eoms:a2u('作业计划模版出错，应填写项为空')}</h1>	  
	<div class="content">
	    <logic:messagesPresent message="true">
	    <html:messages id="message" message="true"><%=message%></html:messages>
	    </logic:messagesPresent>
	</div>
</div>

<%@ include file="/common/footer_eoms.jsp"%>

