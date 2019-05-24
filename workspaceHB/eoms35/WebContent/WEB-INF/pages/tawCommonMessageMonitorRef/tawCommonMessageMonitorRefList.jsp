<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonMessageMonitorRefList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/message/editTawCommonMessageMonitorRef.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonMessageMonitorRefList" cellspacing="0" cellpadding="0"
    id="tawCommonMessageMonitorRefList" pagesize="25" class="table tawCommonMessageMonitorRefList"
    export="true" requestURI="" sort="list">

    <display:column property="monitorid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitorRef.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageMonitorRefForm.monitorid"/>

    <display:column property="teloremail" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitorRef.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageMonitorRefForm.teloremail"/>

    <display:column property="toobjectid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitorRef.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageMonitorRefForm.toobjectid"/>

    <display:column property="userid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitorRef.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageMonitorRefForm.userid"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonMessageMonitorRef"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonMessageMonitorRefs"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonMessageMonitorRefList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>