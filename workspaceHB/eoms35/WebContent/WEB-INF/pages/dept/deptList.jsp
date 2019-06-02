<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="deptList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editDept.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="deptList" cellspacing="0" cellpadding="0"
    id="deptList" pagesize="25" class="table deptList"
    export="true" requestURI="" sort="list">

    <display:column property="deptName" sortable="true" headerClass="sortable"
        url="/editDept.do" paramId="id" paramProperty="id"
         titleKey="deptForm.deptName"/>

    <display:column property="deptDescription" sortable="true" headerClass="sortable"
         titleKey="deptForm.deptDescription"/>

    <display:column property="dudajiang" sortable="true" headerClass="sortable"
         titleKey="deptForm.dudajiang"/>

    <display:column property="aaadd" sortable="true" headerClass="sortable"
         titleKey="deptForm.aaadd"/>

    <display:setProperty name="paging.banner.item_name" value="dept"/>
    <display:setProperty name="paging.banner.items_name" value="depts"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("deptList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>