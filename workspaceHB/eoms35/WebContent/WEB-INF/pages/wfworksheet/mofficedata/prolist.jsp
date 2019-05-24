﻿<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>


<% String source = request.getParameter("6578706f7274");
 if (source == null) {
  %>
<c:if test="${findForward != null && findForward == 'list'}">
	<jsp:include page="/WEB-INF/pages/wfworksheet/mofficedata/listsendUndoJS.jsp"/>
</c:if>
<%} %>
       

<bean:define id="url" value="mofficedata.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="listTable taskList"
		export="false" requestURI="mofficedata.do"
		sort="list" size="total" partialList="true"
		decorator="">
	<c:if test="${total>0}">
	<c:if test="${not empty taskList.buissType }">
   		<display:column headerClass="sortable" sortable="true" title="业务类型" >
			<eoms:id2nameDB id="${taskList.buissType}" beanId="IMofficeDataBuisTypeDAO"/>
			
		</display:column>
		</c:if>
		</c:if>
	<c:if test="${total>0}">
	<c:if test="${not empty taskList.buissType }">
   		<display:column headerClass="sortable" sortable="true" title="专业" >
			<eoms:id2nameDB id="${taskList.majorType}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
		</c:if>
		</c:if>
		<display:column property="producerName" sortable="true" headerClass="sortable" title="制作人" />
		<display:column property="netName" sortable="true" headerClass="sortable" title="网元类型" />
			<c:if test="${total>0}">
	<c:if test="${not empty taskList.deviceName }">
		<display:column property="deviceName" sortable="true" headerClass="sortable" title="设备厂家" />
		</c:if>
</c:if>	
	<c:if test="${total>0}">
	<c:if test="${not empty taskList.buissType }">
		<display:column headerClass="sortable" sortable="true" title="附件" >
			<iframe id="UIFrame1-accessories"
				name="UIFrame1-accessories" class="uploadframe" frameborder="0"
				scrolling="auto"
				src="${app}/accessories/pages/view.jsp?appId=mofficedata&filelist=${taskList.accessories}"
				style="height:80%;width:100%"></iframe>
		</display:column>
</c:if>
</c:if>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>