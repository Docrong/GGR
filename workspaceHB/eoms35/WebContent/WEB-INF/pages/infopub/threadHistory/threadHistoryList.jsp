<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<content tag="heading">
    <bean:message key="threadHistoryList.heading"/>
    >
    <eoms:id2nameDB id="${threadId}" beanId="threadDao"/>
</content>

<c:set var="buttons">

    <input type="button" style="margin-right: 5px"
           onclick="javascript:history.back(-1);"
           value="<fmt:message key="button.back"/>"/>

</c:set>


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<fmt:bundle basename="config/applicationResource-workbench-infopub">
    <display:table name="threadHistoryList" cellspacing="0" cellpadding="0"
                   id="threadHistoryList" pagesize="${pageSize }"
                   class="table threadHistoryList" export="true"
                   requestURI="${app}/workbench/infopub/threadHistory.do?method=search&threadId=${threadId}"
                   sort="list" partialList="true" size="resultSize"
                   decorator="com.boco.eoms.workbench.infopub.displaytag.support.ThreadBrowseHistoryListDisplaytagDecorator">

        <display:setProperty name="export.rtf" value="false"></display:setProperty>

        <display:column property="userId" sortable="true"
                        headerClass="sortable" titleKey="threadHistoryForm.userId"/>

        <display:column property="ip" sortable="true" headerClass="sortable"
                        titleKey="threadHistoryForm.ip"/>

        <display:column property="readTime" sortable="true"
                        headerClass="sortable" titleKey="threadHistoryForm.readTime"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:setProperty name="paging.banner.item_name"
                             value="threadHistory"/>
        <display:setProperty name="paging.banner.items_name"
                             value="threadHistorys"/>
    </display:table>
</fmt:bundle>

<c:out value="${buttons}" escapeXml="false"/>


<%@ include file="/common/footer_eoms.jsp" %>
