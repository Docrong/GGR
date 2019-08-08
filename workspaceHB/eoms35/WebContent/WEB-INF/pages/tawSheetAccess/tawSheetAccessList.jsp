<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<content tag="heading">${eoms:a2u('流程附件模板管理')}</content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/access/editTawSheetAccess.do"/>'"
           value="<fmt:message key="button.add"/>"/>

</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSheetAccessList" cellspacing="0" cellpadding="0"
               id="tawDemoMytableList" class="table tawSheetAccessList"
               export="true" pagesize="25" requestURI="  " sort="external" partialList="true" size="resultSize">

    <display:column property="processname" sortable="true" headerClass="sortable"
                    url="/access/editTawSheetAccess.do" paramId="id" paramProperty="id"
                    title="${eoms:a2u('流程名称')}"/>

    <display:column property="taskname" sortable="true" headerClass="sortable"
                    url="/access/editTawSheetAccess.do" paramId="id" paramProperty="id"
                    title="${eoms:a2u('节点名称')}"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
                    url="/access/editTawSheetAccess.do" paramId="id" paramProperty="id"
                    title="${eoms:a2u('备注')}"/>

    <display:setProperty name="paging.banner.item_name" value="tawSheetAccess"/>
    <display:setProperty name="paging.banner.items_name" value="tawSheetAccesss"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp" %>

