<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<fmt:bundle basename="config/ApplicationResources-supplierkpi">
	<content tag="heading">
	<fmt:message key="tawSupplierkpiInstanceList.StatOrder" />
	</content>
</fmt:bundle>

<%
   String pageNum = (String)request.getAttribute("pageNum");
   int num = Integer.parseInt(pageNum); 
   num = num*15;
%>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
	<!-- <c:out value="${buttons}" escapeXml="false"/> -->
	<display:table name="tawSuppKpiInstReportOrders" cellspacing="0"
		cellpadding="0" pagesize="15"
		requestURI="/supplierkpi/TawSuppKpiInstReportOrder.do?method=list"
		id="tawSuppKpiInstReportOrder"
		class="table tawSuppKpiInstReportOrders" partialList="true"
		size="size">

				<display:column titleKey="tawSupplierkpiInstanceList.numb"
					sortable="true" headerClass="sortable">
					<%=++num %>
				</display:column>
                <!-- url="/supplierkpi/editTawSuppKpiInstReportOrder.do?method=eidt"   -->
				<display:column property="reportName" sortable="true"
					headerClass="sortable"
					paramId="id" paramProperty="id"
					titleKey="tawSupplierkpiInstanceList.reportName" />

				<display:column property="serviceTypeName" sortable="true"
					headerClass="sortable"
					titleKey="tawSupplierkpiInstanceList.serviceType" />

				<display:column property="specialTypeName" sortable="true"
					headerClass="sortable"
					titleKey="tawSupplierkpiInstanceList.specialType" />

				<display:column property="kpiItemName" sortable="true"
					headerClass="sortable"
					titleKey="tawSupplierkpiInstanceList.kpiItemName" />

				<display:column property="latitudeName" sortable="true"
					headerClass="sortable"
					titleKey="tawSupplierkpiInstanceList.latitude" />

				<display:column property="reportTypeName" sortable="true"
					headerClass="sortable"
					titleKey="tawSupplierkpiInstanceList.reportType" />

				<display:column property="reportStartTime" sortable="true"
					headerClass="sortable"
					titleKey="tawSupplierkpiInstanceList.reportStartTime" />

				<display:column property="reportEndTime" sortable="true"
					headerClass="sortable"
					titleKey="tawSupplierkpiInstanceList.reportEndTime" />

				<display:column property="createMan" sortable="true"
					headerClass="sortable"
					titleKey="tawSuppKpiInstReportOrder.createMan" />
					
				<display:column headerClass="sortable" titleKey="button.delete">
					<a href="${app}/supplierkpi/TawSuppKpiInstReportOrder.do?method=delete&id=${tawSuppKpiInstReportOrder.id}&specialType=${tawSuppKpiInstReportOrder.specialType}" onclick="confirm('${eoms:a2u("您确实要删除该订制报表吗？")}')">${eoms:a2u('删除')}</a>
				</display:column>

			</display:table>
		</fmt:bundle>
		<c:out value="${buttons}" escapeXml="false" />

		<%@ include file="/common/footer_eoms.jsp"%>