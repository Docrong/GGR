<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<%
    String specialType = request.getAttribute("specialType").toString();
    String serviceType = request.getAttribute("serviceType").toString();
%>

<script type="text/javascript">
    function addKpiItem() {
        var url = "<c:url value="/supplierkpi/editTawSupplierkpiItem.do"/>";
        url += "?specialTypeForComboBox=<%=specialType%>"
        location.href = url;
    }
</script>

<c:set var="buttons">

</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <content tag="heading">
        <eoms:id2nameDB id="<%=serviceType%>" beanId="ItawSystemDictTypeDao"/>
            ${eoms:a2u('-')}
        <eoms:id2nameDB id="<%=specialType%>" beanId="ItawSystemDictTypeDao"/>
            ${eoms:a2u(' 评估指标列表')}</content>
    <display:table name="tawSupplierkpiItemList" cellspacing="0" cellpadding="0"
                   id="tawSupplierkpiItemList" pagesize="25" class="table tawSupplierkpiItemList"
                   export="true" requestURI="/supplierkpi/tawSupplierkpiItems.do" sort="external" partialList="true"
                   size="resultSize">

        <display:column property="kpiName" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiItemForm.kpiName"/>

        <display:column property="id2dataSource" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiItemForm.dataSource"/>

        <display:column property="id2dataType" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiItemForm.dataType"/>

        <display:column property="id2statictsCycle" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiItemForm.statictsCycle"/>

        <display:column property="id2writeManner" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiItemForm.writeManner"/>

        <display:column property="id2unit" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiItemForm.unit"/>

        <display:column property="id2isImpersonality" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiItemForm.isImpersonality"/>

        <display:setProperty name="paging.banner.item_name" value="tawSupplierkpiItem"/>
        <display:setProperty name="paging.banner.items_name" value="tawSupplierkpiItems"/>
    </display:table>
</fmt:bundle>
<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp" %>