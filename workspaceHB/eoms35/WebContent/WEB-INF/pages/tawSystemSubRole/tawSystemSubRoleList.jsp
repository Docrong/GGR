<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>

<%
	String path = request.getContextPath();
	int roleId = StaticMethod.nullObject2int(request.getAttribute("roleId"));
%>

<content tag="heading"><fmt:message key="tawSystemSubRoleList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/role/editTawSystemSubRole.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<input type="hidden" name="roleId" id="roleId" value="<%=roleId%>">

<display:table name="tawSystemSubRoleList" cellspacing="0" cellpadding="0"
    id="tawSystemSubRoleList" pagesize="25" class="table tawSystemSubRoleList"
    export="true" requestURI="/role/tawSystemSubRoles.do" sort="external" partialList="true" size="resultSize">

	<display:column property="subRoleName" sortable="true" headerClass="sortable" url="/role/editTawSystemSubRole.do" paramId="id" paramProperty="id"
         titleKey="tawSystemSubRoleForm.subRoleName"/>
         
    <display:column property="area" sortable="true" headerClass="sortable"
         titleKey="tawSystemSubRoleForm.area"/>

    <display:column property="deptId" sortable="true" headerClass="sortable"
         titleKey="tawSystemSubRoleForm.deptId"/>
         
    <display:column property="type1" sortable="true" headerClass="sortable"
         titleKey="tawSystemSubRoleForm.type1"/>
         
    <display:column property="type2" sortable="true" headerClass="sortable"
         titleKey="tawSystemSubRoleForm.type2"/>

    <display:column property="limitCount" sortable="true" headerClass="sortable"
         titleKey="tawSystemSubRoleForm.limitCount"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemSubRole"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemSubRoles"/>
</display:table>

<input type="button" style="margin-right: 5px"
        onclick="location.href='<%=path%>/role/editTawSystemSubRole.do?roleId=<%=roleId%>'"
        value="<fmt:message key="button.add"/>"/>
<input type="button" style="margin-right: 5px"
        onclick="location.href='<%=path%>/role/editTawSystemSubRole.do?method=create&roleId=<%=roleId%>'"
        value="<fmt:message key="tawSystemSubRoleForm.create"/>"/>

<script type="text/javascript">
    highlightTableRows("tawSystemSubRoleList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>