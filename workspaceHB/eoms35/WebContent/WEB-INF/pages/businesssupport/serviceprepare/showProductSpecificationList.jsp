<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<bean:define id="url" value="serviceprepare.do"/>
	<display:table name="resultList" cellspacing="0" cellpadding="0"
		id="resultList" pagesize="${pageSize}" class="listTable"
		export="true" requestURI="serviceprepare.do"
		sort="list" size="${total}" partialList="true"
		decorator="com.boco.eoms.businessupport.serviceprepare.webapp.action.ListPSCommonDisplaytagDecoratorHelper">
		 <display:column property="name" sortable="true"
			headerClass="sortable" title="服务规格名称" />		
		 <display:column property="code" sortable="true"
			headerClass="sortable" title="编码" />				
		 <display:column property="comment" sortable="true"
			headerClass="sortable" title="服务规格说明" />										
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>
