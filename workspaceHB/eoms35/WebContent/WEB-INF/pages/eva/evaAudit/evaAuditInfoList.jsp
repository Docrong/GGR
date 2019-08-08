<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<fmt:bundle basename="config/ApplicationResources-eva">
    <content tag="heading">
        <bean:message key="eva.auditHistoryList.heading"/>
    </content>
    <display:table name="auditInfoList" cellspacing="0" cellpadding="0" id="auditInfoList" pagesize="${pageSize }"
                   class="table auditInfoList" export="false"
                   requestURI="${app}/eva/evaAudit.do?method=auditInfoList&templateId=${evaAuditForm.templateId}"
                   sort="list" partialList="true" size="resultSize"
                   decorator="com.boco.eoms.eva.util.AuditInfoListDisplaytagDecorator">

        <display:column property="auditUser" sortable="false" headerClass="sortable" titleKey="eva.auditInfo.user"/>

        <display:column property="status" sortable="false" headerClass="sortable" titleKey="eva.auditInfo.status"/>

        <display:column property="auditTime" sortable="false" headerClass="sortable" titleKey="eva.auditInfo.auditTime"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column property="auditInfo" sortable="false" headerClass="sortable"
                        titleKey="eva.auditInfo.auditInfo"/>

    </display:table>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp" %>
