<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<style type="text/css">
.small {
	width:10px;
}
</style>


<content tag="heading">
	订阅知识内容
</content>

<html:form action="/kmContentsSubscribes.do?method=searchSubscribe" method="post" styleId="kmContentsForm">
	<input type="hidden" name="status" id="status" />

	<display:table name="kmContentsList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}" class="table kmContentsList"
		export="false"
		requestURI="${app}/kmmanager/kmContentsSubscribes.do?method=searchSubscribe"
		>

		<display:column property="contentTitle" title="知识标题" sortable="true"
			paramId="id" paramProperty="id" headerClass="sortable" />


		<display:column property="contentKeys" title="知识关键字" sortable="true"
			paramId="id" paramProperty="id" headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" title="创建人">
			<eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${kmContentsList.contentId }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentss.do?method=detail';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url"><img src="${app}/images/icons/search.gif"/></a>		    
		</display:column> 
		
		<display:column title="收藏" headerClass="imageColumn">
		    <a href="javascript:if(confirm('确定要收藏该知识?')){
		                        var id = '${kmContentsList.contentId }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentsCollects.do?method=collect';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url}"><img src="${app}/images/icons/save.gif"/></a>		    
		</display:column>

		<display:setProperty name="paging.banner.item_name"  value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />
	</display:table>


</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
