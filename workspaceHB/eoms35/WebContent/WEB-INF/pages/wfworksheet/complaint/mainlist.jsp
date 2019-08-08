<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    function openSheet(url) {
        if (parent.frames['north']) {
            parent.frames['north'].location.href = url;
        } else {
            location.href = url;
        }
    }
</script>
<%
    String source = request.getParameter("6578706f7274");
    if (source == null) {
%>
<c:if test="${findForward != null && findForward == 'mainlist'}">
    <jsp:include
            page="/WEB-INF/pages/wfworksheet/common/listsendDoneJS.jsp"/>
</c:if>
<%}%>
<bean:define id="url" value="complaint.do"/>

<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table taskList"
               export="true" requestURI="complaint.do"
               sort="external" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.base.webapp.action.MainListDisplaytagDecoratorHelper">

    <display:column property="sheetId" sortable="true" sortName="main.sheetId"
                    headerClass="sortable" title="工单流水号"/>

    <display:column property="title" sortable="true" sortName="main.title"
                    headerClass="sortable" title="工单主题"/>

    <display:column property="sendTime" sortable="true" sortName="main.sendTime"
                    headerClass="sortable" title="派单时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column property="sheetAcceptLimit" sortable="true" sortName="main.sheetAcceptLimit"
                    headerClass="sortable" title="受理时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="sheetCompleteLimit" sortable="true" sortName="main.sheetCompleteLimit"
                    headerClass="sortable" title="完成时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column sortable="true" headerClass="sortable" title="工单状态" sortName="main.status">
        <eoms:dict key="dict-sheet-common" dictId="sheetStatus" itemId="${taskList.status}" beanId="id2descriptionXML"/>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<!--
html:link
page="/pvm.do?method=showDetailPage&sheetKey=${worksheet.sheetKey}&taskId=${worksheet.id}&taskName=${worksheet.taskName}"
target="portal-north"
-->

<%@ include file="/common/footer_eoms.jsp" %>
