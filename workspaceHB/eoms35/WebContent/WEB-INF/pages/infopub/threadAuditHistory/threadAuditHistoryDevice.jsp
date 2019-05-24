<jsp:directive.page import="com.boco.eoms.base.util.StaticVariable" />
<jsp:directive.page import="com.boco.eoms.workbench.infopub.util.InfopubConstants" />
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="net.fckeditor.FCKeditor"%>
<%@ page import="com.boco.eoms.workbench.infopub.webapp.form.ThreadAuditHistoryForm"%>
<title><bean:message key="threadAuditHistoryDetail.title" /></title>
<content tag="heading">
<bean:message key="threadAuditHistoryDetail.heading" />
>
<eoms:id2nameDB id="${thread.id}" beanId="threadDao" />
</content>


<fmt:bundle basename="config/applicationResource-workbench-infopub">

	<display:table name="threadAuditHistoryList" cellspacing="0" cellpadding="0" id="threadAuditHistoryList" pagesize="25" class="table threadAuditHistoryList" export="true" requestURI="${app}/workbench/infopub/threadAuditHistory.do?method=search"
		sort="external" partialList="true" size="resultSize" decorator="com.boco.eoms.workbench.infopub.displaytag.support.ThreadAuditHistoryListDisplayTagDecorator">



		<display:column property="orgId" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.orgId" />

		<display:column property="submitTime" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.submitTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="opinion" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.opinion" />

		<display:column property="status" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.status" />

		<display:column property="auditTime" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.auditTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="isCurrent" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.isCurrent" />

		<display:column property="note" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.note" />

		<display:setProperty name="paging.banner.item_name" value="threadAuditHistory" />
		<display:setProperty name="paging.banner.items_name" value="threadAuditHistorys" />
	</display:table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
