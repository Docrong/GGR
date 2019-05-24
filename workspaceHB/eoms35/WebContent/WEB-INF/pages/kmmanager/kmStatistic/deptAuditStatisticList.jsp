<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%
String excelUrl = StaticMethod.nullObject2String(request.getAttribute("excelUrl"));
%>

<script language = 'javascript'>
	function period(){
	    var startDate = document.getElementById("startDate").value;
	    var endDate = document.getElementById("endDate").value;
	    if(startDate == ""){
	        alert("请选择开始时间！");
	        return false;	    
	    }	    
	    if(startDate > endDate){
	        alert("开始时间要小于结束时间！");
	        return false;
	    }
	    location.href='${app}/kmmanager/personalAuditStatistics.do?method=deptSearch&startDate='+startDate+'&endDate='+endDate;
	}
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<b><fmt:message key="personalAuditStatistic.list.deptHeading" /></b>
</content>

	<br><br>

	<tr align = 'center'>	
        <td>
            统计时间：
            从&nbsp;&nbsp;<input type="text" name="startDate" id="startDate" value="${startDate}" size="10" onclick="popUpCalendar(this,this,null,null,null,false,-1);" readonly="true" class="text" alt="allowBlank:false,vtext:'请选择开始时间...'" />&nbsp;&nbsp;
            到&nbsp;&nbsp;<input type="text" name="endDate"   id="endDate"   value="${endDate}"   size="10" onclick="popUpCalendar(this,this,null,null,null,false,-1);" readonly="true" class="text" alt="allowBlank:false,vtext:'请选择结束时间...'" />&nbsp;&nbsp;
          <a style="CURSOR:hand" onclick="period()">统计</a>&nbsp;&nbsp;
	    </td>		
	</tr>

	<display:table name="personalAuditStatisticList" cellspacing="0" cellpadding="0"
		id="personalAuditStatisticList" pagesize="${pageSize}" class="table personalAuditStatisticList"
		export="false"
		requestURI="${app}/kmmanager/personalAuditStatistics.do?method=search&period=${period}"
		sort="list" partialList="true" size="resultSize">
		
		<display:column sortable="true" headerClass="sortable" titleKey="personalAuditStatistic.userDept">
			<eoms:id2nameDB id="${personalAuditStatisticList.userDept}" beanId="tawSystemDeptDao" />
		</display:column>

		<display:column property="auditCount" sortable="true"
			headerClass="sortable" titleKey="personalAuditStatistic.auditCount" paramId="id" paramProperty="id"/>

		<display:column property="auditOkCount" sortable="true"
			headerClass="sortable" titleKey="personalAuditStatistic.auditOkCount" paramId="id" paramProperty="id"/>

		<display:column property="auditBackCount" sortable="true"
			headerClass="sortable" titleKey="personalAuditStatistic.auditBackCount" paramId="id" paramProperty="id"/>

		<display:column property="auditOkRatio" sortable="true"
			headerClass="sortable" titleKey="personalAuditStatistic.auditOkRatio" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="personalAuditStatistic" />
		<display:setProperty name="paging.banner.items_name" value="personalAuditStatistics" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<table cellpadding="0" cellspacing="0" width="98%">
<tr>
    <td width="100%" height="32" align="right">
      <html:link href="<%=excelUrl%>" scope="page" >导出EXCEL</html:link>
    </td>

</tr>
</table>
<%@ include file="/common/footer_eoms.jsp"%>