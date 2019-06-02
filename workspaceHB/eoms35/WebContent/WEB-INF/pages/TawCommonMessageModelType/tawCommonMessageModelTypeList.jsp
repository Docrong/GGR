<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonMessageModelTypeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/message/editTawCommonMessageModelType.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonMessageModelTypeList" cellspacing="0" cellpadding="0"
    id="tawCommonMessageModelTypeList" pagesize="25" class="table tawCommonMessageModelTypeList"
    export="true" requestURI="" sort="list">

    <display:column property="modelid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageModelType.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageModelTypeForm.modelid"/>

    <display:column property="modelname" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageModelType.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageModelTypeForm.modelname"/>

    <display:column property="modelremark" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageModelType.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageModelTypeForm.modelremark"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonMessageModelType"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonMessageModelTypes"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonMessageModelTypeList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>