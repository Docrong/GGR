<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<html>

<body>
<display:table name="taskList" cellspacing="0" cellpadding="0" id="taskList" class="table"
	export="false" requestURI="widencomplaint.do" size="${resultSize}" sort="list" pagesize="${pageSize}" partialList="true">

 
<display:column   sortable="true" 
			headerClass="sortable" title="工单号">
			<a href="${app}/sheet/widencomplaint/widencomplaint.do?method=showMainDetailPage&sheetId=${taskList.sheetid }&sheetKey=${taskList.id }">${taskList.SHEETID }</a>
			</display:column> 
<display:column property="complainttime" sortable="true" 
			headerClass="sortable" title="投诉时间"/> 
			<display:column property="customphone" sortable="true" 
			headerClass="sortable" title="投诉号码"/> 
			<display:column property="complaintDesc" sortable="true" 
			headerClass="sortable" title="投诉详细内容"/> 
			<display:column property="dealdesc" sortable="true" 
			headerClass="sortable" title="处理详情 "/> 


</display:table>
</body>
</html>