<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
	String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-1);
	String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();;
 %>

<script type="text/javascript">
function gotoDetail3(workflowType,sheetid){
	var url="../supervisetask/supervisetask.do?method=supervisetaskBoardDetail3&sheetid="+sheetid+"&workflowType="+workflowType;
	window.open(url);
}

</script>




</div>


<display:table name="taskList" cellspacing="0" cellpadding="0" style="text-align:center;"
	id="taskList" pagesize="${pageSize}" class="listTable" export="false"
	size="${total}" partialList="true" requestURI="supervisetask.do">
	
	<display:column title="任务状态" style="text-align:center;" >
		<c:choose>
			<c:when test="${status==0 }">即将进入本级督办序列</c:when>
			<c:when test="${status==1 }">已进入本级督办序列</c:when>
			<c:when test="${status==2 }">已进入上级督办序列</c:when>
		</c:choose>
	</display:column>
	<display:column title="任务下发方式" style="text-align:center;">
		 <c:choose>
		 <c:when test="${taskList.workflowType=='commonfault' }">故障工单</c:when>
		 <c:when test="${taskList.workflowType=='listedregulation' }">摘挂牌工单</c:when>
		 </c:choose>
		<!-- 
		 -->
	</display:column>
	<display:column title="生成任务历时" style="text-align:center;">
		${taskList.costtime}
		<!--
		  -->
	</display:column>
	<display:column title="工单号" style="text-align:center;">
		<a href="####" onclick="gotoDetail3('${taskList.workflowType}','${taskList.sheetid }')">${taskList.sheetid }点击进入三级视图</a>
	</display:column>
	<display:column title="生成任务执行人" style="text-align:center;">
		<eoms:id2nameDB id="${taskList.senduserid }" beanId="tawSystemUserDao" />
		<!-- 
		 -->
	</display:column>
	<display:column title="联系方式" style="text-align:center;">
		 ${taskList.sendContact} 
		<!-- 
		 -->
	</display:column>
	
</display:table>

	

<%@ include file="/common/footer_eoms.jsp"%>
