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

<bean:define id="url" value="netOptimize.do"/>
<% String source = request.getParameter("6578706f7274");
    if (source == null) {
%>
<c:if test="${findForward != null && findForward == 'list'}">
    <jsp:include page="/WEB-INF/pages/wfworksheet/common/listsendUndoJS.jsp"/>
</c:if>
<%} %>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="true" requestURI="netOptimize.do"
               sort="external" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">
    <display:caption media="html">
        <span class="map alert">${eoms:a2u('将要超时的工单')}</span>
        <span class="map serious">${eoms:a2u('已经超时的工单')}</span>
    </display:caption>
    <display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
    <display:column property="sheetId" sortable="true" sortName="task.sheetId"
                    headerClass="sortable" title="${eoms:a2u('工单流水号')}"/>

    <display:column property="title" sortable="true" sortName="main.title"
                    headerClass="sortable" title="${eoms:a2u('工单主题')}"/>

    <display:column property="sendTime" sortable="true" sortName="task.sendTime"
                    headerClass="sortable" title="${eoms:a2u('派单时间')}"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
                    headerClass="sortable" title="${eoms:a2u('完成时限')}"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="taskDisplayName" sortable="true" sortName="task.taskDisplayName"
                    headerClass="sortable" title="${eoms:a2u('处理环节')}"/>

    <display:column sortable="true" headerClass="sortable" title="${eoms:a2u('任务状态')}" sortName="task.taskStatus">
        <c:if test="${taskList.ifWaitForSubTask=='true'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
        </c:if>
        <c:if test="${empty taskList.ifWaitForSubTask||taskList.ifWaitForSubTask=='false'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskList.taskStatus}"
                       beanId="id2descriptionXML"/>
        </c:if>
    </display:column>

    <display:column sortable="true" headerClass="sortable" title="${eoms:a2u('任务所有者')}" sortName="task.taskOwner">
        <eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao"/>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>

</display:table>
<%@ include file="/common/footer_eoms.jsp" %>