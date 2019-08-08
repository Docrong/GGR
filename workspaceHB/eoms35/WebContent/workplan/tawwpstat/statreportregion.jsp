<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<%
    String str = (String) request.getAttribute("str");
%>

<table class="listTable">
    <caption>
        <%=request.getParameter("yearflag")%>
        <bean:message key="statreportregion.title.labHalfYear"/>
        <%=request.getParameter("monthflag")%>
        <bean:message key="statreportregion.title.labMonthStat"/>
    </caption>
    <thead>
    <tr>
        <td rowspan='2'><bean:message key="statreportall.title.labDeptName"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labDay"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labWeek"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labHalfMonth"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labMonth"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labQuarterYear"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labHalfYear"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labYear"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labTemp"/></td>
        <td colspan='3'><bean:message key="statreportall.title.labAllCount"/></td>
        <td rowspan='2'><bean:message key="statreportall.title.labInTimeRate"/></td>
        <td rowspan='2'><bean:message key="statreportall.title.labFinishRate"/></td>
        <td rowspan='2'><bean:message key="statreportall.title.labConstituteCount"/></td>
        <td rowspan='2'><bean:message key="statreportall.title.labScore"/></td>
    </tr>
    <tr>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td><bean:message key="statreportall.title.labAllCounts"/></td>
        <td><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td><bean:message key="statreportall.title.labFinishCounts"/></td>
    </tr>
    </thead>
    <%=str%>
</table>
<%@ include file="/common/footer_eoms.jsp" %>