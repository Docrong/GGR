<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<div id="sheetform">
    <html:form action="/${module}.do?method=performQuery" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/businessdredgebroad/commonqueryhead.jsp" %>
        <jsp:include page="/WEB-INF/pages/wfworksheet/${module}/especialquery.jsp"/>
        <%@ include file="/WEB-INF/pages/wfworksheet/businessdredgebroad/commonquerybottom.jsp" %>
        <!-- buttons -->
        <div class="form-btns">
            <html:submit styleClass="btn" property="method.save" styleId="method.save">
                <fmt:message key="button.done"/>
            </html:submit>
        </div>
    </html:form>
</div>
<%@ include file="/WEB-INF/pages/wfworksheet/businessdredgebroad/commonqueryJs2.jsp" %>
<%@ include file="/common/footer_eoms.jsp" %>
