<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>


<style type="text/css">
.small {
	width: 10px;
}
</style>


<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<display:table name="kmContentsList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}"
		class="table kmContentsList" export="false"
		requestURI="${app}/kmmanager/kmContentsMains.do?method=searchAll" 
		partialList="true" size="resultSize"
		decorator="com.boco.eoms.km.knowledge.displaytag.support.KmContentsDisplaytabDecorator">

		<display:column  titleKey="kmContents.themeId">
			<script>
				if("${kmContentsList.themeId}"==""||"${kmContentsList.themeId}"==null)
					document.write('<eoms:id2nameDB beanId="kmFileTreeDao" id="${kmContentsList.tableId}" />');
				else
					document.write('<eoms:id2nameDB id="${kmContentsList.tableId}" beanId="kmTableGeneralDao" />');
			</script>
		</display:column>
			

		<display:column  property="contentTitle" titleKey="kmContents.contentTitle" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />
			
		<display:column property="createTime" titleKey="kmContents.createTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"
		    paramId="id" paramProperty="id"
			headerClass="sortable" />
		
		<display:column  titleKey="kmContents.createUser">
			<eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao" />
		</display:column>
		
		<display:column  titleKey="kmContents.createDept">
			<eoms:id2nameDB id="${kmContentsList.createDept}" beanId="tawSystemDeptDao" />
		</display:column>
		
		<display:column title="查看" headerClass="imageColumn">
			<a href="javascript:var id = '${kmContentsList.id }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url;
		                        if(themeId==''||themeId==null)
		                        {
			                        url='${app}/kmmanager/files.do?method=detail&id='+id;
		                        }
		                        else
		                        {
		                        	url='${app}/kmmanager/kmContentss.do?method=detail';
			                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        }
		                        location.href=url">
				<img src="${app}/images/icons/search.gif" /> </a>
		</display:column>

		
		<display:setProperty name="paging.banner.item_name" value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />

	</display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
