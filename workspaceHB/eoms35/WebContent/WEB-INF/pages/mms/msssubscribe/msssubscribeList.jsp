<%@ page language="java" import="java.util.*,java.util.List" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript">
function openSheet(url){
	
	//alert(url);
	
	if(doConfirm("你确定要删除吗？"))
	{
		if(parent.frames['north'])
		{
			parent.frames['north'].location.href = url;
		}
		else
		{
			location.href = url;
		}
	}
	else
	{
		return flase; 
	}
}

	function doConfirm(str)
	{ 
		if(confirm(str))
		{
			return true; 
		} 
		else
		{ 
			return flase; 
		} 
	}
	
</script>
<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/msssubscribes.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<bean:define id="url" value="msssubscribes.do" />
<fmt:bundle basename="config/applicationResources-mms">

<content tag="heading">
	<fmt:message key="msssubscribe.list.heading" />
</content>

	<display:table name="msssubscribeList" cellspacing="0" cellpadding="0"
		id="msssubscribeList" pagesize="${pageSize}" class="table msssubscribeList"
		export="false"
		requestURI="msssubscribes.do"
		sort="list" partialList="true" size="resultSize"
		decorator="com.boco.eoms.commons.mms.msssubscribe.util.MsssubscribeDisplaytagDecoratorHelper">

	<display:column property="mmreportName" sortable="true"
			headerClass="sortable" titleKey="msssubscribe.mmreportName"/>

	<display:column property="receivePerson" sortable="true"
			headerClass="sortable" titleKey="msssubscribe.receivePerson" />
	
	<display:column property="createPerson" sortable="true"
			headerClass="sortable" titleKey="msssubscribe.createPerson" />

	<display:column property="receiveTime" sortable="true"
			headerClass="sortable" titleKey="msssubscribe.receiveTime"/>
			
	<display:column property="showDetail" sortable="true"
		headerClass="sortable" titleKey="msssubscribe.showDetail" />
					
	<display:column property="showModify" sortable="true"
		headerClass="sortable" titleKey="msssubscribe.showModify" />
		
	<display:column property="showDelete" sortable="true"
		headerClass="sortable" titleKey="msssubscribe.showDelete" />

	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>