<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<div class="failurePage">
    <h1 class="header">${eoms:a2u('发生错误')}</h1>
    <div class="content">
        <logic:messagesPresent message="true">
            <html:messages id="message" message="true"><%=message%>
            </html:messages>
        </logic:messagesPresent>
    </div>
</div>

<%@ include file="/common/footer_eoms.jsp" %>

