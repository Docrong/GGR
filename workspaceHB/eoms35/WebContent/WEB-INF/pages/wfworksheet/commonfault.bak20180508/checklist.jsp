<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:include page="/WEB-INF/pages/wfworksheet/commonfault/batchJs.jsp"/>
<jsp:include page="/WEB-INF/pages/wfworksheet/commonfault/checkListsendUndoJS.jsp"/>
<bean:define id="url" value="commonfault.do"/>
<input type="hidden" class="text max" id="sheetKeys" name="sheetKeys" value="">
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table taskList"
               export="true" requestURI="commonfault.do"
               sort="external" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.base.webapp.action.AdminListDisplaytagDecoratorHelper">
    <display:column sortable="true" headerClass="sortable" title="选择">
        <input type="checkbox" id="id" name="id" value="${taskList.id}" onclick="getId('${taskList.id}')">
    </display:column>
    <display:column property="sheetId" sortName="main.sheetId" sortable="true"
                    headerClass="sortable" title="工单流水号"/>

    <display:column property="title" sortName="main.title" sortable="true"
                    headerClass="sortable" title="工单主题"/>

    <display:column property="sheetAcceptLimit" sortName="main.sheetAcceptLimit" sortable="true"
                    headerClass="sortable" title="受理时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="sheetCompleteLimit" sortName="main.sheetCompleteLimit" sortable="true"
                    headerClass="sortable" title="处理时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column sortable="true" headerClass="sortable" title="工单状态" sortName="main.status">
        <eoms:dict key="dict-sheet-common" dictId="sheetStatus" itemId="${taskList.status}" beanId="id2descriptionXML"/>
    </display:column>


    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<%@ include file="/common/footer_eoms.jsp" %>