<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonMessageOpertypeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/message/editTawCommonMessageOpertype.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonMessageOpertypeList" cellspacing="0" cellpadding="0"
    id="tawCommonMessageOpertypeList" pagesize="25" class="table tawCommonMessageOpertypeList"
    export="true" requestURI="" sort="list">

    <display:column property="modelid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageOpertype.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageOpertypeForm.modelid"/>

    <display:column property="modelname" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageOpertype.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageOpertypeForm.modelname"/>

    <display:column property="operid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageOpertype.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageOpertypeForm.operid"/>

    <display:column property="opername" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageOpertype.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageOpertypeForm.opername"/>

    <display:column property="operremark" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageOpertype.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageOpertypeForm.operremark"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonMessageOpertype"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonMessageOpertypes"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonMessageOpertypeList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>