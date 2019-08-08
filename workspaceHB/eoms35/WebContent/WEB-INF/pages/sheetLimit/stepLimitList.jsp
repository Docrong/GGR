<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url
                   value="/sheet/newSheetLimit/sheetLimit.do?method=editLevelLimit&id=${levelId}&flowName=${flowName}&type=addStep"/>'"
           value="<bean:message key="button.add"/>"/>
</c:set>


<display:table name="stepLimitList" cellspacing="0" cellpadding="0"
               id="stepLimitList" pagesize="30" class="table stepLimitList" size="timeTotal"
               export="true" requestURI="" sort="list">
    <display:column property="taskCnName" sortable="true" headerClass="sortable"
                    title="步骤名"/>
    <display:column property="acceptLimit" sortable="true" headerClass="sortable" title="受理时限"/>
    <display:column property="completeLimit" sortable="true" headerClass="sortable" title="处理时限"/>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<c:if test="${timeTotal==0 }">
    <c:out value="${buttons}" escapeXml="false"/>
</c:if>
<%@ include file="/common/footer_eoms.jsp" %>