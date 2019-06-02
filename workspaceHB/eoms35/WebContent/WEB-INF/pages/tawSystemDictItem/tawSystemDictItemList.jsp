<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemDictItemList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/dict/editTawSystemDictItem.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemDictItemList" cellspacing="0" cellpadding="0"
    id="tawSystemDictItemList" pagesize="25" class="table tawSystemDictItemList"
    export="true" requestURI="" sort="list">

    <display:column property="itemId" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictItem.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictItemForm.itemId"/>

    <display:column property="itemName" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictItem.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictItemForm.itemName"/>

    <display:column property="dictId" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictItem.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictItemForm.dictId"/>

    <display:column property="dictName" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictItem.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictItemForm.dictName"/>

    <display:column property="itemCode" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictItem.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictItemForm.itemCode"/>

    <display:column property="itemCodeEx" sortable="true" headerClass="sortable"
         titleKey="tawSystemDictItemForm.itemCodeEx"/>

    <display:column property="itemRemark" sortable="true" headerClass="sortable"
        url="/dict/editTawSystemDictItem.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDictItemForm.itemRemark"/>

    <display:column property="sysType" sortable="true" headerClass="sortable"
         titleKey="tawSystemDictItemForm.sysType"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemDictItem"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemDictItems"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemDictItemList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>