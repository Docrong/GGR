<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<script language='javascript'>
    function onMonth() {
        document.statyear.submit();
    }
</script>
<%
    int yearFlag = StaticMethod.null2int((String) request.getAttribute("yearflag"));
    int monthFlag = StaticMethod.null2int((String) request.getAttribute("monthflag"));
    String str = (String) request.getAttribute("str");
%>

<table class="formTable">

    <tr>
        <td rowspan='2' style="{background-color:#EDF5FD;}">网元</td>
        <td rowspan='2' style="{background-color:#EDF5FD;}">作业计划</td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labDay"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labWeek"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labHalfMonth"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labMonth"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message
                key="statreportall.title.labQuarterYear"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labHalfYear"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labYear"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labTemp"/></td>
        <td colspan='3' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCount"/></td>
        <td rowspan='2' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeRate"/></td>
        <td rowspan='2' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishRate"/></td>
        <%--<td rowspan='2' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labConstituteCount" /></td>
        <td rowspan='2' style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labScore" /></td>
        --%></tr>
    <tr>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labAllCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labInTimeCounts"/></td>
        <td style="{background-color:#EDF5FD;}"><bean:message key="statreportall.title.labFinishCounts"/></td>
    </tr>

    <%=str%>
</table>
<br>

<%@ include file="/common/footer_eoms.jsp" %>