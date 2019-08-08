<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<display:table name="taskListAgent" cellspacing="0" cellpadding="0"
               id="taskListAgent" pagesize="${pageSize}" class="listTable taskListAgent"
               export="true" requestURI="commonfault.do"
               sort="external" size="totalAgent" partialList="true"
               decorator="com.boco.eoms.sheet.commonfault.webapp.action.ProcessListCommonFaultDisplaytagDecoratorHelper">
    <% if (source == null) { %>
    <display:caption media="html">
        <span class="map alert colorrow">将要超时的工单</span>
        <span class="map serious">已经超时的工单</span>
        已调用代维流程
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
        <eoms:id2nameDB id="${taskListAgent.mainFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
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

        <c:if test="${taskListAgent.ifWaitForSubTask=='true'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
        </c:if>
        <c:if test="${empty taskListAgent.ifWaitForSubTask||taskListAgent.ifWaitForSubTask=='false'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskListAgent.taskStatus}"
                       beanId="id2descriptionXML"/>
        </c:if>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
        <eoms:id2nameDB id="${taskListAgent.taskOwner}" beanId="tawSystemUserDao"/>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>