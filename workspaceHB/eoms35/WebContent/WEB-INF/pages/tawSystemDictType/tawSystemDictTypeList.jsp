<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemDictTypeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/dict/editTawSystemDictType.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemDictTypeList" cellspacing="0" cellpadding="0"
    id="tawSystemDictTypeList" pagesize="25" class="table tawSystemDictTypeList"
    export="true" requestURI="" sort="list">

    <display:column property="dictId" sortable="true" headerClass="sortable"
        titleKey="tawSystemDictTypeForm.dictId"/>

    <display:column property="dictCode" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictType.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictTypeForm.dictCode"/>

    <display:column property="dictName" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictType.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictTypeForm.dictName"/>

    <display:column property="sysType" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictType.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictTypeForm.sysType"/>

    <display:column property="dictRemark" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictType.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictTypeForm.dictRemark"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemDictType"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemDictTypes"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemDictTypeList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>