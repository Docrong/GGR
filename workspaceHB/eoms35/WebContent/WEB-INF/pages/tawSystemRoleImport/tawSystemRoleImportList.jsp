<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<fmt:bundle basename="config.ApplicationResources-role">
    <content tag="heading">
        <fmt:message key="tawSystemRoleImportList.heading"/>
    </content>
    <fmt:bundle basename="config.ApplicationResources">
        <c:set var="buttons">
            <input type="button" style="margin-right: 5px"
                   onclick="location.href='<html:rewrite page='/systemRoleImport.do?method=edit'/>'"
                   value="<fmt:message key="button.add"/>"/>
        </c:set>
    </fmt:bundle>
    <!-- <c:out value="${buttons}" escapeXml="false"/> -->

    <display:table name="tawSystemRoleImportList" cellspacing="0"
                   cellpadding="0" id="tawSystemRoleImportList" pagesize="25"
                   class="table tawSystemRoleImportList" export="false"
                   requestURI="/role/systemRoleImport.do?method=list" sort="list"
                   partialList="true" size="resultSize">
        <display:column property="version" sortable="true"
                        headerClass="sortable" titleKey="tawSystemRoleImportForm.version"/>
        <display:column property="memo" sortable="true" headerClass="sortable"
                        titleKey="tawSystemRoleImportForm.memo"/>
        <display:column property="versionAt" sortable="true"
                        headerClass="sortable" titleKey="tawSystemRoleImportForm.versionAt"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column sortable="true"
                        headerClass="sortable" titleKey="tawSystemRoleImportForm.accessoriesId">
            <eoms:download ids="${tawSystemRoleImportList.accessoriesId }"></eoms:download>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="tawSystemRoleImportForm.operation.import"
                        url="/role/systemRoleImport.do?method=importrole" paramProperty="id" paramId="id">
            <fmt:message key="tawSystemRoleImportForm.operation.import"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="tawSystemRoleImportForm.operation.restore"
                        url="/role/systemRoleImport.do?method=restore" paramProperty="id" paramId="id">
            <fmt:message key="tawSystemRoleImportForm.operation.restore"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="tawSystemRoleImportForm.operation.delete"
                        url="/role/systemRoleImport.do?method=delete" paramProperty="id" paramId="id">
            <fmt:message key="tawSystemRoleImportForm.operation.delete"/>
        </display:column>

        <display:setProperty name="paging.banner.item_name"
                             value="tawSystemRoleImport"/>
        <display:setProperty name="paging.banner.items_name"
                             value="tawSystemRoleImports"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp" %>

