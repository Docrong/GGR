<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemUserRefRoleList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/sysuser/editTawSystemUserRefRole.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemUserRefRoleList" cellspacing="0" cellpadding="0"
    id="tawSystemUserRefRoleList" pagesize="25" class="table tawSystemUserRefRoleList"
    export="true" requestURI="" sort="list">

    <display:column property="roleid" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUserRefRole.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserRefRoleForm.roleid"/>

    <display:column property="rolename" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUserRefRole.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserRefRoleForm.rolename"/>

    <display:column property="userid" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUserRefRole.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserRefRoleForm.userid"/>

    <display:column property="username" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUserRefRole.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserRefRoleForm.username"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUserRefRole.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserRefRoleForm.remark"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemUserRefRole"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemUserRefRoles"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemUserRefRoleList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>