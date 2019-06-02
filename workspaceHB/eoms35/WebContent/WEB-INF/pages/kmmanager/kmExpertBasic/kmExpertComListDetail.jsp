<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">
	<html:form action="/kmExpertComs.do" method="post" styleId="kmExpertComForm">
		<display:table name="kmExpertComList" cellspacing="0" cellpadding="0"
			id="kmExpertComList" pagesize="${pageSize}"
			class="table kmExpertComList" export="false"
			requestURI="${app}/kmmanager/kmExpertComs.do?method=listDetail"
			sort="list" partialList="true" size="resultSize">
			
			<display:column property="expertComDate" sortable="true"
				format="{0,date,yyyy-MM-dd}" headerClass="sortable"
				titleKey="kmExpertCom.expertComDate" />

			<display:column property="expertComDetail" sortable="true"
				headerClass="sortable" titleKey="kmExpertCom.expertComDetail" />

			<display:column sortable="true" style="height=50"
				headerClass="sortable" titleKey="kmExpertCom.expertComPath">
				<eoms:attachment name="kmExpertComList" property="expertComPath" scope="page" idField="expertComPath" appCode="expert" viewFlag="Y" />
			</display:column>

			<display:setProperty name="paging.banner.item_name" value="kmExpertCom" />
			<display:setProperty name="paging.banner.items_name" value="kmExpertComs" />			
		</display:table>
	</html:form>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>