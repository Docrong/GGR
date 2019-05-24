<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
	<html:form action="/kmExpertCets.do" method="post" styleId="kmExpertCetForm">
		<display:table name="kmExpertCetList" cellspacing="0" cellpadding="0"
			id="kmExpertCetList" pagesize="${pageSize}"
			class="table kmExpertCetList" export="false"
			requestURI="${app}/kmmanager/kmExpertCets.do?method=listDetail"
			sort="list" partialList="true" size="resultSize">

			<display:column property="expertCetName" sortable="true"
				headerClass="sortable" titleKey="kmExpertCet.expertCetName" />

			<display:column property="expertCetDate" sortable="true"
				headerClass="sortable" titleKey="kmExpertCet.expertCetDate" />

			<display:column property="expertCetDetail" sortable="true"
				headerClass="sortable" titleKey="kmExpertCet.expertCetDetail" />

			<display:column sortable="true" style="height=50"
				headerClass="sortable" titleKey="kmExpertCet.expertCetPath">
				<eoms:attachment name="kmExpertCetList" property="expertCetPath" scope="page" idField="expertCetPath" appCode="expert" viewFlag="Y" />
			</display:column>

			<display:setProperty name="paging.banner.item_name" value="kmExpertCet" />
			<display:setProperty name="paging.banner.items_name" value="kmExpertCets" />
		</display:table>
	</html:form>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>