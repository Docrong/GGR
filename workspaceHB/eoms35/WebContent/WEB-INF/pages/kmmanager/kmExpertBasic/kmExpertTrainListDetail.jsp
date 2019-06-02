<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
	<html:form action="/kmExpertTrains.do" method="post" styleId="kmExpertTrainForm">
		<display:table name="kmExpertTrainList" cellspacing="0" cellpadding="0"
			id="kmExpertTrainList" pagesize="${pageSize}"
			class="table kmExpertTrainList" export="false"
			requestURI="${app}/kmmanager/kmExpertTrains.do?method=listDetail"
			sort="list" partialList="true" size="resultSize">

			<display:column property="expertLicense" sortable="true"
				headerClass="sortable" titleKey="kmExpertTrain.expertLicense" />

			<display:column property="expertTrainDate" sortable="true"
				format="{0,date,yyyy-MM-dd}" headerClass="sortable"
				titleKey="kmExpertTrain.expertTrainDate" />

			<display:column property="expertTrainTime" sortable="true"
				format="{0,date,yyyy-MM-dd}" headerClass="sortable"
				titleKey="kmExpertTrain.expertTrainTime" />

			<display:column property="expertTrainLesson" sortable="true"
				headerClass="sortable" titleKey="kmExpertTrain.expertTrainLesson" />

			<display:column property="expertCity" sortable="true"
				headerClass="sortable" titleKey="kmExpertTrain.expertCity" />

			<display:column property="expertUnit" sortable="true"
				headerClass="sortable" titleKey="kmExpertTrain.expertUnit" />

			<display:column property="expertTrainDes" sortable="true"
				headerClass="sortable" titleKey="kmExpertTrain.expertTrainDes" />

			<display:setProperty name="paging.banner.item_name" value="kmExpertTrain" />
			<display:setProperty name="paging.banner.items_name" value="kmExpertTrains" />
		</display:table>
	</html:form>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>