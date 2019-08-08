<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<% String source = request.getParameter("6578706f7274");
    if (source == null) {
%>
<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/batchJs.jsp"/>
<c:if test="${findForward != null && findForward == 'list'}">
    <jsp:include page="/WEB-INF/pages/wfworksheet/commonfault/listsendUndoJS.jsp"/>
</c:if>
<%} %>


<bean:define id="url" value="commonfault.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="true" requestURI="commonfault.do"
               sort="external" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.commonfault.webapp.action.ProcessListCommonFaultDisplaytagDecoratorHelper">
    <% if (source == null) { %>
    <display:caption media="html">
        <span class="map alert colorrow">将要超时的工单</span>
        <span class="map serious">已经超时的工单</span>
    </display:caption>
    <display:column sortable="true" property="id" media="html"
                    headerClass="sortable" title=""/>
    <display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
    <%} %>
    <display:column property="sheetId" sortable="true" sortName="task.sheetId"
                    headerClass="sortable" title="工单流水号"/>
    <display:column property="title" sortable="true" sortName="main.title"
                    headerClass="sortable" title="工单主题"/>
    <display:column property="mainNetName" sortable="true" sortName="main.mainNetName"
                    headerClass="sortable" title="网元名称"/>

    <display:column sortable="true" sortName="main.mainFaultResponseLevel"
                    headerClass="sortable" title="故障处理响应级别">
        <eoms:id2nameDB id="${taskList.mainFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column property="sendTime" sortable="true" sortName="task.sendTime"
                    headerClass="sortable" title="派单时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
                    headerClass="sortable" title="完成时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="taskDisplayName" sortable="true" sortName="task.taskDisplayName"
                    headerClass="sortable" title="处理环节"/>

    <display:column sortable="true" headerClass="sortable" title="任务状态" sortName="task.taskStatus">

        <c:if test="${taskList.ifWaitForSubTask=='true'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
        </c:if>
        <c:if test="${empty taskList.ifWaitForSubTask||taskList.ifWaitForSubTask=='false'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskList.taskStatus}"
                       beanId="id2descriptionXML"/>
        </c:if>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
        <eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao"/>
    </display:column>

    <% if (source == null) { %>
    <logic:notEmpty name="batchTaskMapKey">
        <display:footer>
            <tr>
                <td colspan="11">

                    <input type="radio" name="bathTask" value="none" onclick="checkAll('none')" checked="checked"> 全不选

                    <logic:iterate id="batchTaskMapKey" name="batchTaskMapKey">
                        <input type="radio" name="bathTask" value="${batchTaskMapKey}"
                               onclick="checkAll('${batchTaskMapKey}')"> ${batchTaskMap[batchTaskMapKey]}
                    </logic:iterate>

                    <c:choose>
                        <c:when test="${fn:length(batchTaskMap) == 1 && batchTaskMap['HoldHumTask'] != null}">
                            <input type="button" id="holdId" value="批量归档" onclick="loadBatchPage(this)" class="btn"
                                   operateType="18" taskName="HoldHumTask">
                        </c:when>
                        <c:when test="${batchTaskMap['HoldHumTask'] == null}">
                            <input type="button" value="批量回复" onclick="loadBatchPage(this)" class="btn"
                                   operateType="46">
                        </c:when>
                        <c:when test="${fn:length(batchTaskMap) > 1 && batchTaskMap['HoldHumTask'] != null}">
                            <input type="button" id="holdId" value="批量归档" onclick="loadBatchPage(this)" class="btn"
                                   operateType="18" taskName="HoldHumTask">
                            <input type="button" value="批量回复" onclick="loadBatchPage(this)" class="btn"
                                   operateType="46">
                        </c:when>
                    </c:choose>

                </td>
            </tr>
        </display:footer>
    </logic:notEmpty>
    <%} %>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<%@ include file="/common/footer_eoms.jsp" %>