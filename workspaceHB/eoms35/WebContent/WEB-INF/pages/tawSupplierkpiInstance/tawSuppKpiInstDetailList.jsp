<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<jsp:directive.page
        import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance"/>

<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <content tag="heading">
        <fmt:message key="tawSupplierkpiInstanceList.title"/>
    </content>
</fmt:bundle>
<%
    String pageNum = (String) request.getAttribute("pageNum");
    int num = Integer.parseInt(pageNum);
    num = num * 15;
%>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <!-- <c:out value="${buttons}" escapeXml="false"/> -->
    <display:table name="tawSupplierkpiInstMonomerList" cellspacing="0"
                   cellpadding="0" pagesize="15"
                   requestURI="/supplierkpi/queryMonomertawSuppKpiIns.do?method=query"
                   id="tawSupplierkpiInstMonomerList"
                   class="table tawSupplierkpiInstMonomerList" partialList="true"
                   size="resultSize">

        　　 <display:column titleKey="tawSupplierkpiInstanceList.numb" sortable="true"
                           headerClass="sortable">
        <%=++num %>
    </display:column>

        <display:column property="manufacturerName" sortable="true"
                        headerClass="sortable" titleKey="tawSupplierkpiInstanceList.supplier"/>

        <display:column property="kpiName" sortable="true"
                        headerClass="sortable" titleKey="tawSupplierkpiInstanceList.kpiName"/>

        <display:column property="examineContent" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.examineContent"/>

        <display:column property="assessNote" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.assessNote"/>

        <display:column property="fillRole" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.fillRole"/>

        <display:column property="firstFillTime" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.firstFillTime"/>

        <display:setProperty name="paging.banner.item_name" value="tawSupplierkpiInstance"/>
        <display:setProperty name="paging.banner.items_name" value="tawSupplierkpiInstances"/>


    </display:table>
</fmt:bundle>
<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp" %>
