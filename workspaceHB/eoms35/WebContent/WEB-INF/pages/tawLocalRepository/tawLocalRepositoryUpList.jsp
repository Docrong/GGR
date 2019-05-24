<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>



<fmt:bundle basename="config/applicationResource-tawlocalrepository">

<content tag="heading">
	<fmt:message key="tawLocalRepositoryUp.list.heading" />
</content>

	<display:table name="tawLocalRepositoryUpList" cellspacing="0" cellpadding="0"
		id="tawLocalRepositoryUp" pagesize="${pageSize}" class="table tawLocalRepositoryUpList"
		export="false"
		requestURI=""  decorator = "com.boco.eoms.repository.util.TawLocalRepositoryIdToNet"
		sort="list" partialList="true" size="resultSize">

	<display:column property="repositoryId" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepositoryUp.net"/>

	<display:column property="upNum" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepositoryUp.upNum"/>
	
     <display:column  sortable="true" headerClass="sortable" titleKey="tawLocalRepositoryUp.beforeHardwareRepository" >
		<eoms:id2nameDB id="${tawLocalRepositoryUp.beforeHardwareRepository}" beanId="ItawSystemDictTypeDao"/>
	</display:column>

	<display:column  sortable="true"  headerClass="sortable" titleKey="tawLocalRepositoryUp.beforeSoftwareRepository"  >
			<eoms:id2nameDB id="${tawLocalRepositoryUp.beforeSoftwareRepository}" beanId="ItawSystemDictTypeDao"/>
	</display:column>
	<display:column sortable="true"
			headerClass="sortable" titleKey="tawLocalRepositoryUp.hardwareRepository" >
		<eoms:id2nameDB id="${tawLocalRepositoryUp.hardwareRepository}" beanId="ItawSystemDictTypeDao"/>
	</display:column>

	<display:column sortable="true"
			headerClass="sortable" titleKey="tawLocalRepositoryUp.softwareRepository" >
		<eoms:id2nameDB id="${tawLocalRepositoryUp.softwareRepository}" beanId="ItawSystemDictTypeDao"/>
	</display:column>

	<display:column property="patchs" sortable="true"
			headerClass="sortable" titleKey="tawLocalRepositoryUp.patch"  />

		<display:setProperty name="paging.banner.item_name" value="tawLocalRepositoryUp" />
		<display:setProperty name="paging.banner.items_name" value="tawLocalRepositoryUps" />
	</display:table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>