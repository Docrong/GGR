<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemPostTypeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawSystemPostType.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemPostTypeList" cellspacing="0" cellpadding="0"
    id="tawSystemPostTypeList" pagesize="25" class="table tawSystemPostTypeList"
    export="true" requestURI="/tawSystemPostTypes.do" sort="external" partialList="true" size="resultSize">

    <display:column property="deleted" sortable="true" headerClass="sortable"
         titleKey="tawSystemPostTypeForm.deleted"/>

    <display:column property="notes" sortable="true" headerClass="sortable"
         titleKey="tawSystemPostTypeForm.notes"/>

    <display:column property="posttype_name" sortable="true" headerClass="sortable"
         titleKey="tawSystemPostTypeForm.posttype_name"/>

    <display:column property="system_name" sortable="true" headerClass="sortable"
         titleKey="tawSystemPostTypeForm.system_name"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemPostType"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemPostTypes"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemPostTypeList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>