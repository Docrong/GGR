<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url
                   value="/sheet/overtimetip/overtimetip.do?method=showInputPage&flowName=${flowName}"/>'"
           value="<bean:message key="button.add"/>"/>
</c:set>


<display:table name="timeList" cellspacing="0" cellpadding="0"
               id="timeList" pagesize="${pageSize}" class="table timeList" size="timeTotal"
               export="true" requestURI="" sort="list"
               decorator="com.boco.eoms.sheet.overtimetip.webapp.action.OvertimeTipListDisplaytagDecoratorHelper">
    <c:if test="${!empty columnMap}">
        <c:if test="${!empty columnMap['specialty1']}">
            <display:column property="specialty1" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty1']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty2']}">
            <display:column property="specialty2" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty2']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty3']}">
            <display:column property="specialty3" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty3']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty4']}">
            <display:column property="specialty4" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty4']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty5']}">
            <display:column property="specialty5" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty5']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty6']}">
            <display:column property="specialty6" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty6']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty7']}">
            <display:column property="specialty7" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty7']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty8']}">
            <display:column property="specialty8" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty8']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty9']}">
            <display:column property="specialty9" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty9']}"/>
        </c:if>
        <c:if test="${!empty columnMap['specialty10']}">
            <display:column property="specialty10" sortable="true" headerClass="sortable"
                            title="${columnMap['specialty10']}"/>
        </c:if>
    </c:if>
    <display:column property="overtimeLimit" sortable="true" headerClass="sortable" title="预超时提醒时间(分钟)"/>
    <display:column property="userId" sortable="true" headerClass="sortable" title="设置人"/>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<c:out value="${buttons}" escapeXml="false"/>
<%@ include file="/common/footer_eoms.jsp" %>