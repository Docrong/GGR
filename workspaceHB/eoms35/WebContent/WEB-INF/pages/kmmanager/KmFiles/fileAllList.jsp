<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<%
String operateType = StaticMethod.nullObject2String(request.getAttribute("operateType"));
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String operateUserId = sessionform.getUserid();
%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<content tag="heading">
	    <b>本人上传的文件</b>
	</content>

	<display:table name="fileList" cellspacing="0" cellpadding="0"
		id="fileList" pagesize="${pageSize}" class="table fileList"
		export="false"
		requestURI="${app}/kmmanager/files.do?method=searchAllUpload"
		sort="list" partialList="true" size="resultSize">

		<display:column sortable="true" headerClass="sortable" titleKey="kmFile.fileName" 
		    paramId="id" paramProperty="id">
			<a href="${app}/kmmanager/files.do?method=detail&id=${fileList.id}&nodeId=${KM_FILETREE_NODEID}&operateType=<%=operateType%>">${fileList.fileName}</a>
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmFileTree.nodeName" 
		    paramId="id" paramProperty="id">
			<eoms:id2nameDB beanId="kmFileTreeDao" id="${fileList.nodeId}" />
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmFile.uploadTime" 
			property="uploadTime" paramId="id" paramProperty="id" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmFile.fileSize" 
		    paramId="id" paramProperty="id">
		    ${fileList.fileSize}&nbsp;KB
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmFile.fileGrade" 
		    paramId="id" paramProperty="id">
			<eoms:dict key="dict-kmmanager" dictId="fileGrade" itemId="${fileList.fileGrade}" beanId="id2nameXML" />
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmFile.clickCount" 
		    property="clickCount" paramId="id" paramProperty="id" />
	
		<display:setProperty name="paging.banner.item_name" value="file" />
		<display:setProperty name="paging.banner.items_name" value="files" />
	</display:table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>