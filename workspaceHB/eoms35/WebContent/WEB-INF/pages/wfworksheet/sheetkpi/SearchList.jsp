<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="java.util.*" %>
<%
    List list = (ArrayList) request.getAttribute("colMap");
%>
<script type="text/javascript">

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: "theform"});
    });
</script>
<form id="theform" method="post" action="kpiBaseAction.do?method=excelsearch">
    <input type="hidden" class="text" name="cnname" value="${cnname }">
    <input type="hidden" class="text" name="sendTimeEndDate" value="${ sendTimeEndDate }">
    <input type="hidden" class="text" name="sendTimeStartDate" value="${ sendTimeStartDate }">


    <input type="hidden" class="text" name="deptid" value="${dept}">

    <input type="hidden" class="text" id="sql" value="<%=list.get(0) %>">
    <a href=""></a>
    <display:table name="taskList" cellspacing="0" cellpadding="0"
                   id="taskList" pagesize="${pageSize}" class="table bureaudataHlrList"
                   export="true" requestURI="sheetKpiBaseAction.do" sort="list"
                   size="total" partialList="true">

        <display:column sortable="true" sortName="sheetid"
                        headerClass="sortable" title="工单流水号">
            <a href="${app}/sheet/commonfault/commonfault.do?method=showMainDetailPage&sheetKey=${taskList.ID}">
                    ${taskList.SHEETID }
            </a>
        </display:column>

        <display:column property="AREANAME" sortable="true" sortName="AREANAME"
                        headerClass="sortable" title="地区名称"/>
        <display:column property="TITLE" sortable="true" sortName="TITLE"
                        headerClass="sortable" title="工单主题"/>

        <display:column property="SENDTIME" sortable="true" sortName="SENDTIME"
                        headerClass="sortable" title="派单时间"/>

        <display:column property="DEALNAME" sortable="true" sortName="DEALNAME"
                        headerClass="sortable" title="处理环节"/>


        <display:setProperty name="export.pdf" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
    </display:table>
</form>
<%@ include file="/common/footer_eoms.jsp" %>

