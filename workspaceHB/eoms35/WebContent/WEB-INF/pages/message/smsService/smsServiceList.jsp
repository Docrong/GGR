<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="smsServiceList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editSmsService.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="smsServiceList" cellspacing="0" cellpadding="0"
    id="smsServiceList" pagesize="25" class="table smsServiceList"
    export="true" requestURI="/smsServices.html" sort="external" partialList="true" size="resultSize">

    <display:column property="deleted" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.deleted"/>

    <display:column property="isSendImediat" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.isSendImediat"/>

    <display:column property="isSendNight" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.isSendNight"/>

    <display:column property="moduleId" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.moduleId"/>

    <display:column property="moduleName" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.moduleName"/>

    <display:column property="msgType" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.msgType"/>

    <display:column property="name" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.name"/>

    <display:column property="password" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.password"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.remark"/>

    <display:column property="urgency" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.urgency"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
        url="/editSmsService.html" paramId="id" paramProperty="id"
         titleKey="smsServiceForm.userId"/>

    <display:setProperty name="paging.banner.item_name" value="smsService"/>
    <display:setProperty name="paging.banner.items_name" value="smsServices"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("smsServiceList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>