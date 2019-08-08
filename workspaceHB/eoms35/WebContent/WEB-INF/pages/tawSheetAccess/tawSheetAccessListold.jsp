<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<content tag="heading"><fmt:message key="tawSheetAccessList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/access/editTawSheetAccess.do"/>'"
           value="<fmt:message key="button.add"/>"/>

</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSheetAccessList" cellspacing="0" cellpadding="0"
               id="tawSheetAccessList" class="table tawSheetAccessList"
               export="true" requestURI="/tawSheetAccesss.do">

    <display:column property="processname" sortable="true" headerClass="sortable"
                    url="/access/editTawSheetAccess.do" paramId="id" paramProperty="id"
                    titleKey="tawSheetAccessForm.processname"/>

    <display:column property="taskname" sortable="true" headerClass="sortable"
                    url="/access/editTawSheetAccess.do" paramId="id" paramProperty="id"
                    titleKey="tawSheetAccessForm.taskname"/>

    <display:column property="accesss" sortable="true" headerClass="sortable"
                    url="/access/editTawSheetAccess.do" paramId="id" paramProperty="id"
                    titleKey="tawSheetAccessForm.access"/>


    <display:column property="remark" sortable="true" headerClass="sortable"
                    url="/access/editTawSheetAccess.do" paramId="id" paramProperty="id"
                    titleKey="tawSheetAccessForm.remark"/>

    <display:setProperty name="paging.banner.item_name" value="tawSheetAccess"/>
    <display:setProperty name="paging.banner.items_name" value="tawSheetAccesss"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp" %>

