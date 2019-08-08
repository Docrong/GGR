<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/mappings.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>

<fmt:bundle basename="config/applicationResource-mapping">

    <content tag="heading">
        <fmt:message key="mapping.list.heading"/>
    </content>

    <display:table name="mappingList" cellspacing="0" cellpadding="0"
                   id="mappingList" pagesize="${pageSize}" class="table mappingList"
                   export="false"
                   requestURI="${app}/mappingstorage/mappings.do?method=search"
                   sort="list" partialList="true" size="resultSize">

        <display:column property="app_code" sortable="true"
                        headerClass="sortable" titleKey="mapping.appcode"
                        href="${app}/mappingstorage/mappings.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="app_name" sortable="true"
                        headerClass="sortable" titleKey="mapping.appname"
                        href="${app}/mappingstorage/mappings.do?method=edit" paramId="id" paramProperty="id"/>
        <display:column property="new_table" sortable="true"
                        headerClass="sortable" titleKey="mapping.newtable"
                        href="${app}/mappingstorage/mappings.do?method=edit" paramId="id" paramProperty="id"/>
        <display:column property="beanid" sortable="true"
                        headerClass="sortable" titleKey="mapping.beanid"
                        href="${app}/mappingstorage/mappings.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="context" sortable="true"
                        headerClass="sortable" titleKey="mapping.context"
                        href="${app}/mappingstorage/mappings.do?method=edit" paramId="id" paramProperty="id"/>


        <display:setProperty name="paging.banner.item_name" value="mapping"/>
        <display:setProperty name="paging.banner.items_name" value="mappings"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>