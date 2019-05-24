<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/trainPlans.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="trainPlan.list.heading" />
</content>

	<display:table name="trainPlanList" cellspacing="0" cellpadding="0"
		id="trainPlanList" pagesize="${pageSize}" class="table trainPlanList"
		export="false"
		requestURI="${app}/kmmanager/trainPlans.do?method=search"
		sort="list" partialList="true" size="resultSize">
		
	<display:column property="trainName" sortable="true"
			headerClass="sortable" titleKey="trainPlan.trainName"  paramId="id" paramProperty="id"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="trainPlan.trainUser">
		<eoms:id2nameDB id="${trainPlanList.trainUser}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column  sortable="true" headerClass="sortable" titleKey="trainPlan.trainDept" >
			<eoms:id2nameDB id="${trainPlanList.trainDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column property="trainVender" sortable="true"
			headerClass="sortable" titleKey="trainPlan.trainVender"  paramId="id" paramProperty="id"/>
			
	<display:column property="trainTime" sortable="true"  
			headerClass="sortable" titleKey="trainPlan.trainTime"  paramId="id" paramProperty="id"/>
	
	<display:column property="trainAddress" sortable="true"
			headerClass="sortable" titleKey="trainPlan.trainAddress"  paramId="id" paramProperty="id"/>

	<display:column property="trainUnit" sortable="true"
			headerClass="sortable" titleKey="trainPlan.trainUnit"  paramId="id" paramProperty="id"/>

	<display:column  sortable="true" headerClass="sortable" titleKey="trainPlan.trainSpeciality" >
		<eoms:id2nameDB id="${trainPlanList.trainSpeciality}" beanId="trainSpecialtyDao" />
	</display:column>

	<display:column property="trainType" sortable="true"
			headerClass="sortable" titleKey="trainPlan.trainType"  paramId="id" paramProperty="id"/>

	<display:column property="trainBeginTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainPlan.trainBeginTime"  paramId="id" paramProperty="id"/>
		
	<display:column property="trainEndTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainPlan.trainEndTime"  paramId="id" paramProperty="id"/>
				
	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${trainPlanList.id }';
		                        var url='${app}/kmmanager/trainPlans.do?method=detail';
		                        url = url + '&id=' + id;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>		    
		</display:column> 
		<display:setProperty name="paging.banner.item_name" value="trainPlan" />
		<display:setProperty name="paging.banner.items_name" value="trainPlans" />
	</display:table>
	<!-- 只有管理员能添加培训计划 -->
	<c:if test="${trainRequireForm.trainUser == admin}">
		<c:out value="${buttons}" escapeXml="false" />
	</c:if>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>