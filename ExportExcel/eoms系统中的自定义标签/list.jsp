<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<% String source = request.getParameter("6578706f7274");
    if (source == null) {
%>
<c:if test="${findForward != null && findForward == 'list'}">
    <jsp:include page="/WEB-INF/pages/wfworksheet/resourceserrata/listsendUndoJS.jsp"/>
</c:if>
<%} %>


<bean:define id="url" value="resourceserrata.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="true" requestURI="resourceserrata.do"
               sort="list" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.resourceserrata.webapp.action.ProcessListDisplaytagMy">

    <display:caption media="html">
        <span class="map alert">将要超时的工单</span>
        <span class="map serious">已经超时的工单</span>
    </display:caption>
    <display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
    <display:column property="sheetId" sortable="true"
                    headerClass="sortable" title="工单流水号"/>

    <display:column property="title" sortable="true"
                    headerClass="sortable" title="工单主题"/>

    <display:column property="sendTime" sortable="true"
                    headerClass="sortable" title="派单时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="completeTimeLimit" sortable="true"
                    headerClass="sortable" title="完成时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="taskDisplayName" sortable="true"
                    headerClass="sortable" title="处理环节"/>

    <display:column sortable="true" headerClass="sortable" title="任务状态">
        <c:choose>
            <c:when test="${taskList.taskDisplayName=='归档'}">
                待质检
            </c:when>
            <c:otherwise>
                <c:if test="${taskList.ifWaitForSubTask=='true'}">
                    <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
                </c:if>
                <c:if test="${empty taskList.ifWaitForSubTask||taskList.ifWaitForSubTask=='false'}">
                    <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskList.taskStatus}"
                               beanId="id2descriptionXML"/>
                </c:if>
            </c:otherwise>
        </c:choose>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="任务所有者">
        <eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao"/>
    </display:column>

    <display:column headerClass="sortable" sortable="true" title="上一级处理部门">
        <eoms:id2nameDB id="${taskList.preDealDept}" beanId="tawSystemDeptDao"/>
    </display:column>

    <display:column headerClass="sortable" sortable="true" title="上一级处理人员">
        <eoms:id2nameDB id="${taskList.preDealUserId}" beanId="tawSystemUserDao"/>
    </display:column>


    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<eoms:exportexcel
        sql="SELECT m.ID as ID, m.SHEETID as 工单号, m.TITLE as 标题, m.SHEETACCEPTLIMIT as 处理时限, m.SHEETCOMPLETELIMIT as 受理时限, m.SENDTIME as 派单时间, m.SENDUSERID as 派单人, m.SENDDEPTID as SENDDEPTID, m.SENDROLEID as SENDROLEID, m.SENDCONTACT as SENDCONTACT, m.ENDTIME as ENDTIME, m.ENDRESULT as ENDRESULT, m.STATUS as STATUS, u.id as 用户ID, u.deptname as deptname, u.email as email, u.userid as userid, u.username as username FROM eomsv35.commonfault_main m,eomsv35.taw_system_user u WHERE  m.sendtime>Sysdate-1200 AND m.senduserid=u.userid"/>
<%@ include file="/common/footer_eoms.jsp" %>