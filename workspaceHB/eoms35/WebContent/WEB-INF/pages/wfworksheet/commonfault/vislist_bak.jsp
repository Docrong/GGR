<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">

</script>

<bean:define id="url" value="commonfault.do" />
<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="taskList" pagesize="${pageSize}" class="listTable taskList"
	export="false" requestURI="commonfault.do" sort="list"
	size="total" partialList="true"
	decorator="com.boco.eoms.sheet.commonfault.webapp.action.BatchCombineSheetDisplaytagDecoratorHelper">

	<display:column property="sheetId" sortable="true" headerClass="sortable"  
		title="工单流水号" />
	<display:column property="title" sortable="true" headerClass="sortable"
		title="工单主题" />
	<display:column property="sendTime" sortable="true" headerClass="sortable" 
		title="派单时间" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
	<display:column property="mainNetName" sortable="true" headerClass="sortable"
		title="网元名称" />
	<display:column  sortable="true" sortName="mainNetSortOne"
		headerClass="sortable" title="网络一级分类">
		<eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
	</display:column>
	<display:column  sortable="true" sortName="mainNetSortTwo"
		headerClass="sortable" title="网络二级分类">
		<eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
	</display:column>
	<display:column  sortable="true" sortName="mainNetSortThree"
		headerClass="sortable" title="网络三级分类">
		<eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
	</display:column>		
</display:table>

<%@ include file="/common/footer_eoms.jsp"%>
