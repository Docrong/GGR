<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>


<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/tawLocalRepositorys.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-tawlocalrepository">

<content tag="heading">
	<fmt:message key="tawLocalRepository.list.heading" />
</content>

	<display:table name="tawLocalRepositoryList" cellspacing="0" cellpadding="0"
		id="tawLocalRepositoryList" pagesize="${pageSize}" class="table tawLocalRepositoryList"
		export="false"
		requestURI=""
		sort="list" partialList="true" size="resultSize">

	<display:column property="province" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.province"paramId="id" paramProperty="id"/>
	
	<display:column sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.city">
		<eoms:id2nameDB id="${tawLocalRepositoryList.city}" beanId="ItawSystemDictTypeDao"/>		
	</display:column>

	<display:column property="net" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.net" href="${app}/repository/tawLocalRepositorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column   sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.netType" >
		<eoms:id2nameDB id="${tawLocalRepositoryList.netType}" beanId="ItawSystemDictTypeDao"/>		
	</display:column>
	<display:column   sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.driverTpye" >
		<eoms:id2nameDB id="${tawLocalRepositoryList.driverTpye}" beanId="ItawSystemDictTypeDao"/>		
	</display:column>

	<display:column   sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.company"  >
		<eoms:id2nameDB id="${tawLocalRepositoryList.company}" beanId="ItawSystemDictTypeDao"/>		
	</display:column>

	<display:column   sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.netModale"  >
		<eoms:id2nameDB id="${tawLocalRepositoryList.netModale}" beanId="ItawSystemDictTypeDao"/>		
	</display:column>

	<display:column sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.hardwareRepository"  >
		<eoms:id2nameDB id="${tawLocalRepositoryList.hardwareRepository}" beanId="ItawSystemDictTypeDao"/>		
	</display:column>

	<display:column sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.softwareRepository"  >
		<eoms:id2nameDB id="${tawLocalRepositoryList.softwareRepository}" beanId="ItawSystemDictTypeDao"/>		
	</display:column>

	<display:column property="patch" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.patch"  paramId="id" paramProperty="id"/>

	<display:column property="networkTime" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.networkTime"  paramId="id" paramProperty="id"/>
 
	<display:column property="stateName" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepository.state"  paramId="id" paramProperty="id"/>

   <display:column titleKey="tawLocalRepository.execute" style="width:60px">	
		<a  style="vertical-align: middle;" href="${app}/repository/tawLocalRepositorys.do?method=edit&id=${tawLocalRepositoryList.id}" title="${eoms:a2u('修改')}" ><img src="${app }/images/icons/edit.gif"></a>
    	|
 		<a  style="vertical-align: middle;" href="${app}/repository/tawLocalRepositoryUps.do?method=add&repositoryId=${tawLocalRepositoryList.id}" title="${eoms:a2u('升级')}" ><img src="${app }/images/icons/note.gif"></a>
    	
 	</display:column>
		<display:setProperty name="paging.banner.item_name" value="tawLocalRepository" />
		<display:setProperty name="paging.banner.items_name" value="tawLocalRepositorys" />
	</display:table>

 	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>