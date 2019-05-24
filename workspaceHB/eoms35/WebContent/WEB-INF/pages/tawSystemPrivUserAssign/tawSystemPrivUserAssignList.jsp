<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemPrivUserAssignList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawSystemPrivUserAssign.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemPrivUserAssignList" cellspacing="0" cellpadding="0"
    id="tawSystemPrivUserAssignList" pagesize="25" class="table tawSystemPrivUserAssignList"
    export="true" requestURI="/tawSystemPrivUserAssigns.do" sort="external" partialList="true" size="resultSize">

    <display:column property="currentprivid" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.currentprivid"/>

    <display:column property="currentprivname" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.currentprivname"/>

    <display:column property="hide" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.hide"/>

    <display:column property="isonepriv" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.isonepriv"/>

    <display:column property="leaf" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.leaf"/>

    <display:column property="ordercode" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.ordercode"/>

    <display:column property="parentprivid" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.parentprivid"/>

    <display:column property="parentprivname" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.parentprivname"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.remark"/>

    <display:column property="userid" sortable="true" headerClass="sortable"
        url="/editTawSystemPrivUserAssign.do" paramId="id" paramProperty="id"
         titleKey="tawSystemPrivUserAssignForm.userid"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemPrivUserAssign"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemPrivUserAssigns"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemPrivUserAssignList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>