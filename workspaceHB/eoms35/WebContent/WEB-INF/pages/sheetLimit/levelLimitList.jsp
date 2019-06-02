<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<content tag="heading">
工单时限管理
</content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/sheet/newSheetLimit/sheetLimit.do?method=editLevelLimit&flowName=${flowName}&type=add"/>'"
        value="<fmt:message key="button.add"/>"/>
</c:set>
<script type="text/javascript">
function getUrl(url)
	{
		window.open(url);
	}
</script>

<display:table name="levelLimitList" cellspacing="0" cellpadding="0"
    id="levelLimitList" pagesize="15" class="table levelLimitList" size="timeTotal"
    export="true" requestURI="" sort="list" decorator="com.boco.eoms.sheet.limit.webapp.action.SheetLimitListDisplaytagDecoratorHelper">
    <c:if test="${!empty columnMap}">
        <c:if test="${!empty columnMap['level1']}">
    		<display:column property="level1" sortable="true" headerClass="sortable"
         		title="${columnMap['level1']}"/>
    	</c:if>
        <c:if test="${!empty columnMap['level2']}">
    		<display:column property="level2" sortable="true" headerClass="sortable"
         		title="${columnMap['level2']}"/>
    	</c:if>
        <c:if test="${!empty columnMap['level3']}">
    		<display:column property="level3" sortable="true" headerClass="sortable"
         		title="${columnMap['level3']}"/>
    	</c:if>
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
    </c:if>
    <display:column property="acceptLimit" sortable="true" headerClass="sortable" title="受理时限(分钟)"/>
    <display:column property="replyLimit" sortable="true" headerClass="sortable" title="处理时限(分钟)"/>
    <display:column  property="stepLimitList" sortable="false" headerClass="sortable"
         		title="环节时限"/>
  
   <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
	<display:setProperty name="export.csv" value="false"/>
  </display:table>

<c:out value="${buttons}" escapeXml="false"/>
<%@ include file="/common/footer_eoms.jsp"%>