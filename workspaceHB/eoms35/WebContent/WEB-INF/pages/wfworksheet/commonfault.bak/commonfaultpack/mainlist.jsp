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
                    headerClass="sortable" title="${eoms:a2u('告警标题')}"/>
    <display:column property="mainNetName" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('网元名称')}"/>
    <display:column property="mainFaultResponseLevel" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('故障级别')}"/>
    <display:column property="mainEquipmentModel" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('故障设备型号')}"/>
    <display:column property="mainFaultGenerantTime" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('故障产生时间')}"/>
    <display:column property="mainAlarmLogicSort" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('告警逻辑分类')}"/>
    <display:column property="mainAlarmLogicSort" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('告警逻辑子类')}"/>
    <display:column property="mainAlarmId" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('操作')}"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<%
    if ("new".equals(alarmMethod) || "DraftHumTask".equals(taskName)) { %>
<input type="button" class="btn" value="${eoms:a2u('添加告警')}" name="button" onclick="addAlarm();">
<%} %>

<%@ include file="/common/footer_eoms.jsp" %>
