<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript">
Ext.onReady(function() {
	
	
	var config = {
		btnId:'themeName',
		treeDataUrl:'${app}/kmmanager/kmTableThemes.do?method=getNodesRadioTree',
		treeRootId:'1',
		treeRootText:'知识库分类',
		treeChkMode:'single',
		treeChkType:'forums',
		showChkFldId:'themeName',
		saveChkFldId:'themeId'
	}
	tree = new xbox(config);	
});
</script>
<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmContentsApplys.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<html:form action="/kmContentsApplys.do?method=searchX" styleId="kmTableGeneralForm" method="post">


		<table align="center">
			<tr>
				<td align="center">
					<fmt:message key="kmTableGeneral.themeId" />
				</td>
				<td class="content">
					<input type="text" id="themeName" name="themeName" class="text" readonly="readonly" />
					<input type="hidden" id="themeId" name="applyTheme" value="${kmContentApplyForm.applyTheme}" />
				</td>

				<td>
					<input type="submit" class="btn" value="<fmt:message key="button.query"/>" />&nbsp;&nbsp;
					<input type="reset" class="btn"	value="<fmt:message key="button.reset"/>" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</html:form>

<content tag="heading">
	<fmt:message key="kmContentsApply.list.heading" />
</content>

	<display:table name="kmContentsApplyList" cellspacing="0" cellpadding="0"
		id="kmContentsApplyList" pagesize="${pageSize}" class="table kmContentsApplyList"
		export="false"
		requestURI="${app}/kmmanager/kmContentsApplys.do?method=searchX"
		sort="list" partialList="true" size="resultSize">

	<display:column sortable="true" headerClass="sortable" titleKey="kmContentsApply.applyTable">
		<eoms:id2nameDB id="${kmContentsApplyList.applyTable}" beanId="kmTableThemeDao" />
	</display:column>

	<display:column sortable="true" headerClass="sortable" titleKey="kmContentsApply.applyTheme">
		<eoms:id2nameDB id="${kmContentsApplyList.applyTheme}" beanId="kmTableThemeDao" />
	</display:column>
		
	<display:column property="applyTitle" sortable="true" 
		headerClass="sortable" titleKey="kmContentsApply.applyTitle"  paramId="id" paramProperty="id"/>

	<display:column  sortable="true" headerClass="sortable" titleKey="kmContentsApply.applyUser"  >
		<eoms:id2nameDB id="${kmContentsApplyList.applyUser}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column property="applyDate" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmContentsApply.applyDate"  paramId="id" paramProperty="id"/>
	
	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${kmContentsApplyList.id}';
		                        var url='${app}/kmmanager/kmContentsApplys.do?method=detail';
		                        url = url + '&id=' + id ;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>	
	</display:column>	
		<display:setProperty name="paging.banner.item_name" value="kmContentsApply" />
		<display:setProperty name="paging.banner.items_name" value="kmContentsApplys" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>