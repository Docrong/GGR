<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/trainFeedbacks.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-trainfeedback">

<content tag="heading">
	<fmt:message key="trainFeedback.list.heading" />
</content>

	<display:table name="trainFeedbackList" cellspacing="0" cellpadding="0"
		id="trainFeedbackList" pagesize="${pageSize}" class="table trainFeedbackList"
		export="false"
		requestURI="${app}/trainFeedback/trainFeedbacks.do?method=search"
		sort="list" partialList="true" size="resultSize">
	<display:column sortable="true" headerClass="sortable" titleKey="trainFeedback.feedbackUser">
		<eoms:id2nameDB id="${trainFeedbackList.feedbackUser}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column  sortable="true" headerClass="sortable" titleKey="trainFeedback.trainFeedbackDept" >
			<eoms:id2nameDB id="${trainFeedbackList.trainFeedbackDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column  sortable="true"	headerClass="sortable" titleKey="trainFeedback.trainPlanId" >
		<eoms:id2nameDB id="${trainFeedbackList.trainPlanId}" beanId="trainPlanDao" />
	</display:column>
	
	<display:column property="trainFeedbackTel" sortable="true"
			headerClass="sortable" titleKey="trainFeedback.trainFeedbackTel"  paramId="id" paramProperty="id"/>

	<display:column property="trainFeedbackTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainFeedback.trainFeedbackTime"  paramId="id" paramProperty="id"/>

	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${trainFeedbackList.id }';
		                        var url='${app}/kmmanager/trainFeedbacks.do?method=edit';
		                        url = url + '&id=' + id;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>		    
	</display:column> 
	
		<display:setProperty name="paging.banner.item_name" value="trainFeedback" />
		<display:setProperty name="paging.banner.items_name" value="trainFeedbacks" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>