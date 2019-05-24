<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonMessageAddServiceList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/message/editTawCommonMessageAddService.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonMessageAddServiceList" cellspacing="0" cellpadding="0"
    id="tawCommonMessageAddServiceList" pagesize="25" class="table tawCommonMessageAddServiceList"
    export="true" requestURI="" sort="list">

    <display:column property="issendimediat" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.issendimediat"/>

    <display:column property="issendnight" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.issendnight"/>

    <display:column property="messagetype" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.messagetype"/>

    <display:column property="modelid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.modelid"/>

    <display:column property="modelname" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.modelname"/>

    <display:column property="operid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.operid"/>

    <display:column property="opername" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.opername"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.remark"/>

    <display:column property="urgency" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageAddService.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageAddServiceForm.urgency"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonMessageAddService"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonMessageAddServices"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonMessageAddServiceList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>