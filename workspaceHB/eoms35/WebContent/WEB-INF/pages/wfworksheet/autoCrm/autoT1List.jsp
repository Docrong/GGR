<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
function add(){
	location.href = "./autot1crm.do?method=showAutoT1Page&flowId=${flowId}";
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

<bean:define id="url" value="autot1crm.do" />
<input type="button" value="新增" onclick="add()" class="btn">
<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
	export="false" requestURI="autot1crm.do" sort="list" size="total"
	partialList="true">
	<display:column sortable="true" headerClass="sortable"
		title="地区" sortName="faultsite">
		<a href="./autot1crm.do?method=edit&flowId=${flowId }&type=open&id=${taskList.id}">
		
		<c:if test="${taskList.faultsite=='HB.WH'}">武汉</c:if>
		<c:if test="${taskList.faultsite=='HB.YC'}">宜昌</c:if>
		<c:if test="${taskList.faultsite=='HB.ES'}">恩施</c:if>
		<c:if test="${taskList.faultsite=='HB.JZ'}">荆州</c:if>
		<c:if test="${taskList.faultsite=='HB.JH'}">江汉</c:if>
		<c:if test="${taskList.faultsite=='HB.JM'}">荆门</c:if>
		<c:if test="${taskList.faultsite=='HB.HS'}">黄石</c:if>
		<c:if test="${taskList.faultsite=='HB.EZ'}">鄂州</c:if>
		<c:if test="${taskList.faultsite=='HB.XN'}">咸宁</c:if>
		<c:if test="${taskList.faultsite=='HB.HG'}">黄冈</c:if>
		<c:if test="${taskList.faultsite=='HB.XF'}">襄樊</c:if>
		<c:if test="${taskList.faultsite=='HB.SZ'}">随州</c:if>
		<c:if test="${taskList.faultsite=='HB.SY'}">十堰</c:if>
		<c:if test="${taskList.faultsite=='HB.XG'}">孝感</c:if>
		</a>
	</display:column>
	<display:column   sortable="true"
		headerClass="sortable" title="模板名称" sortName="templatename" >
		<c:if test="${taskList.templatename=='32' }">业务开通</c:if>			
		<c:if test="${taskList.templatename=='33' }">业务变更</c:if>	
		<c:if test="${taskList.templatename=='34' }">业务拆除</c:if>	
		<c:if test="${taskList.templatename=='31' }">资源确认</c:if>	
	</display:column>
	<display:column property="createdate" sortable="true"
		headerClass="sortable" title="创建时间" sortName="createdate" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.excel" value="false" />
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>
