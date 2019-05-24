<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

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
	    location.href='${app}/kmmanager/deptContributeStatistics.do?method=search&startDate='+startDate+'&endDate='+endDate;
	}
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<b><fmt:message key="deptContributeStatistic.list.heading" /></b>
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

	<display:table name="deptContributeStatisticList" cellspacing="0" cellpadding="0"
		id="deptContributeStatisticList" pagesize="${pageSize}" class="table deptContributeStatisticList"
		export="false"
		requestURI="${app}/kmmanager/deptContributeStatistics.do?method=search"
		sort="list" partialList="true" size="resultSize">

		<display:column sortable="true" headerClass="sortable" titleKey="deptContributeStatistic.deptName">
			<eoms:id2nameDB id="${deptContributeStatisticList.deptName}" beanId="tawSystemDeptDao" />
		</display:column>

		<display:column property="addCount" sortable="true"
			headerClass="sortable" titleKey="deptContributeStatistic.addCount" paramId="id" paramProperty="id"/>

		<display:column property="auditOkPercentage" sortable="true"
			headerClass="sortable" titleKey="deptContributeStatistic.auditOkPercentage" paramId="id" paramProperty="id"/>

		<display:column property="utilizationRate" sortable="true"
			headerClass="sortable" titleKey="deptContributeStatistic.utilizationRate" paramId="id" paramProperty="id"/>

		<display:column property="useCount" sortable="true"
			headerClass="sortable" titleKey="deptContributeStatistic.useCount" paramId="id" paramProperty="id"/>

		<display:column property="upCount" sortable="true"
			headerClass="sortable" titleKey="deptContributeStatistic.upCount" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="deptContributeStatistic" />
		<display:setProperty name="paging.banner.items_name" value="deptContributeStatistics" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>