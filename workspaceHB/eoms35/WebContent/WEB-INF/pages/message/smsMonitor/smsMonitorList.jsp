<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<content tag="heading"><fmt:message key="smsMonitorList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/editSmsMonitor.html"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="smsMonitorList" cellspacing="0" cellpadding="0"
               id="smsMonitorList" pagesize="25" class="table smsMonitorList"
               export="true" requestURI="/smsMonitors.html" sort="external" partialList="true" size="resultSize">

    <display:column property="applyId" sortable="true" headerClass="sortable"
                    url="/editSmsMonitor.html" paramId="id" paramProperty="id"
                    titleKey="smsMonitorForm.applyId"/>

    <display:column property="content" sortable="true" headerClass="sortable"
                    url="/editSmsMonitor.html" paramId="id" paramProperty="id"
                    titleKey="smsMonitorForm.content"/>

    <display:column property="dispatchTime" sortable="true" headerClass="sortable"
                    url="/editSmsMonitor.html" paramId="id" paramProperty="id"
                    titleKey="smsMonitorForm.dispatchTime"/>

    <display:column property="mobile" sortable="true" headerClass="sortable"
                    url="/editSmsMonitor.html" paramId="id" paramProperty="id"
                    titleKey="smsMonitorForm.mobile"/>

    <display:column property="receiverId" sortable="true" headerClass="sortable"
                    url="/editSmsMonitor.html" paramId="id" paramProperty="id"
                    titleKey="smsMonitorForm.receiverId"/>

    <display:setProperty name="paging.banner.item_name" value="smsMonitor"/>
    <display:setProperty name="paging.banner.items_name" value="smsMonitors"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("smsMonitorList");
</script>

<%@ include file="/common/footer_eoms.jsp" %>