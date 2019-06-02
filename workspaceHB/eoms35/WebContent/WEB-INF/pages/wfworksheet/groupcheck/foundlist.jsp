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

<bean:define id="url" value="groupcheck.do" />

	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table taskList"
		export="true" requestURI="groupcheck.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.groupcheck.webapp.action.GroupListDisplaytagDecoratorHelper">
       <display:column property="dictname" sortable="true"
			headerClass="sortable" title="地市" />
			
		<display:column property="feizi" sortable="true"
			headerClass="sortable" title="分子" />
			
		<display:column property="feimu" sortable="true"
			headerClass="sortable" title="分母" />
			
		<display:column property="foundNum" sortable="true"
			headerClass="sortable" title="自动发现率" />
			
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>