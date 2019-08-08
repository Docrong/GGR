<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>

<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");

    String userId = sessionform.getUserid();
%>

<div id="sheetform">
    <html:form action="/${module}.do?method=performQueryHB" styleId="theform">
        <input type="hidden" name="type" id="type" value="interface"/>
        <input type="hidden" name="interface" id="interface" value="interface"/>
        <input type="hidden" name="userName" id="userName" value="<%=userId%>"/>
        <%@ include file="/WEB-INF/pages/wfworksheet/commonfault/commonqueryheadhb.jsp" %>
        <jsp:include page="/WEB-INF/pages/wfworksheet/${module}/especialquery.jsp"/>
        <%@ include file="/WEB-INF/pages/wfworksheet/common/commonquerybottom.jsp" %>
        <!-- buttons -->
        <div class="form-btns">
            <html:submit styleClass="btn" property="method.save" styleId="method.save">
                <fmt:message key="button.done"/>
            </html:submit>
        </div>
    </html:form>
</div>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp" %>
<%@ include file="/common/footer_eoms.jsp" %>