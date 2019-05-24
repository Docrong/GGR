<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
	
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<table class="formTable">
<caption>
		<div class="header center"><fmt:message key="kmContents.draft" />
		</div>
</caption>
</table>
    <display:table name="kmContentsDraftList" cellspacing="0" cellpadding="0"
		id="kmContentsDraftList" pagesize="${pageSize}" class="table kmContentsDraftList"
		export="false"
		requestURI="${app}/kmmanager/kmContentss.do?method=kmDraft"
		sort="list" partialList="true" size="resultSize">
		
		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.themeId">
			<eoms:id2nameDB id="${kmContentsDraftList.themeId}" beanId="kmTableThemeDao" />
		</display:column>

		<display:column sortable="true" property="contentTitle" titleKey="kmContents.contentTitle"
			paramId="id" paramProperty="id" headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.createUser">
			<eoms:id2nameDB id="${kmContentsDraftList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.createDept">
			<eoms:id2nameDB id="${createDept}" beanId="tawSystemDeptDao" />
		</display:column>

		<display:column sortable="true" property="createTime" titleKey="kmContents.createTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"
			paramId="id" paramProperty="id" headerClass="sortable" />

		<display:column sortable="false" property="contentKeys" titleKey="kmContents.contentKeys"
			paramId="id" paramProperty="id" headerClass="sortable" />

		<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${kmContentsDraftList.contentId }';
		                        var tableId = '${kmContentsDraftList.tableId}';
		                        var themeId = '${kmContentsDraftList.themeId}';
		                        var url='${app}/kmmanager/kmContentss.do?method=detailDraft';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>		    
		</display:column> 
		</display:table>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>