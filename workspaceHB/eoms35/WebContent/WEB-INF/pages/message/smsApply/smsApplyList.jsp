<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="smsApplyList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editSmsApply.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="smsApplyList" cellspacing="0" cellpadding="0"
    id="smsApplyList" pagesize="25" class="table smsApplyList"
    export="true" requestURI="/smsApplys.html" sort="external" partialList="true" size="resultSize">

    <display:column property="count" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.count"/>

    <display:column property="cycle" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.cycle"/>

    <display:column property="cycleTime" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.cycleTime"/>

    <display:column property="endTime" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.endTime"/>

    <display:column property="name" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.name"/>

    <display:column property="interval" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.interval"/>

    <display:column property="mobile" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.mobile"/>

    <display:column property="receiverId" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.receiverId"/>

    <display:column property="receiverType" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.receiverType"/>

    <display:column property="regetData" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.regetData"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.remark"/>

    <display:column property="sendDay" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.sendDay"/>

    <display:column property="sendHour" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.sendHour"/>

    <display:column property="sendMin" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.sendMin"/>

    <display:column property="sendStatus" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.sendStatus"/>

    <display:column property="serviceId" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.serviceId"/>

    <display:column property="startTime" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.startTime"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
        url="/editSmsApply.html" paramId="id" paramProperty="id"
         titleKey="smsApplyForm.userId"/>

    <display:setProperty name="paging.banner.item_name" value="smsApply"/>
    <display:setProperty name="paging.banner.items_name" value="smsApplys"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("smsApplyList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>