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
	    location.href='${app}/kmmanager/generalStatistics.do?method=search&startDate='+startDate+'&endDate='+endDate;
	}
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<b>知识按知识库统计</b>
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

	<display:table name="generalStatisticList" cellspacing="0" cellpadding="0"
		id="GeneralStatistic" pagesize="${pageSize}" class="table generalStatisticList"
		export="false" requestURI="${app}/kmmanager/generalStatistics.do?method=search"
		sort="list" partialList="true" size="${pageSize}" >
		

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.tableId">
			<eoms:id2nameDB id="${GeneralStatistic.tableId}" beanId="kmTableGeneralDao" />
		</display:column>


	<display:column property="isBest" sortable="true"
			headerClass="sortable" title="推荐数" paramId="id" paramProperty="id"/>

	<display:column property="isPublic" sortable="true"
			headerClass="sortable" title="公开数" paramId="id" paramProperty="id"/>

	<display:column property="gradeOne" sortable="true"
			headerClass="sortable" title="一星数" paramId="id" paramProperty="id"/>

	<display:column property="gradeTwo" sortable="true"
			headerClass="sortable" title="二星数" paramId="id" paramProperty="id"/>

	<display:column property="gradeThree" sortable="true"
			headerClass="sortable" title="三星数" paramId="id" paramProperty="id"/>

	<display:column property="gradeFour" sortable="true"
			headerClass="sortable" title="四星数" paramId="id" paramProperty="id"/>

	<display:column property="gradeFive" sortable="true"
			headerClass="sortable" title="五星数" paramId="id" paramProperty="id"/>

	<display:column property="readCount" sortable="true"
			headerClass="sortable" title="阅读数" paramId="id" paramProperty="id"/>

	<display:column property="useCount" sortable="true"
			headerClass="sortable" title="引用数" paramId="id" paramProperty="id"/>

	<display:column property="modifyCount" sortable="true"
			headerClass="sortable" title="修改数" paramId="id" paramProperty="id"/>

	</display:table>

<table cellpadding="0" cellspacing="0" width="98%">
<tr>
    <td width="100%" height="32" align="right">
      <html:link href="<%=excelUrl%>" scope="page" >导出EXCEL</html:link>
    </td>

</tr>
</table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>