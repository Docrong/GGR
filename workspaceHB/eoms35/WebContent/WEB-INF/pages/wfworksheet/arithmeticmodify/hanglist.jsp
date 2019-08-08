<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<% String source = request.getParameter("6578706f7274");
    if (source == null) {
%>
<%} %>
<bean:define id="url" value="inconstruct.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="true" requestURI="inconstruct.do"
               sort="list" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.inconstruct.webapp.action.ProcessDetailListDisplaytagDecoratorHelper">
    <display:column property="sheetId" sortName="main.sendTime" sortable="true"
                    headerClass="sortable" title="工单流水号"/>

    <display:column property="title" sortName="main.title" sortable="true"
                    headerClass="sortable" title="工单主题"/>

    <display:column property="sendTime" sortable="true"
                    headerClass="sortable" title="派单时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="taskDisplayName" sortable="true"
                    headerClass="sortable" title="处理环节"/>

    <display:column sortable="true" headerClass="sortable" title="工单状态">
        挂起
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp" %>
