<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function addAlarm() {
        var url = "${app}/sheet/commonfault/commonfaultpack.do?method=showNewSheetPage&mainId=${mainId}";
        window.location = url;
    }

    function openSheet(url, height) {
        window.showModalDialog(url, window, "dialogHeight:" + height + "px;dialogWidth:1020px;center:YES;help:NO;resizable:NO;status:YES;");
        window.location.href = "${app}/sheet/commonfault/commonfaultpack.do?method=showOwnStarterList&mainId=${mainId}&alarmMethod=${alarmMethod}&taskName=${taskName}";
        //window.location.href.reload();
    }
</script>
<bean:define id="alarmMethod" value="${alarmMethod}"/>
<bean:define id="taskName" value="${taskName}"/>
<bean:define id="url" value="../commonfault/commonfaultpack.do"/>
<display:table name="mainList" cellspacing="0" cellpadding="0"
               id="mainList" pagesize="${pageSize}" class="table seResourceAssessMain"
               export="true" requestURI="../commonfault/commonfaultpack.do" sort="list" size="total"
               partialList="true"
               decorator="com.boco.eoms.sheet.commonfaultpack.webapp.action.faultpackListDisplaytagDecoratorHelper">
    <display:column property="title" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('鍛婅鏍囬')}"/>
    <display:column property="mainNetName" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('缃戝厓鍚嶇О')}"/>
    <display:column property="mainFaultResponseLevel" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('鏁呴殰绾у埆')}"/>
    <display:column property="mainEquipmentModel" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('鏁呴殰璁惧鍨嬪彿')}"/>
    <display:column property="mainFaultGenerantTime" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('鏁呴殰浜х敓鏃堕棿')}"/>
    <display:column property="mainAlarmLogicSort" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('鍛婅閫昏緫鍒嗙被')}"/>
    <display:column property="mainAlarmLogicSort" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('鍛婅閫昏緫瀛愮被')}"/>
    <display:column property="mainAlarmSolveDate" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('告警消除时间')}"/>
    <display:column property="mainAlarmId" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('鎿嶄綔')}"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<%
    if ("new".equals(alarmMethod) || "DraftHumTask".equals(taskName)) { %>
<input type="button" class="btn" value="${eoms:a2u('????')}" name="button" onclick="addAlarm();">
<%} %>

<%@ include file="/common/footer_eoms.jsp" %>
