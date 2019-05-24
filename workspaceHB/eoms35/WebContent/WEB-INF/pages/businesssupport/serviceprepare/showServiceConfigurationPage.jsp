<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<bean:define id="url" value="serviceprepare.do"/>
	<display:table name="resultList" cellspacing="0" cellpadding="0"
		id="resultList" pagesize="${pageSize}" class="listTable"
		export="true" requestURI="serviceprepare.do"
		sort="list" size="${total}" partialList="true"
		decorator="com.boco.eoms.businessupport.serviceprepare.webapp.action.ListCommonDisplaytagDecoratorHelper">
		 <display:column property="professionalServiceDirectory_name" sortable="true"
			headerClass="sortable" title="专业服务名称" />	
		 <display:column property="taskLinks_chName" sortable="true"
			headerClass="sortable" title="环节名称" />	
		 <display:column property="processType_name" sortable="true"
			headerClass="sortable" title="流程名称" />	
		 <display:column property="productSpecification_name" sortable="true"
			headerClass="sortable" title="服务规格名称" />	
		 <display:column  sortable="true"
			headerClass="sortable" title="是否必做任务">
			<c:if test="${resultList.serviceConfiguration_isNeed=='0' }">
			是
			</c:if>
			<c:if test="${resultList.serviceConfiguration_isNeed=='1' }">
			否
			</c:if>			
	     </display:column>						
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>
