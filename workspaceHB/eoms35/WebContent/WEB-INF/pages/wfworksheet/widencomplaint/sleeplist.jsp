<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
</script>
<% String source = request.getParameter("6578706f7274");
 if (source == null) {
 %>
<c:if test="${findForward != null && findForward == 'list'}">
	<jsp:include page="/WEB-INF/pages/wfworksheet/common/listsendUndoJS.jsp"/>
</c:if>
<%} %>

<bean:define id="url" value="widencomplaint.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="listTable taskList"
		export="true" requestURI="widencomplaint.do"
		sort="external" size="total" partialList="true" >

	<c:if test="${!empty taskList && taskList.mainSleepStatus == '1' }">
		<display:column sortable="true" headerClass="sortable" title="工单流水号">
			 <a href='widencomplaint.do?method=showDetailPage&sheetKey=${taskList.id }&u=${taskList.mainT1Dealer }&a=${taskList.mainSleepUser }&taskStatus=4'target=_blank>${taskList.sheetId }</a>
		</display:column>
		
		<display:column property="title" sortable="true"   sortName="title"
			headerClass="sortable" title="工单主题" />
		<display:column property="sendTime" sortable="true" sortName="sendTime"
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetCompleteLimit" sortable="true" sortName="sheetCompleteLimit"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		<display:column sortable="true" headerClass="sortable" title="T1处理人" sortName="mainT1Dealer">
			<eoms:id2nameDB id="${taskList.mainT1Dealer}" beanId="tawSystemSubRoleDao" />
			<eoms:id2nameDB id="${taskList.mainT1Dealer}" beanId="tawSystemUserDao" />
		</display:column>

        <display:column sortable="true" headerClass="sortable" title="休眠申请人" sortName="mainSleepUser">
			<eoms:id2nameDB id="${taskList.mainSleepUser}" beanId="tawSystemUserDao" />			
		</display:column>
		
				<display:column sortable="true" headerClass="sortable" title="工单状态">
			申请休眠中
		</display:column>
		
	</c:if>
	<c:if test="${!empty taskList && taskList.mainSleepStatus == '2' }">
		<display:column sortable="true" headerClass="sortable" title="工单流水号">
			 <a href='widencomplaint.do?method=showDetailPage&sheetKey=${taskList.id }&u=${taskList.mainT1Dealer }&a=${taskList.mainSleepUser }&taskStatus=6'target=_blank>${taskList.sheetId }</a>
		</display:column>
		
		<display:column property="title" sortable="true"   sortName="title"
			headerClass="sortable" title="工单主题" />
		<display:column property="sendTime" sortable="true" sortName="sendTime"
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetCompleteLimit" sortable="true" sortName="sheetCompleteLimit"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		<display:column sortable="true" headerClass="sortable" title="T1处理人" sortName="mainT1Dealer">
			<eoms:id2nameDB id="${taskList.mainT1Dealer}" beanId="tawSystemSubRoleDao" />
			<eoms:id2nameDB id="${taskList.mainT1Dealer}" beanId="tawSystemUserDao" />
		</display:column>

        <display:column sortable="true" headerClass="sortable" title="休眠申请人" sortName="mainSleepUser">
			<eoms:id2nameDB id="${taskList.mainSleepUser}" beanId="tawSystemUserDao" />			
		</display:column>
				
		<display:column sortable="true" headerClass="sortable" title="工单状态">
			 休眠中
		</display:column>
		
	</c:if>
		
		

		
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>

<%@ include file="/common/footer_eoms.jsp"%>