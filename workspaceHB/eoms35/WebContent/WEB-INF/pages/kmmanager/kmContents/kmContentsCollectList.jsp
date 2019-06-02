<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<style type="text/css">
.small {
	width: 10px;
}
</style>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <caption>
		<div class="header center"><b><fmt:message key="kmContents.form.heading"/>&nbsp;收藏</b></div>
	</caption>

	<display:table name="kmContentsCollectList" cellspacing="0"
		cellpadding="0" id="kmContentsList" pagesize="${pageSize}"
		class="table kmContentsCollectList" export="false"
		requestURI="${app}/kmmanager/kmContentsCollects.do">

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.themeId">
			<eoms:id2nameDB id="${kmContentsList.themeId}" beanId="kmTableThemeDao" />
		</display:column>
		
		<display:column sortable="true" property="contentTitle" titleKey="kmContents.contentTitle" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.createUser">
			<eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column sortable="false" property="contentKeys" titleKey="kmContents.contentKeys" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column title="查看" headerClass="imageColumn">
			<a href="javascript:var id = '${kmContentsList.contentId }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentss.do?method=detail';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url">
				<img src="${app}/images/icons/search.gif" />
			</a>
		</display:column>
	
		<display:column title="删除" headerClass="imageColumn">
			<a href="javascript:if(confirm('确定要删除该收藏?')){
		                        var id = '${kmContentsList.contentId }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentsCollects.do?method=remove';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url}">
		                        
				<img src="${app}/images/icons/list-delete.gif" />
			</a>
		</display:column>

		<display:setProperty name="paging.banner.item_name"  value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />

	</display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
