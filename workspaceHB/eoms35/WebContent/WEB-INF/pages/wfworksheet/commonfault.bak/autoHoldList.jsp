<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
function add(){
	location.href = "./commonfaultauto.do?method=showCommonfaultAutoPage&ruleType=autoHold";
}
function openSheet(url){
	if(parent.frames['portal-north']){
		parent.frames['portal-north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
</script>

<bean:define id="url" value="commonfaultauto.do"/>
<input type="button" value="新增" onclick="add()" class="btn">
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
		export="false" requestURI="commonfaultauto.do"
		sort="list" size="total" partialList="true">
			
		<display:column  sortable="true" headerClass="sortable" title="网络告警ID" sortName="remark1">
			<a href="./commonfaultauto.do?method=edit&ruleType=autoHold&type=open&id=${taskList.id}">
				${taskList.remark1}
			</a>
		</display:column>
			
		<display:column  sortable="true" headerClass="sortable" title="措施" sortName="commonFaultDesc">
			${taskList.commonFaultDesc}
		</display:column>
			
		<display:column sortable="true" headerClass="sortable" title="归档描述" sortName="remark2">
			${taskList.remark2}
		</display:column>


		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
		<display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.excel" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>