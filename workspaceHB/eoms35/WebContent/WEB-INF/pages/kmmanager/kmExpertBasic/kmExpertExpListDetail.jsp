<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
    <html:form action="/kmExpertExps.do" method="post" styleId="kmExpertExpForm">
		<display:table name="kmExpertExpList" cellspacing="0" cellpadding="0"
			id="kmExpertExpList" pagesize="${pageSize}"
			class="table kmExpertExpList" export="false"
			requestURI="${app}/kmmanager/kmExpertExps.do?method=listDetail"
			sort="list" partialList="true" size="resultSize">
			
			<display:column property="expertCompany" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertCompany" />

			<display:column property="expertStart" sortable="true"
				format="{0,date,yyyy-MM-dd}" headerClass="sortable"
				titleKey="kmExpertExp.expertStart" />

			<display:column property="expertEnd" sortable="true"
				format="{0,date,yyyy-MM-dd}" headerClass="sortable"
				titleKey="kmExpertExp.expertEnd" />

			<display:column property="expertPosition" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertPosition" />

			<display:column property="expertAddress" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertAddress" />

			<display:column property="expertResponsiblitily" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertResponsiblitily" />

			<display:setProperty name="paging.banner.item_name" value="kmExpertExp" />
			<display:setProperty name="paging.banner.items_name" value="kmExpertExps" />
	    </display:table>
    </html:form>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>