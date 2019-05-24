<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemAreaForm.title"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/area/editTawSystemArea.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemAreaList" cellspacing="0" cellpadding="0"
    id="tawSystemAreaList" pagesize="25" class="table tawSystemAreaList"
    export="true" requestURI="/tawSystemAreas.html" sort="external" partialList="true" size="resultSize">

    <display:column property="areaid" sortable="true" headerClass="sortable"
        url="//area/editTawSystemArea.do" paramId="id" paramProperty="id"
         titleKey="tawSystemAreaForm.areaid"/>

    <display:column property="areaname" sortable="true" headerClass="sortable"
        url="//area/editTawSystemArea.do" paramId="id" paramProperty="id"
         titleKey="tawSystemAreaForm.areaname"/>

    <display:column property="leaf" sortable="true" headerClass="sortable"
        url="//area/editTawSystemArea.do" paramId="id" paramProperty="id"
         titleKey="tawSystemAreaForm.leaf"/>
         
    <display:column property="areacode" sortable="true" headerClass="sortable"
        url="//area/editTawSystemArea.do" paramId="id" paramProperty="id"
         titleKey="tawSystemAreaForm.areacode"/>
         
    <display:column property="remark" sortable="true" headerClass="sortable"
        url="//area/editTawSystemArea.do" paramId="id" paramProperty="id"
         titleKey="tawSystemAreaForm.remark"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemArea"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemAreas"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemAreaList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>