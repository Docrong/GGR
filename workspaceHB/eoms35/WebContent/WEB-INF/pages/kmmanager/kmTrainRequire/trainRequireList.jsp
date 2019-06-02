<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/trainRequires.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">


	<display:table name="trainRequireList" cellspacing="0" cellpadding="0"
		id="trainRequireList" pagesize="${pageSize}" class="table trainRequireList"
		export="false"
		requestURI="${app}/kmmanager/trainRequires.do?method=search"
		sort="list" partialList="true" size="resultSize">
	
	<display:column property="trainNo" sortable="true" title="需求编号"
			headerClass="sortable"   paramId="id" paramProperty="id"/>
	
	<display:column property="trainQuestion" sortable="true"
			headerClass="sortable" titleKey="trainRequire.trainQuestion"  paramId="id" paramProperty="id"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="trainRequire.trainUser">
		<eoms:id2nameDB id="${trainRequireList.trainUser}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column  sortable="true" headerClass="sortable" titleKey="trainRequire.trainDept" >
			<eoms:id2nameDB id="${trainRequireList.trainDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column property="trainVender" sortable="true"
			headerClass="sortable" titleKey="trainRequire.trainVender"  paramId="id" paramProperty="id"/>

	<display:column  sortable="true" headerClass="sortable" titleKey="trainRequire.trainSpeciality" >
		<eoms:id2nameDB id="${trainRequireList.trainSpeciality}" beanId="trainSpecialtyDao" />
	</display:column>

	<display:column property="trainType" sortable="true" title="设备类型"
			headerClass="sortable"   paramId="id" paramProperty="id"/>
	
	<display:column property="trainDate" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainRequire.trainDate"  paramId="id" paramProperty="id"/>
	
	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${trainRequireList.id}';
		                        var url='${app}/kmmanager/trainRequires.do?method=detail';
		                        url = url + '&id=' + id ;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>		    
	</display:column> 
		
		<display:setProperty name="paging.banner.item_name" value="trainRequire" />
		<display:setProperty name="paging.banner.items_name" value="trainRequires" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>