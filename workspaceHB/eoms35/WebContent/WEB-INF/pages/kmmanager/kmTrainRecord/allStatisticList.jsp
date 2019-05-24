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
	    location.href='${app}/kmmanager/trainRecords.do?method=searchAll&startDate='+startDate+'&endDate='+endDate;
	}
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<b>个人培训记录统计</b>
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

	<display:table name="trainRecordList" cellspacing="0" cellpadding="0"
		id="trainRecordList" pagesize="${pageSize}" class="table trainRecordList"
		export="false"
		requestURI="${app}/kmmanager/trainRecords.do?method=searchAll"
		sort="list" partialList="true" size="resultSize">

	<display:column property="trainName" sortable="true"
			headerClass="sortable" titleKey="trainAllStatistic.trainName"  paramId="id" paramProperty="id"/>

	<display:column property="trainCount" sortable="true"
			headerClass="sortable" titleKey="trainAllStatistic.trainCount"  paramId="id" paramProperty="id"/>

	<display:column property="timeCount" sortable="true"
			headerClass="sortable" titleKey="trainAllStatistic.timeCount"  paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="trainRecord" />
		<display:setProperty name="paging.banner.items_name" value="trainRecords" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>