<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url
                   value="/sheet/dealtypeconfig/dealtypeconfig.do?method=showInputPage&flowName=${flowName}"/>'"
           value="<bean:message key="button.add"/>"/>
</c:set>

<display:table name="configList" cellspacing="0" cellpadding="0"
               id="configList" pagesize="${pageSize}" class="table configList" size="configTotal"
               export="false" requestURI="" sort="list"
               decorator="com.boco.eoms.sheet.dealtypeconfig.webapp.action.DealTypeConfigListDisplaytagDecoratorHelper">
    <display:column property="taskDisplayName" sortable="true" headerClass="sortable" title="环节"/>
    <display:column property="dealPerformerType" sortable="true" headerClass="sortable" title="处理类型"/>

</display:table>
<c:if test="${empty configTotal || configTotal==0 }">
    <c:out value="${buttons}" escapeXml="false"/>
</c:if>
<%@ include file="/common/footer_eoms.jsp" %>