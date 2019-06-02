<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmFile.title" />
	<eoms:id2nameDB id="${KM_FILETREE_NODEID}" beanId="kmFileTreeDao" />
</content>

	<display:table name="fileList" cellspacing="0" cellpadding="0"
		id="fileList" pagesize="${pageSize}" class="table fileList"
		export="false"
		requestURI="${app}/kmmanager/files.do?method=search&nodeId=${KM_FILETREE_NODEID}"
		sort="list" partialList="true" size="resultSize">

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFile.fileName" paramId="id" paramProperty="id">
	<a href="${app}/kmmanager/files.do?method=detail&id=${fileList.id}&nodeId=${KM_FILETREE_NODEID}">${fileList.fileName}</a>
	</display:column>
	
	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFileTree.nodeName" paramId="id" paramProperty="id">
		<eoms:id2nameDB beanId="kmFileTreeDao" id="${fileList.nodeId}"/>
	</display:column>

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFile.userId" paramId="id" paramProperty="id">
		<eoms:id2nameDB beanId="tawSystemUserDao" id="${fileList.userId}"/>
	</display:column>

	<display:column property="uploadTime" sortable="true"
			headerClass="sortable" titleKey="kmFile.uploadTime" paramId="id" paramProperty="id"/>

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFile.fileSize" paramId="id" paramProperty="id">
	${fileList.fileSize}&nbsp;KB
	</display:column>

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFile.fileGrade" paramId="id" paramProperty="id">
		<eoms:dict key="dict-kmmanager" dictId="fileGrade" itemId="${fileList.fileGrade}" beanId="id2nameXML" />
	</display:column>

	<display:column property="clickCount" sortable="true"
			headerClass="sortable" titleKey="kmFile.clickCount" paramId="id" paramProperty="id"/>
	
	
		<display:setProperty name="paging.banner.item_name" value="file" />
		<display:setProperty name="paging.banner.items_name" value="files" />
	</display:table>
<!-- 
	<c:out value="${buttons}" escapeXml="false" />
 -->
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>