<%@ page language="java" import="java.util.*,java.util.List" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/mmsreportTemplates.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>
<script type="text/javascript">
    function openSheet(url) {

        //alert(url);

        if (doConfirm("你确定要删除吗？")) {
            if (parent.frames['north']) {
                parent.frames['north'].location.href = url;
            } else {
                location.href = url;
            }
        } else {
            return flase;
        }
    }

    function doConfirm(str) {
        if (confirm(str)) {
            return true;
        } else {
            return flase;
        }
    }

</script>
<fmt:bundle basename="config/applicationResources-mms">

    <bean:define id="url" value="mmsreportTemplates.do"/>
    <content tag="heading">
        <fmt:message key="mmsreportTemplate.list.heading"/>
    </content>

    <display:table name="mmsreportTemplateList" cellspacing="0" cellpadding="0"
                   id="mmsreportTemplateList" pagesize="${pageSize}" class="table mmsreportTemplateList"
                   export="false"
                   requestURI="mmsreportTemplates.do"
                   sort="list" partialList="true" size="resultSize"
                   decorator="com.boco.eoms.commons.mms.mmsreporttemplate.util.MmsreportTemplateDisplaytagDecoratorHelper">

        <display:column property="mmsName" sortable="true"
                        headerClass="sortable" titleKey="mmsreportTemplate.mmsName"/>


        <display:column property="executeCycle" sortable="true"
                        headerClass="sortable" titleKey="mmsreportTemplate.executeCycle"/>

        <display:column property="statReportId" sortable="true"
                        headerClass="sortable" titleKey="mmsreportTemplate.statReportId"/>

        <display:column property="mmsReportDesc" sortable="true"
                        headerClass="sortable" titleKey="mmsreportTemplate.mmsReportDesc"/>

        <display:column property="showDetail" sortable="true"
                        headerClass="sortable" titleKey="mmsreportTemplate.showDetail"/>

        <display:column property="showModify" sortable="true"
                        headerClass="sortable" titleKey="mmsreportTemplate.showModify"/>

        <display:column property="showDelete" sortable="true"
                        headerClass="sortable" titleKey="mmsreportTemplate.showDelete"/>

        <display:setProperty name="paging.banner.item_name" value="mmsreportTemplate"/>
        <display:setProperty name="paging.banner.items_name" value="mmsreportTemplates"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>