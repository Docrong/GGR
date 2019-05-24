<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/trainEnters.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-trainenter">

<content tag="heading">
	<fmt:message key="trainEnter.list.heading" />
</content>

	<display:table name="trainEnterList" cellspacing="0" cellpadding="0"
		id="trainEnterList" pagesize="${pageSize}" class="table trainEnterList"
		export="false"
		requestURI="${app}/kmmanager/trainEnters.do?method=search"
		sort="list" partialList="true" size="resultSize">
	
	<display:column sortable="true" headerClass="sortable" titleKey="trainEnter.enterName">
		<eoms:id2nameDB id="${trainEnterList.enterName}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column  sortable="true" headerClass="sortable" titleKey="trainEnter.trainEnterDept" >
			<eoms:id2nameDB id="${trainEnterList.trainEnterDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	
	<display:column  sortable="true"	headerClass="sortable" titleKey="trainEnter.trainPlanId" >
		<eoms:id2nameDB id="${trainEnterList.trainPlanId}" beanId="trainPlanDao" />
	</display:column>
	
	<display:column property="trainEnterTel" sortable="true"
			headerClass="sortable" titleKey="trainEnter.trainEnterTel"  paramId="id" paramProperty="id"/>

	<display:column property="trainEnterTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainEnter.trainEnterTime"  paramId="id" paramProperty="id"/>
	
	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${trainEnterList.id }';
		                        var url='${app}/kmmanager/trainEnters.do?method=edit';
		                        url = url + '&id=' + id;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>		    
	</display:column> 
	
		<display:setProperty name="paging.banner.item_name" value="trainEnter" />
		<display:setProperty name="paging.banner.items_name" value="trainEnters" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>