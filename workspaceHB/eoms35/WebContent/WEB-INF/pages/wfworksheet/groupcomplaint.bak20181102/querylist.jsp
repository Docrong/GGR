<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
function openSheet(url){
	if(parent.frames['portal-north']){
		parent.frames['portal-north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
</script>
<logic:notPresent name="recordTotal">
<bean:define id="url" value="groupcomplaint.do" />
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
		export="true" requestURI="groupcomplaint.do"
		sort="external" size="total" partialList="true"
		decorator="">

		<display:column property="title" sortable="true"  sortName="title"
			headerClass="sortable" title="工单主题" />

			
		<display:column sortable="true"  sortName="sheetId"
			headerClass="sortable" title="工单流水号">
			<a href= "${app}/sheet/groupcomplaint/groupcomplaint.do?method=showMainDetailPage&sheetKey=${taskList.id}" target=_blank>${taskList.sheetId}</a>
		</display:column>	

		<display:column property="sheetAcceptLimit" sortable="true"  sortName="sheetAcceptLimit"
			headerClass="sortable" title="受理时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetCompleteLimit" sortable="true"  sortName="sheetCompleteLimit"
			headerClass="sortable" title="处理时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		
		<display:column property="taskdisplayname" sortable="true"  sortName="title"
			headerClass="sortable" title="当期环节" />
		
		<display:column property="subrolename" sortable="true"  sortName="title"
			headerClass="sortable" title="当前处理人" />
			
		<display:column property="ls" sortable="true"  sortName="title"
			headerClass="sortable" title="处理用时(小时)" />
		
		<display:column property="overtime" sortable="true"  sortName="title"
			headerClass="sortable" title="是否超时" />
			
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>

	</display:table>
</logic:notPresent>
<logic:present name="recordTotal">
	<center>
	<bean:message bundle="sheet" key="worksheet.query.total" />${recordTotal}<bean:message bundle="sheet" key="worksheet.query.totalNumber" />
	</center>
</logic:present>
<%@ include file="/common/footer_eoms.jsp"%>
		