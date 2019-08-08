<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <content tag="heading"><fmt:message key="tawSupplierkpiInstanceList.heading"/></content>
</fmt:bundle>
<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/supplierkpi/editTawSupplierkpiInstance.do"/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <display:table name="tawSupplierkpiInstanceList" cellspacing="0" cellpadding="0"
                   id="tawSupplierkpiInstanceList" pagesize="25" class="table tawSupplierkpiInstanceList"
                   export="true" requestURI="/supplierkpi/tawSupplierkpiInstances.do" sort="external" partialList="true"
                   size="resultSize">

        <display:column property="appendMan" sortable="true" headerClass="sortable"
                        url="/supplierkpi/editTawSupplierkpiInstance.do" paramId="id" paramProperty="id"
                        titleKey="tawSupplierkpiInstanceForm.appendMan"/>

        <display:column property="appendManArea" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.appendManArea"/>

        <display:column property="appendTime" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.appendTime"/>

        <display:column property="assessContent" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.assessContent"/>

        <display:column property="assessId" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.assessId"/>

        <display:column property="assessNote" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.assessNote"/>

        <display:column property="assessRole" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.assessRole"/>

        <display:column property="checkContent" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.checkContent"/>

        <display:column property="checkTime" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.checkTime"/>

        <display:column property="checkUser" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.checkUser"/>

        <display:column property="company" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.company"/>

        <display:column property="dataSource" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.dataSource"/>

        <display:column property="dataType" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.dataType"/>

        <display:column property="examineContent" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.examineContent"/>

        <display:column property="fillEndTime" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.fillEndTime"/>

        <display:column property="fillStratTime" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.fillStratTime"/>

        <display:column property="infoState" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.infoState"/>

        <display:column property="isImpersonality" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.isImpersonality"/>

        <display:column property="isPass" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.isPass"/>

        <display:column property="itemType" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.itemType"/>

        <display:column property="manufacturerId" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.manufacturerId"/>

        <display:column property="memo" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.memo"/>

        <display:setProperty name="paging.banner.item_name" value="tawSupplierkpiInstance"/>
        <display:setProperty name="paging.banner.items_name" value="tawSupplierkpiInstances"/>
    </display:table>
</fmt:bundle>
<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSupplierkpiInstanceList");
</script>

<%@ include file="/common/footer_eoms.jsp" %>